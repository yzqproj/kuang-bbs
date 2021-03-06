package com.kuang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.model.entity.Blog;
import com.kuang.model.entity.Comment;
import com.kuang.model.entity.Question;
import com.kuang.model.entity.UserInfo;
import com.kuang.service.BlogService;
import com.kuang.service.CommentService;
import com.kuang.service.QuestionService;
import com.kuang.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 遇见狂神说
 * @since 2020-06-29
 */
@RestController
@RequestMapping
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;
    @Resource
    BlogService blogService;
    @Resource
    QuestionService questionService;
    @Resource
    CommentService commentService;

    // 更新用户资料
    @GetMapping("/userinfo/setting/{uid}")
    public String userSetting(@PathVariable String uid, Model model){
        // 用户信息回填
        userInfoCallBack(uid,model);
        // todo: 可扩展
        return "user/settings";
    }
    @PostMapping("/userinfo/update/{uid}")
    public String userInfo(@PathVariable String uid,UserInfo userInfo){
        // 获取用户信息;
        userInfoService.updateById(userInfo);
        return "redirect:/user/"+uid;
    }

    // 用户信息回填
    private void userInfoCallBack(String uid,Model model){
        UserInfo userInfo = userInfoService.getById(uid);
        model.addAttribute("userInfo",userInfo);
        if (userInfo.getHobby()!=null && !userInfo.getHobby().equals("")){
            String[] hobbys = userInfo.getHobby().split(",");
            model.addAttribute("infoHobbys",hobbys);
        }
        // 获取用户的问题，博客，回复数
        long blogCount = blogService.count(new QueryWrapper<Blog>().eq("author_id", uid));
       long questionCount = questionService.count(new QueryWrapper<Question>().eq("author_id", uid));
        long commentCount = commentService.count(new QueryWrapper<Comment>().eq("user_id", uid));
        model.addAttribute("blogCount",blogCount);
        model.addAttribute("questionCount",questionCount);
        model.addAttribute("commentCount",commentCount);
    }


}

