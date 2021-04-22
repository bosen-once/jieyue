package com.example.jieyue.admin.controller;

import com.example.jieyue.admin.service.AdminRbacService;
import com.example.jieyue.common.entity.*;
import com.example.jieyue.common.mapper.*;
import com.example.jieyue.common.utils.IsEmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminRbacController {
    @Autowired
    AdminRbacService rbacService;
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
    @Autowired
    IsEmptyUtil isEmptyUtil;

    @RequestMapping("/admin/rbac")
    public ModelAndView index(ModelAndView modelAndView){
        // 管理员列表
        List<SysAdmin> adminList = adminMapper.findAll();
        // 角色列表
        List<SysRole> roleList = roleMapper.findAll();
        // 权限列表
        List<SysAccess> accessList = accessMapper.findAll();

        modelAndView.addObject("adminList",adminList);
        modelAndView.addObject("roleList",roleList);
        modelAndView.addObject("accessList",accessList);

        modelAndView.setViewName("admin/rbac/index");
        return modelAndView;
    }

    /*
     * 编辑管理员的角色页面
     * todo
     */
    @RequestMapping("/admin/rbac/update-admin-role")
    public ModelAndView updateAdminRole(ModelAndView modelAndView,int adminId){
        // 获取管理员角色信息
        SysAdminRole adminRole = adminRoleMapper.findByAdminId(adminId);

        modelAndView.addObject("adminRole",adminRole);
        modelAndView.setViewName("updateRoleAccess");
        return modelAndView;
    }

    /*
     * 执行编辑管理员的角色操作
     * todo
     */
    @RequestMapping("/admin/rbac/update-admin-role-action")
    public ModelAndView updateAdminRoleAction(ModelAndView modelAndView,int adminId,String roles){

        modelAndView.setViewName("updateRoleAccess");
        return modelAndView;
    }

    /*
     * 添加角色
     */
    @RequestMapping("/admin/add-role")
    public ModelAndView addRole(ModelAndView modelAndView,String name){
        if (isEmptyUtil.strings(name)){
            modelAndView.addObject("msg","请输入角色名");
        }
        if (roleMapper.countByName(name)!=0){
            modelAndView.addObject("msg","此角色已存在");
        }
        if (roleMapper.insert(name)!=1){
            modelAndView.addObject("msg","添加角色失败");
        }else{
            modelAndView.addObject("msg","添加角色成功");
        }
        modelAndView.setViewName("redirect:/admin/rbac");
        return modelAndView;
    }

    /*
     * 添加权限
     */
    @RequestMapping("/admin/add-access")
    public ModelAndView addAccess(ModelAndView modelAndView,String name,String url){
        if (isEmptyUtil.strings(name,url)){
            modelAndView.addObject("msg","必填信息不能为空");
        }
        if (accessMapper.countByName(name)!=0){
            modelAndView.addObject("msg","此权限已存在");
        }
        if (accessMapper.insert(name,url)!=1){
            modelAndView.addObject("msg","添加权限失败");
        }else{
            modelAndView.addObject("msg","添加权限成功");
        }
        modelAndView.setViewName("redirect:/admin/rbac");
        return modelAndView;
    }

    @RequestMapping("/admin/del-admin")
    public ModelAndView delAdmin(ModelAndView modelAndView,int id){
        modelAndView.setViewName("redirect:/admin/rbac");
        if (id==1){
            modelAndView.addObject("msg","无法删除此管理员");
            return modelAndView;
        }
        if (adminMapper.deleteById(id)==1){
            modelAndView.addObject("msg","删除管理员成功");
        }else{
            modelAndView.addObject("msg","删除管理员失败");
        }
        return modelAndView;
    }

    @RequestMapping("/admin/del-role")
    public ModelAndView delRole(ModelAndView modelAndView,int id){
        if (roleMapper.deleteById(id)==1){
            modelAndView.addObject("msg","删除角色成功");
        }else{
            modelAndView.addObject("msg","删除角色失败");
        }
        modelAndView.setViewName("redirect:/admin/rbac");
        return modelAndView;
    }

    @RequestMapping("/admin/del-access")
    public ModelAndView delAccess(ModelAndView modelAndView,int id){
        if (accessMapper.deleteById(id)==1){
            modelAndView.addObject("msg","删除权限成功");
        }else{
            modelAndView.addObject("msg","删除权限失败");
        }
        modelAndView.setViewName("redirect:/admin/rbac");
        return modelAndView;
    }

    /*
     * 停用管理员
     */
    @RequestMapping("/admin/off-admin")
    public ModelAndView offAdmin(ModelAndView modelAndView,int id){
        modelAndView.setViewName("redirect:/admin/rbac");
        if (id==1){
            modelAndView.addObject("无法停用此管理员！");
            return modelAndView;
        }
        if (adminMapper.updateMarkById(id,0)==1){
            modelAndView.addObject("msg","停用成功！");
        }else{
            modelAndView.addObject("msg","停用失败！");
        }
        return modelAndView;
    }

    /*
     * 启用管理员
     */
    @RequestMapping("/admin/on-admin")
    public ModelAndView onAdmin(ModelAndView modelAndView,int id){
        modelAndView.setViewName("redirect:/admin/rbac");
        if (adminMapper.updateMarkById(id,1)==1){
            modelAndView.addObject("msg","启用成功！");
        }else{
            modelAndView.addObject("msg","启用失败！");
        }
        return modelAndView;
    }

    /*
     * 停用角色
     */
    @RequestMapping("/admin/off-role")
    public ModelAndView offRole(ModelAndView modelAndView,int id){
        modelAndView.setViewName("redirect:/admin/rbac");
        if (roleMapper.updateStatus(0,id)==1){
            modelAndView.addObject("msg","停用角色成功！");
        }else{
            modelAndView.addObject("msg","停用角色失败！");
        }
        return modelAndView;
    }

    /*
     * 启用角色
     */
    @RequestMapping("/admin/on-role")
    public ModelAndView onRole(ModelAndView modelAndView,int id){
        modelAndView.setViewName("redirect:/admin/rbac");
        if (roleMapper.updateStatus(1,id)==1){
            modelAndView.addObject("msg","启用角色成功！");
        }else{
            modelAndView.addObject("msg","启用角色失败！");
        }
        return modelAndView;
    }

    /*
     * 启用权限
     */
    @RequestMapping("/admin/on-access")
    public ModelAndView onAccess(ModelAndView modelAndView,int id){
        modelAndView.setViewName("redirect:/admin/rbac");
        if (accessMapper.updateStatus(1,id)==1){
            modelAndView.addObject("msg","启用权限成功！");
        }else{
            modelAndView.addObject("msg","启用权限失败！");
        }
        return modelAndView;
    }

    /*
     * 停用权限
     */
    @RequestMapping("/admin/off-access")
    public ModelAndView offAccess(ModelAndView modelAndView,int id){
        modelAndView.setViewName("redirect:/admin/rbac");
        if (accessMapper.updateStatus(0,id)==1){
            modelAndView.addObject("msg","停用权限成功！");
        }else{
            modelAndView.addObject("msg","停用权限失败！");
        }
        return modelAndView;
    }

    /*
     * 修改权限信息
     */
    @RequestMapping("/admin/update-access")
    public ModelAndView updateAccess(ModelAndView modelAndView,int id,String name,String url){
        modelAndView.setViewName("redirect:/admin/rbac");
        if (accessMapper.updateInfo(id,name,url)==1){
            modelAndView.addObject("msg","修改权限信息成功！");
        }else{
            modelAndView.addObject("msg","修改权限信息失败！");
        }
        return modelAndView;
    }

    /*
     * 修改角儿权限信息页面
     */
    @RequestMapping("/admin/role-access")
    public ModelAndView roleAccessIndex(ModelAndView modelAndView,int id){
        modelAndView.setViewName("/admin/rbac/role-access");
        // 获取角色信息
        SysRole role = roleMapper.findById(id);
        // 获取角色的权限信息
        List<SysRoleAccess> roleAccessList = roleAccessMapper.findByRoleId(id);
        Map<Integer,SysRoleAccess> roleAccessMap = new HashMap<>();
        for (SysRoleAccess sysRoleAccess : roleAccessList) {
            roleAccessMap.put(sysRoleAccess.getAccessId(),sysRoleAccess);
        }
        // 获取全部权限信息
        List<SysAccess> allAccessList = accessMapper.findAll();

        modelAndView.addObject("role",role);
        modelAndView.addObject("allAccessList",allAccessList);
        modelAndView.addObject("roleAccessMap",roleAccessMap);

        return modelAndView;
    }

    /*
     * 修改角色的权限
     */
    @RequestMapping("/admin/role-access/action")
    public ModelAndView action(ModelAndView modelAndView,int role,int ... ids){
        modelAndView.setViewName("redirect: /admin/role-access?id="+role);
        if (rbacService.setRoleAccess(role,ids)){
            modelAndView.addObject("msg","设置成功");
        }else{
            modelAndView.addObject("msg","设置失败");
        }
        return modelAndView;
    }
}
