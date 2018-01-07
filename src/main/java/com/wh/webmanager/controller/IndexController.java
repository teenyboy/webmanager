package com.wh.webmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
public class IndexController {

    @RequestMapping(value = "/{name}")
    public String index(@PathVariable String name, Model model){
        model.addAttribute("name",name);
        return "default";
    }
}
