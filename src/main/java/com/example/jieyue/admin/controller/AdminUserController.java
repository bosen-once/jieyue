package com.example.jieyue.admin.controller;

import com.example.jieyue.admin.service.AdminUserService;
import com.example.jieyue.common.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>后台用户列表控制器</p>
 * @author Bosen
 * @date 2021/8/9 22:34
 */
@RestController
public class AdminUserController {
    @Autowired
    AdminUserService userService;

    @RequestMapping("/admin/user")
    public ModelAndView index(ModelAndView modelAndView, @RequestParam(defaultValue = "1")int page){
        // 获取订单信息
        List<SysUser> userList = userService.getUserList(page,10);
        modelAndView.addObject("userList",userList);

        // 获取订单总页数
        int allPage = userService.getAllPage(10);
        modelAndView.addObject("page",page);
        modelAndView.addObject("allPage",allPage);

        modelAndView.setViewName("admin/user/index");
        return modelAndView;
    }

}
