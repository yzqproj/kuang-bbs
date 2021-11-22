package com.kuang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.model.entity.Blog;
import com.kuang.model.entity.BlogCategory;
import com.kuang.service.BlogCategoryService;
import com.kuang.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class BlogCategoryController {

    @Resource
    BlogCategoryService blogCategoryService;
    @Resource
    BlogService blogService;

    @GetMapping("/blog/category/{bid}/{page}/{limit}")
    public HashMap<String,Object> blogPage(
            @PathVariable int bid,
            @PathVariable int page,
            @PathVariable int limit ) {

        if (page < 1) {
            page = 1;
        }
        HashMap<String, Object> model=new HashMap<>();
        // 查询这个分类下的所有问题，获取查询的数据信息
        Page<Blog> pageParam = new Page<>(page, limit);
        blogService.page(pageParam, new QueryWrapper<Blog>()
                .eq("category_id", bid).orderByDesc("gmt_create"));

        List<Blog> records = pageParam.getRecords();

        model.put("blogList", records);
        model.put("pageParam", pageParam);

        // 查询这个分类信息
        BlogCategory category = blogCategoryService.getById(bid);
        model.put("thisCategoryName", category.getCategory());

        // 全部分类信息
        List<BlogCategory> categoryList = blogCategoryService.list(null);
        model.put("categoryList", categoryList);

        return model;
    }
}

