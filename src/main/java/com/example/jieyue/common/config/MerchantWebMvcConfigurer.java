package com.example.jieyue.common.config;

import com.example.jieyue.common.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>商户登陆拦截器</p>
 * @author Bosen
 * 2020/11/2 14:08
 */
@Configuration
public class MerchantWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("/merchant/login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/mer*/**")
                .excludePathPatterns(
                        "/merchant/login","/merchant/do-login","/merchant/sign-up","/merchant/sign-check",
                        "/css/**","/js/**","/image/**","/fonts/**","/mapping/**","/data/**",
                        "/lib/*/*/**"
                );
    }
}
