package com.example.jieyue.merchant.controller;

import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.merchant.service.MerchantLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Service;

/**
 * <p>商户登录控制器</p>
 * @author Bosen
 * 2020/11/8 12:43
 */
@RestController
@RequestMapping("/merchant")
public class MerchantLoginController {
    @Autowired
    MerchantLoginService loginService;

    @RequestMapping({"login",""})
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("merchant/login/index");
        return modelAndView;
    }
    
    /*
     * 登录
     */
    @RequestMapping("do-login")
    public ModelAndView doLogin(ModelAndView modelAndView, String email, String password, HttpSession session){
        int serviceRes = loginService.doLogin(email,password);
        switch (serviceRes){
            case 0:
                modelAndView.addObject("msg","必填信息不能为空");
                modelAndView.setViewName("redirect:login");
                return modelAndView;
            case -1:
                modelAndView.addObject("msg","邮箱或密码错误");
                modelAndView.setViewName("redirect:login");
                return modelAndView;
            case 1:
                // 登陆成功
                SysMt merchant = loginService.getMerchantInfo(email);
                if (merchant.getState() == 0) {
                    modelAndView.addObject("msg","您的账号处于停用状态，请等待管理员处理");
                    break;
                }
                session.setAttribute("merchant",merchant);
                break;
        }
        modelAndView.setViewName("redirect:/merchant/home");
        return modelAndView;
    }

    /*
     * 退出登录
     */
    @RequestMapping("logout")
    public ModelAndView logout(ModelAndView modelAndView,HttpServletRequest request){
        request.getSession().setAttribute("merchant",null);
        modelAndView.setViewName("redirect:/merchant/login");
        return modelAndView;
    }

    /*
     * 注册
     */
    @RequestMapping("sign-up")
    public String signUp(String email,String name,String password){
        int serviceRes = loginService.signUp(email,name,password);
        switch (serviceRes){
            case -1:
                return "验证邮件发送失败，请重试";
            case 0:
                return "必填信息不能为空";
            case 1:
                return "验证信息已发送至邮箱，请留意接收";
            case 2:
                return "该邮箱已被注册";
            case 3:
                return "邮箱格式不正确";
        }
        return null;
    }

    /*
     * 邮箱验证
     */
    @RequestMapping("sign-check")
    public ModelAndView singCheck(ModelAndView modelAndView,String email){
        int serviceRes = loginService.singCheck(email);
        switch (serviceRes){
            case -1:
                modelAndView.setViewName("redirect:/error");
                break;
            case 0:
                modelAndView.setViewName("redirect:/merchant/login");
                modelAndView.addObject("msg","网络超时请重试");
                break;
            case 1:
                modelAndView.setViewName("redirect:/merchant/login");
                modelAndView.addObject("msg","注册成功，请等待管理员的审核");
                break;
        }
        return modelAndView;
    }
}
