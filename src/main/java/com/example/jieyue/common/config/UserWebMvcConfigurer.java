package com.example.jieyue.common.config;

import com.example.jieyue.common.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>用户登陆拦截器</p>
 * @author Bosen
 * 2020/11/2 14:08
 */
@Configuration
public class UserWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("/user/login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns(
                        "/","/user/home","/user/login","/user/do-login","/user/sign-up","/user/sign-check",
                        "/css/**","/js/**","/image/**","/fonts/**","/mapping/**","/data/**","/user/check-order-status",
                        "/lib/*/*/**","/user/about","/user/product","/user/shop","/user/search","/user/wxpay/notify",
                        "/user/pay/test"
                );
    }

}
