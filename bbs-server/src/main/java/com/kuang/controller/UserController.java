package com.kuang.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.model.entity.Blog;
import com.kuang.model.entity.Comment;
import com.kuang.model.entity.Question;
import com.kuang.model.entity.UserInfo;
import com.kuang.service.BlogService;
import com.kuang.service.CommentService;
import com.kuang.service.QuestionService;
import com.kuang.service.UserInfoService;
import com.kuang.utils.KuangUtils;
import com.kuang.model.vo.LayerPhoto;
import com.kuang.model.vo.LayerPhotoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 遇见狂神说
 * @since 2020-06-28
 */
@RestController
@RequestMapping
public class UserController {

    @Resource
    UserInfoService userInfoService;
    @Resource
    BlogService blogService;
    @Resource
    QuestionService questionService;
    @Resource
    CommentService commentService;

    @GetMapping("/user/{uid}")
    public String userIndex(@PathVariable String uid, Model model){
        // 用户信息回填
        userInfoCallBack(uid,model);
        // 用户的博客列表
        Page<Blog> pageParam = new Page<>(1, 10);
        blogService.page(pageParam,new QueryWrapper<Blog>().eq("author_id", uid)
                                                         .orderByDesc("gmt_create"));
        // 结果
        List<Blog> blogList = pageParam.getRecords();
        model.addAttribute("blogList",blogList);
        model.addAttribute("pageParam",pageParam);

        return "user/index";
    }

    @GetMapping("/user/blog/{uid}/{page}/{limit}")
    public String userIndexBlog(@PathVariable String uid,
                                @PathVariable int page,
                                @PathVariable int limit,
                                Model model){
        // 用户信息回填
        userInfoCallBack(uid,model);
        // 用户的博客列表
        if (page < 1){
            page = 1;
        }
        Page<Blog> pageParam = new Page<>(page, limit);
        blogService.page(pageParam,new QueryWrapper<Blog>().eq("author_id", uid)
                .orderByDesc("gmt_create"));

        // 结果
        List<Blog> blogList = pageParam.getRecords();
        model.addAttribute("blogList",blogList);
        model.addAttribute("pageParam",pageParam);

        return "user/index";
    }

    @GetMapping("/user/question/{uid}/{page}/{limit}")
    public String userIndexQuestion(@PathVariable String uid,
                                @PathVariable int page,
                                @PathVariable int limit,
                                Model model){
        // 用户信息回填
        userInfoCallBack(uid,model);

        //
        if (page < 1){
            page = 1;
        }
        Page<Question> pageParam = new Page<>(page, limit);
        questionService.page(pageParam,new QueryWrapper<Question>().eq("author_id", uid)
                .orderByDesc("gmt_create"));

        // 结果
        List<Question> blogList = pageParam.getRecords();
        model.addAttribute("questionList",blogList);
        model.addAttribute("pageParam",pageParam);

        return "user/user-question";
    }

    @GetMapping("/user/comment/{uid}/{page}/{limit}")
    public String userIndexComment(@PathVariable String uid,
                                    @PathVariable int page,
                                    @PathVariable int limit,
                                    Model model){
        // 用户信息回填
        userInfoCallBack(uid,model);
        //
        if (page < 1){
            page = 1;
        }
        Page<Comment> pageParam = new Page<>(page, limit);
        commentService.page(pageParam,new QueryWrapper<Comment>().eq("user_id", uid)
                .orderByDesc("gmt_create"));

        // 结果
        List<Comment> commentList = pageParam.getRecords();
        model.addAttribute("commentList",commentList);
        model.addAttribute("pageParam",pageParam);

        return "user/user-comment";
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
       long blogCount =  blogService.count(new QueryWrapper<Blog>().eq("author_id", uid));
       long questionCount = questionService.count(new QueryWrapper<Question>().eq("author_id", uid));
        long commentCount = commentService.count(new QueryWrapper<Comment>().eq("user_id", uid));
        model.addAttribute("blogCount",blogCount);
        model.addAttribute("questionCount",questionCount);
        model.addAttribute("commentCount",commentCount);

    }

    // 捐赠layer弹窗二维码
    @GetMapping("/user/donate/{uid}")
    @ResponseBody
    public String userLayerDonate(@PathVariable String uid){
        // todo: 数据库设计
        ArrayList<LayerPhotoData> layerPhotos = new ArrayList<>();

        layerPhotos.add(new LayerPhotoData().setAlt("支付宝").setPid(1).setSrc("/images/donate/alipay.png").setThumb(""));
        layerPhotos.add(new LayerPhotoData().setAlt("微信").setPid(2).setSrc("/images/donate/wechat.jpg").setThumb(""));

        LayerPhoto donate = new LayerPhoto().setTitle("赞赏").setId(666).setStart(1);
        donate.setData(layerPhotos);

        String donateJsonString = JSONObject.toJSONString(donate);
        KuangUtils.print(donateJsonString);
        return donateJsonString;
    }

    // 更新头像
    @GetMapping("/user/update-avatar/{uid}")
    public String toUpdateAvatar(@PathVariable String uid,Model model){
        // 用户信息回填
        userInfoCallBack(uid,model);
        return "user/update-avatar";
    }

}

