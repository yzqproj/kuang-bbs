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
import com.kuang.utils.RequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
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


@GetMapping("/getUser")
public User getUser(){
    User sessionUser = RequestHelper.getSessionUser();
    return  sessionUser;

}

    @PostMapping("/login")
    public String login( @RequestParam("username") String username,@RequestParam("password") String password){

        User sqlUser = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));

        if (sqlUser.getUsername()!=null){
            if (Objects.equals(password, sqlUser.getPassword())){

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
            return "密码输入有误";
        }
        // 用户名已存在
        User hasUser = userService.getOne(new QueryWrapper<User>().eq("username", registerForm.getUsername()));
        if (hasUser!=null){
            model.put("registerMsg","用户名已存在");
            return "用户名已存在";
        }

        // 验证邀请码
        Invite invite = inviteService.getOne(new QueryWrapper<Invite>().eq("code", registerForm.getCode()));
        if (invite==null){
            model.put("registerMsg","邀请码不存在");
            return "邀请码不存在r";
        }else {
            // 邀请码存在，判断邀请码是否有效
            if (invite.getStatus()==1){
                model.put("registerMsg","邀请码已被使用");
                return "邀请码已被使用r";
            }else {
                // 构建用户对象
                User user = new User();
                user.setUid(KuangUtils.getUuid()); // 用户唯一id
                user.setRoleId(2);
                user.setUsername(registerForm.getUsername());
                // 密码加密
                user.setPassword(registerForm.getPassword());
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
