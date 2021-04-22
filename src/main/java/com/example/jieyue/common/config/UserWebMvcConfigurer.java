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
        /*
         * 网站主页，静态资源，网站作者页面，以及登陆注册所需页面外，未登录时的访问 统一跳转至登陆注册页面
         */
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
