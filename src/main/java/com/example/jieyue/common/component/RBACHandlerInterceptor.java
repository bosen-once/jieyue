package com.example.jieyue.common.component;

import com.example.jieyue.common.entity.*;
import com.example.jieyue.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>权限检查拦截器</p>
 * @author Bosen
 * @date 2021/8/9 22:40
 */
@Component
public class RBACHandlerInterceptor implements HandlerInterceptor {
    
    private static SysRoleMapper roleMapper;

    private static SysAccessMapper accessMapper;

    private static SysAdminRoleMapper adminRoleMapper;

    private static SysRoleAccessMapper roleAccessMapper;

    @Autowired
    public void setRoleMapper(SysRoleMapper roleMapper) {
        RBACHandlerInterceptor.roleMapper = roleMapper;
    }

    @Autowired
    public void setAccessMapper(SysAccessMapper accessMapper) {
        RBACHandlerInterceptor.accessMapper = accessMapper;
    }

    @Autowired
    public void setAdminRoleMapper(SysAdminRoleMapper adminRoleMapper) {
        RBACHandlerInterceptor.adminRoleMapper = adminRoleMapper;
    }

    @Autowired
    public void setRoleAccessMapper(SysRoleAccessMapper roleAccessMapper) {
        RBACHandlerInterceptor.roleAccessMapper = roleAccessMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!checkRbac(request)) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("" +
                    "<html>" +
                    "<head>" +
                    "<meta charset='utf-8'>" +
                    "<title>权限不足</title>" +
                    "</head>" +
                    "<body>" +
                    "<script>" +
                    "   alert('你未拥有该权限！');" +
                    "   window.history.back();" +
                    "</script>" +
                    "</body>" +
                    "</html>");
            return false;
        }
        return true;
    }

    /**
     * <p>检查权限</p>
     */
    public boolean checkRbac(HttpServletRequest request) {
        try {
            // 获取当前请求的地址
            String curUrl = request.getRequestURI();

            // 获取当前管理员的信息
            SysAdmin admin = (SysAdmin) request.getSession().getAttribute("admin");
            if (admin.getId() == 1) {
                // 不限制id为1的管理员
                return true;
            }

            // 获取角色信息
            SysAdminRole adminRole = adminRoleMapper.findByAdminId(admin.getId());

            SysRole role = roleMapper.findById(adminRole.getRoleId());
            if (role == null || role.getStatus() == 0) {
                return false;
            }

            // 获取权限信息
            List<SysRoleAccess> roleAccessList = roleAccessMapper.findByRoleId(role.getId());
            for (SysRoleAccess roleAccess : roleAccessList) {
                SysAccess access = accessMapper.findById(roleAccess.getAccessId());
                if (access == null) {
                    continue;
                }
                if (access.getUrl().equals(curUrl) && access.getStatus() == 1) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
