package com.example.jieyue.common.component;

import com.example.jieyue.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 访问网页地址时的过滤器，
 * 查看用户是否登陆，
 * 未登陆返回至指定页面
 * </p>
 * @author Bosen
 * 2020/11/2 14:07
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    SysAdminMapper adminMapper;
    @Autowired
    SysRoleMapper roleMapper;
    @Autowired
    SysAccessMapper accessMapper;
    @Autowired
    SysAdminRoleMapper adminRoleMapper;
    @Autowired
    SysRoleAccessMapper roleAccessMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断请求
        String uri = request.getRequestURI();
        String admin = "/admin[\\d\\w\\S]*";
        String merchant = "/mer[\\d\\w\\S]*";
        Pattern adminPattern = Pattern.compile(admin);
        Pattern merchantPattern = Pattern.compile(merchant);
        if(adminPattern.matcher(uri).matches()){
            // admin
            Object user = request.getSession().getAttribute("admin");
            if (user == null) {
                // 获取request返回页面到登录页
                request.getRequestDispatcher("/admin/login").forward(request, response);
                return false;
            }
        }else if (merchantPattern.matcher(uri).matches()){
            // merchant
            Object user = request.getSession().getAttribute("merchant");
            if (user == null) {
                // 获取request返回页面到登录页
                request.getRequestDispatcher("/merchant/login").forward(request, response);
                return false;
            }
        }else{
            // user
            Object user = request.getSession().getAttribute("user");
            if (user == null) {
                // 获取request返回页面到登录页
                request.getRequestDispatcher("/user/login").forward(request, response);
                return false;
            }
        }
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * <p>管理员权限判断</p>
     * @author Bosen
     * 2020/12/7 7:09
     */
    public boolean checkAdminAccess(int adminId,String url){
        int roleId = adminRoleMapper.findByAdminId(adminId).getRoleId();
        return false;
    }
}