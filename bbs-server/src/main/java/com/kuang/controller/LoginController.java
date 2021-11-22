package com.kuang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.model.entity.Invite;
import com.kuang.model.entity.User;
import com.kuang.model.entity.UserInfo;
import com.kuang.model.vo.LoginForm;
import com.kuang.service.InviteService;
import com.kuang.service.UserInfoService;
import com.kuang.service.UserService;
import com.kuang.utils.JwtUtil;
import com.kuang.utils.KuangUtils;
import com.kuang.model.vo.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping
public class LoginController {

    @Resource
    InviteService inviteService;
    @Resource
    UserService userService;
    @Resource
    UserInfoService userInfoService;

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }


    @PostMapping("/login")
    public String login( @RequestParam("username") String username,@RequestParam("password") String password){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        User sqlUser = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));

        System.out.println(sqlUser.getPassword());
        System.out.println(bCryptPasswordEncoder.encode(password));
        if (sqlUser.getUsername()!=null){
            if (bCryptPasswordEncoder.matches(password,sqlUser.getPassword())){

                return JwtUtil.sign(sqlUser.getUsername(), sqlUser.getUid());
            }else {
                return  "密码错误";
            }

        }else {
            return "用户不存在";
        }

    }


    /**
     * 注册
     * 注册业务
     *
     * @param registerForm 注册表单
     * @return {@link String}
     */
    @PostMapping("/register")
    public String register(@RequestBody RegisterForm registerForm ){
        KuangUtils.print("注册表单信息："+registerForm.toString());
        // 表单密码重复判断
        HashMap model=new HashMap();
        if (!registerForm.getPassword().equals(registerForm.getRepassword())){
            model.put("registerMsg","密码输入有误");
            return "register";
        }
        // 用户名已存在
        User hasUser = userService.getOne(new QueryWrapper<User>().eq("username", registerForm.getUsername()));
        if (hasUser!=null){
            model.put("registerMsg","用户名已存在");
            return "register";
        }

        // 验证邀请码
        Invite invite = inviteService.getOne(new QueryWrapper<Invite>().eq("code", registerForm.getCode()));
        if (invite==null){
            model.put("registerMsg","邀请码不存在");
            return "register";
        }else {
            // 邀请码存在，判断邀请码是否有效
            if (invite.getStatus()==1){
                model.put("registerMsg","邀请码已被使用");
                return "register";
            }else {
                // 构建用户对象
                User user = new User();
                user.setUid(KuangUtils.getUuid()); // 用户唯一id
                user.setRoleId(2);
                user.setUsername(registerForm.getUsername());
                // 密码加密
                String bCryptPassword = new BCryptPasswordEncoder().encode(registerForm.getPassword());
                user.setPassword(bCryptPassword);
                user.setGmtCreate(KuangUtils.getTime());
                user.setLoginDate(KuangUtils.getTime());
                // 保存对象！
                userService.save(user);
                KuangUtils.print("新用户注册成功："+user);

                // 激活邀请码
                invite.setActiveTime(KuangUtils.getTime());
                invite.setStatus(1);
                invite.setUid(user.getUid());
                inviteService.updateById(invite);

                // todo: 用户信息
                userInfoService.save(new UserInfo().setUid(user.getUid()));

                // 注册成功，重定向到登录页面
                return "redirect:/toLogin";
            }
        }
    }

}
