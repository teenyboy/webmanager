package com.wh.mobile.controller;

import com.alibaba.fastjson.JSON;
import com.wh.mobile.service.MMenuService;
import com.wh.webmanager.controller.BaseController;
import com.wh.webmanager.domain.ManagerMenu;
import com.wh.webmanager.domain.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/m/menu",method = {RequestMethod.GET,RequestMethod.POST})
public class MMenuController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(MMenuController.class);

    @Autowired
    private MMenuService mMenuService;

    @ResponseBody
    @RequestMapping(value = "/queryMenus")
    public String queryMenus(){
        ServiceResult serviceResult = null;
        try {
            List<ManagerMenu> managerMenus = mMenuService.queryMenus();
            serviceResult = new ServiceResult(true, JSON.toJSONString(managerMenus));
        }catch (Exception e){
            logger.error("加载菜单错误...",e);
            serviceResult = new ServiceResult(false,"等待服务...");
        }
        return toResult(serviceResult);
    }
}
