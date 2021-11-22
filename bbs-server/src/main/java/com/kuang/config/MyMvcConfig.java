package com.kuang.config;

import com.kuang.interceptor.CorsInterceptor;
import com.kuang.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 我的mvc配置
 *
 * @author yanni
 * @date 2021/11/22
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
@Bean
public LoginInterceptor getLogin(){
    return  new LoginInterceptor();
}
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(getLogin()).addPathPatterns("/**").excludePathPatterns("/register","/login","/swagger-ui/**","/v3/**","/swagger-ui.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/upload/");
    }

}