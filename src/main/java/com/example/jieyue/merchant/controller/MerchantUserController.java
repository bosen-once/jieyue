package com.example.jieyue.merchant.controller;

import com.example.jieyue.common.entity.SysUser;
import com.example.jieyue.merchant.service.MerchantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MerchantUserController {
    @Autowired
    MerchantUserService userService;

    @RequestMapping("/merchant/user")
    public ModelAndView index(ModelAndView modelAndView, @RequestParam(defaultValue = "1")int page){
        // 获取订单信息
        List<SysUser> userList = userService.getUserList(page,10);
        modelAndView.addObject("userList",userList);

        // 获取订单总页数
        int allPage = userService.getAllPage(10);
        modelAndView.addObject("page",page);
        modelAndView.addObject("allPage",allPage);

        modelAndView.setViewName("merchant/user/index");
        return modelAndView;
    }
}
