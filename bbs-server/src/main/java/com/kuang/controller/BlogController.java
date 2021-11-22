package com.kuang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.model.entity.Blog;
import com.kuang.model.entity.BlogCategory;
import com.kuang.model.entity.Comment;
import com.kuang.service.BlogCategoryService;
import com.kuang.service.BlogService;
import com.kuang.service.CommentService;
import com.kuang.utils.KuangUtils;
import com.kuang.model.vo.QuestionWriteForm;
import com.kuang.utils.RequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 遇见狂神说
 * @since 2020-06-29
 */
@RestController
@RequestMapping
public class BlogController {

    @Resource
    BlogCategoryService blogCategoryService;
    @Resource
    BlogService blogService;
    @Resource
    CommentService commentService;


    @GetMapping("/blogList/{page}/{limit}")
    public HashMap blogListPage(
            @PathVariable int page,
            @PathVariable int limit
    ) {
        HashMap<String, Object> model = new HashMap<>();
        ;
        if (page < 1) {
            page = 1;
        }
        Page<Blog> pageParam = new Page<>(page, limit);
        blogService.page(pageParam, new QueryWrapper<Blog>().orderByDesc("gmt_create"));

        // 结果
        List<Blog> blogList = pageParam.getRecords();
        model.put("blogList", blogList);
        model.put("pageParam", pageParam);

        // 分类信息
        List<BlogCategory> categoryList = blogCategoryService.list(null);
        model.put("categoryList", categoryList);

        return model;
    }

    // 写文章
    @GetMapping("/blog/write")
    public HashMap toWrite() {
        HashMap model = new HashMap();
        List<BlogCategory> categoryList = blogCategoryService.list(null);
        model.put("categoryList", categoryList);
        return model;
    }

    @PostMapping("/blog/write")
    public synchronized HashMap write(QuestionWriteForm questionWriteForm) {
        // 构建问题对象
        Blog blog = new Blog();

        blog.setBid(KuangUtils.getUuid());
        blog.setTitle(questionWriteForm.getTitle());
        blog.setContent(questionWriteForm.getContent());
        blog.setSort(0);
        blog.setViews(0);

        blog.setAuthorId(questionWriteForm.getAuthorId());
        blog.setAuthorName(questionWriteForm.getAuthorName());
        blog.setAuthorAvatar(questionWriteForm.getAuthorAvatar());

        BlogCategory category = blogCategoryService.getById(questionWriteForm.getCategoryId());
        blog.setCategoryId(questionWriteForm.getCategoryId());
        blog.setCategoryName(category.getCategory());
        blog.setGmtCreate(KuangUtils.getTime());
        blog.setGmtUpdate(KuangUtils.getTime());
        // 存储对象
        blogService.save(blog);

        // 重定向到列表页面
        return null;
    }

    /**
     * 阅读文章
     *
     * @param bid 报价
     * @return {@link HashMap}
     */
    @GetMapping("/blog/read/{bid}")
    public HashMap read(@PathVariable("bid") String bid) {
        HashMap model = new HashMap();
        Blog blog = blogService.getOne(new QueryWrapper<Blog>().eq("bid", bid));
        // todo: redis缓存. 防止阅读重复
        blog.setViews(blog.getViews() + 1);
        blogService.updateById(blog);
        model.put("blog", blog);
        // todo： 查询评论
        List<Comment> commentList = commentService.list(new QueryWrapper<Comment>().eq("topic_id", bid).orderByDesc("gmt_create"));
        model.put("commentList", commentList);
        return model;
    }

    // 编辑问题
    @GetMapping("/blog/editor/{uid}/{bid}")
    public synchronized HashMap toEditor(@PathVariable("uid") String uid,
                                         @PathVariable("bid") String bid) {
        HashMap model = new HashMap();
        Blog blog = blogService.getOne(new QueryWrapper<Blog>().eq("bid", bid));

        if (!blog.getAuthorId().equals(uid)) {
            KuangUtils.print("禁止非法编辑");
            return null;
        }

        model.put("blog", blog);

        List<BlogCategory> categoryList = blogCategoryService.list(null);
        model.put("categoryList", categoryList);

        return model;
    }

    @GetMapping("/blog/{blogId}")

    public HashMap<String, Object> getBlogById(@PathVariable("blogId") String blogId) {

        Blog blog = blogService.getOne(new QueryWrapper<Blog>().eq("bid", blogId));
        String userId = RequestHelper.getSessionUser().getUid();
        if (!blog.getAuthorId().equals(userId)) {
            KuangUtils.print("禁止非法编辑");
            return null;
        }


        List<BlogCategory> categoryList = blogCategoryService.list(null);
        HashMap<String, Object> res = new HashMap<>(2);
        res.put("blog", blog);
        res.put("categoryList", categoryList);

        return res;
    }

    @PostMapping("/blog/editor")
    public String editor(Blog blog) {
        Blog queryBlog = blogService.getOne(new QueryWrapper<Blog>().eq("bid", blog.getBid()));

        queryBlog.setTitle(blog.getTitle());
        queryBlog.setCategoryId(blog.getCategoryId());
        queryBlog.setContent(blog.getContent());
        queryBlog.setGmtUpdate(KuangUtils.getTime());

        blogService.updateById(queryBlog);

        return "redirect:/blog/read/" + blog.getBid();
    }

    // 删除问题
    @GetMapping("/blog/delete/{uid}/{bid}")
    public String delete(@PathVariable("uid") String uid,
                         @PathVariable("bid") String bid) {

        Blog blog = blogService.getOne(new QueryWrapper<Blog>().eq("bid", bid));

        if (!blog.getAuthorId().equals(uid)) {
            KuangUtils.print("禁止非法删除");
            return "redirect:/blog";
        }

        blogService.removeById(blog);
        // 重定向到列表页面
        return "redirect:/blog";
    }

    // 评论
    @PostMapping("/blog/comment/{bid}")
    public String comment(@PathVariable("bid") String bid, Comment comment) {
        // 存储评论
        comment.setCommentId(KuangUtils.getUuid());
        comment.setTopicCategory(1);
        comment.setGmtCreate(KuangUtils.getTime());
        commentService.save(comment);
        // 重定向到列表页面
        return "redirect:/blog/read/" + bid;
    }


}

