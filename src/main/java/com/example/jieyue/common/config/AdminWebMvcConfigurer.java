package com.example.jieyue.common.config;

import com.example.jieyue.common.component.LoginHandlerInterceptor;
import com.example.jieyue.common.component.RBACHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>后台登陆拦截器</p>
 * @author Bosen
 * 2020/11/2 14:08
 */
@Configuration
public class AdminWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("/admin/login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns(
                        "/admin/login","/admin/do-login","/admin/sign-up","/admin/sign-check",
                        "/css/**","/js/**","/image/**","/fonts/**","/mapping/**","/data/**",
                        "/lib/*/*/**"
                );

        // 权限拦截
        registry.addInterceptor(new RBACHandlerInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns(
                        "/admin/login","/admin/logout","/admin/do-login","/admin/sign-up","/admin/sign-check",
                        "/css/**","/js/**","/image/**","/fonts/**","/mapping/**","/data/**",
                        "/lib/*/*/**", "/admin/home", "/admin"
                );
    }

}
