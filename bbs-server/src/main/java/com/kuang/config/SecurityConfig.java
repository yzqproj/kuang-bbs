package com.kuang.config;

import com.kuang.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * 安全配置
 *
 * @author yanni
 * @date 2021/11/21
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    UserServiceImpl userService;

    /**
     * 配置
     *请求授权验证
     * @param http http
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // .denyAll();    //拒绝访问
        // .authenticated();    //需认证通过
        // .permitAll();    //无条件允许访问
        // 访问权限
        http.authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/register", "/login", "/toLogin","/swagger-ui/**","/swagger-ui.html").permitAll()
                .antMatchers("/*").authenticated();

        // 登录配置
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/toLogin")
                .loginProcessingUrl("/login") // 登陆表单提交请求
                .defaultSuccessUrl("/index"); // 设置默认登录成功后跳转的页面

        // 注销配置
        http.headers().contentTypeOptions().disable();
        http.headers().frameOptions().disable(); // 图片跨域
        http.csrf().disable();//关闭csrf功能:跨站请求伪造,默认只能通过post方式提交logout请求
        http.logout().logoutSuccessUrl("/");

        // 记住我配置
        http.rememberMe().rememberMeParameter("remember");
    }

    /**
     * 配置
     * 用户授权验证
     * @param auth 身份验证
     * @throws Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    // 密码加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}