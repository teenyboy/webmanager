package com.wh.webmanager.controller;

import com.alibaba.fastjson.JSON;
import com.wh.webmanager.domain.ManagerMenu;
import com.wh.webmanager.domain.ServiceResult;
import com.wh.webmanager.domain.enums.YnEnum;
import com.wh.webmanager.service.ManagerMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger("IndexController.class");

    @Autowired
    private ManagerMenuService managerMenuService;

    @RequestMapping(value = "/")
    public String index(){
        return "default";
    }

    @ResponseBody
    @RequestMapping(value = "/queryManagerMenus")
    public ServiceResult queryManagerMenus(){
        ManagerMenu managerMenu = new ManagerMenu();
        managerMenu.setYn(YnEnum.Y.getValue());
        try {
            List<ManagerMenu> managerMenus = managerMenuService.queryManagerMenus(managerMenu);
            return new ServiceResult(true, JSON.toJSONString(managerMenus));
        }catch (Exception e){
            logger.error("主页查询栏目下拉失败",e);
            return new ServiceResult(false,"操作失败，请刷新");
        }
    }
}
