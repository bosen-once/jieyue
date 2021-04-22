package com.example.jieyue.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>关于我们页面</p>
 * @author Bosen
 * 2020/11/2 21:00
 */
@RestController
public class UserAboutController {
    @RequestMapping("/user/about")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("user/about/index");
        return modelAndView;
    }
}
