package com.example.jieyue.user.controller;

import com.example.jieyue.common.entity.SysUser;
import com.example.jieyue.user.service.UserLoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>登陆接口控制器类</p>
 * @author Bosen
 * 2020/10/30 17:42
 */
@RestController
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    private UserLoginService service;
    /*
     * 登陆页面
     */
    @RequestMapping("login")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("user/login/index");
        return modelAndView;
    }
    
    /*
     * 退出登陆
     */
    @RequestMapping("logout")
    public ModelAndView logout(ModelAndView modelAndView,HttpServletRequest request){
        request.getSession().setAttribute("user",null);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    /*
     * 登陆请求
     */
    @RequestMapping("do-login")
    public ModelAndView doLogin(HttpSession session, ModelAndView modelAndView, String email, String password){
        int result = service.dologin(email,password);
        if (result==1){
            // 登陆成功
            SysUser user = service.userInfo(email);
            session.setAttribute("user",user);
            modelAndView.setViewName("redirect:/");
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
     * 注册请求
     */
    @RequestMapping("sign-up")
    public String signUp(String email,String username,String password,String repwd){
        int result = service.singup(email,username,password,repwd);
        if (result==0){
            return "必填信息不能为空！";
        }
        if (result==4){
            return "该邮箱已被注册";
        }
        if (result==3){
            return "邮箱格式不正确";
        }
        if (result==2){
            return "两次密码输入不一致";
        }
        if (result==1){
            return "验证邮件已发送，请留意您的邮箱";
        }
        if (result==-1){
            return "注册超时，请重试";
        }
        return "网络出现错误！！";
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
            modelAndView.addObject("msg","注册成功，请完成登录");
            modelAndView.setViewName("redirect:login");

        }
        return modelAndView;
    }

}
