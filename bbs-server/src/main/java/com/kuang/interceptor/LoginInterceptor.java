package com.kuang.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.model.entity.User;
import com.kuang.service.UserService;
import com.kuang.utils.JwtUtil;
import com.kuang.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yanni
 * @date time 2021/11/22 14:18
 * @modified By:
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }

        log.info("token=" + token);
        boolean flag = JwtUtil.verifyToken(token);

        if (flag) {
            String userId = JwtUtil.getUserId(token);
            assert userId != null;
            User user = UserUtil.getUserByUserCode(userId);


            int level = 0;

            request.setAttribute("user", user);
            // String email = (String) session.getAttribute("email");
            //如果session中没有user，表示没登陆
            System.out.println("-----------------------------------------------------------");
            System.out.println(user);
            if (user.getUsername() == null) {
                //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
                //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
                System.out.println("没有登录权限");
                return false;
            } else {
                if (level == 2) {
                    System.out.println("进入成功");
                    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
                    return true;
                } else {
                    return false;
                }
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
