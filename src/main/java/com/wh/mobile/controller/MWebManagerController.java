package com.wh.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/m/webmanager",method = {RequestMethod.GET,RequestMethod.POST})
public class MWebManagerController {

    @RequestMapping(value = "/toContent")
    public String toContent(Long id,Model view){
        return "";
    }
}
