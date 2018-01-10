package com.wh.webmanager.controller;

import com.wh.webmanager.domain.ManagerMenu;
import com.wh.webmanager.service.ManagerMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
public class IndexController {

    @Autowired
    private ManagerMenuService managerMenuService;

    @RequestMapping(value = "/{name}")
    public String index(@PathVariable String name, Model model){

        List<ManagerMenu> managerMenus = managerMenuService.queryManagerMenus();
        model.addAttribute("name",name);
        model.addAttribute("managerMenus",managerMenus);
        return "default";
    }
}
