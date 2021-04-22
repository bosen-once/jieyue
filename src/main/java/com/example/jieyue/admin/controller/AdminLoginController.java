package com.example.jieyue.admin.controller;

import com.example.jieyue.admin.service.AdminLoginService;
import com.example.jieyue.common.entity.SysAdmin;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>后台登陆控制器</p>
 * @author Bosen
 * 2020/11/3 14:03
 */
@RestController
@RequestMapping("/admin")
public class AdminLoginController {
    @Autowired
    AdminLoginService service;
    /*
     * 登陆页面
     */
    @RequestMapping({"login",""})
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("admin/login/index");
        return modelAndView;
    }

    /*
     * 登陆处理
     */
    @RequestMapping("do-login")
    public ModelAndView doLogin(HttpSession session, ModelAndView modelAndView,String email, String password){
        int result = service.doLogin(email,password);
        if (result==1){
            // 登陆成功
            SysAdmin admin = service.adminInfo(email);
            session.setAttribute("admin",admin);
            modelAndView.setViewName("redirect:/admin/home");
        }else if (result==-1){
            // 用户名或密码错误！
            modelAndView.addObject("msg","用户名或密码错误！");
            modelAndView.setViewName("redirect:login");
        }else if (result==0){
            // 必填信息不能为空！
            modelAndView.addObject("msg","必填信息不能为空！");
            modelAndView.setViewName("redirect:login");
        }
        return modelAndView;
    }
    
    /*
     * 退出登录
     */
    @RequestMapping("logout")
    public ModelAndView logout(ModelAndView modelAndView,HttpSession session){
        session.setAttribute("merchant",null);
        modelAndView.setViewName("redirect:/admin/login");
        return modelAndView;
    }

    /*
     * 注册请求
     */
    @RequestMapping("sign-up")
    public ModelAndView signUp(ModelAndView modelAndView,String name,String email,String password){
        int result = service.singup(email,email,password);
        if (result==0){
            // 必填信息不能为空！
            modelAndView.addObject("msg","必填信息不能为空！");
        }
        if (result==4){
            // 该邮箱已被注册
            modelAndView.addObject("msg","该邮箱已被注册");
        }
        if (result==3){
            // 邮箱格式不正确
            modelAndView.addObject("msg","邮箱格式不正确");
        }
        if (result==2){
            // 两次密码输入不一致
            modelAndView.addObject("msg","两次密码输入不一致");
        }
        if (result==1){
            // 验证邮件已发送，请留意您的邮箱
            modelAndView.addObject("msg","验证邮件已发送，请留意您的邮箱");
        }
        if (result==-1){
            // 验证邮件发送失败，请重试
            modelAndView.addObject("msg","注册超时，请重试");
        }
        modelAndView.setViewName("redirect:login");
        return modelAndView;
    }

    /*
     * 验证邮件确认注册
     */
    @RequestMapping("sign-check")
    public ModelAndView singCheck(ModelAndView modelAndView,@Param("email") String email){
        int res = service.singCheck(email);
        if (res==-1){
            modelAndView.setViewName("redirect:/error");
        }
        if (res==0){
            modelAndView.addObject("网络超时请重试");
            modelAndView.setViewName("redirect:login");
        }
        if (res==1){
            modelAndView.addObject("msg","注册成功");
            modelAndView.setViewName("redirect:login");
        }
        return modelAndView;
    }
}
