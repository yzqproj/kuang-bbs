package com.kuang.controller;


import com.kuang.mapper.DownloadMapper;
import com.kuang.model.entity.Download;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 遇见狂神说
 * @since 2020-07-08
 */
@RestController
@RequestMapping
@Schema(name = "下载")
public class DownloadController {

    @Resource
    DownloadMapper downloadMapper;

    @GetMapping({"/download"})
    public String download(Model model){
        List<Download> downloadList = downloadMapper.selectList(null);
        model.addAttribute("downloadList",downloadList);
        return "page/download";
    }

}

