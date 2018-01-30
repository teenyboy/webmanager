package com.wh.mobile.controller;

import com.wh.webmanager.domain.WebContent;
import com.wh.webmanager.domain.WebManager;
import com.wh.webmanager.service.WebContentService;
import com.wh.webmanager.service.WebManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/m/webcontent",method = {RequestMethod.GET,RequestMethod.POST})
public class MWebContentController {

    @Resource
    private WebContentService webContentService;
    @Resource
    private WebManagerService webManagerService;

    @RequestMapping("/")
    public String index(Long webMid,Model view){
        WebContent webContent = webContentService.queryWebContentByWebMId(webMid);
        WebManager webManager = webManagerService.queryWebManagerById(webMid);
        view.addAttribute("title",webManager.getTitle());
        view.addAttribute("content",webContent.getContent());
        return "mobile/mwebcontent";
    }
}
