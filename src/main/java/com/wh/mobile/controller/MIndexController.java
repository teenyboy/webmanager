package com.wh.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/m/",method = {RequestMethod.GET,RequestMethod.POST})
public class MIndexController {

    @RequestMapping(value = "/")
    public String index(){
        return "mobile/mindex";
    }


}
