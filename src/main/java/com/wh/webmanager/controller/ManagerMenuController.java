package com.wh.webmanager.controller;

import com.wh.webmanager.domain.ManagerMenu;
import com.wh.webmanager.domain.QueryParams;
import com.wh.webmanager.domain.ServiceResult;
import com.wh.webmanager.domain.enums.YnEnum;
import com.wh.webmanager.service.ManagerMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/menu",method = {RequestMethod.GET,RequestMethod.POST})
public class ManagerMenuController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger("ManagerMenuController.class");

    @Autowired
    private ManagerMenuService managerMenuService;

    @RequestMapping(value = "/")
    public String menu(){
        return "/sys/menu/menumanager";
    }

    @ResponseBody
    @RequestMapping(value = "/queryMenus")
    public Map<String, Object> queryMenus(ManagerMenu managerMenu){
        managerMenu.setYn(YnEnum.Y.getValue());
        List<ManagerMenu> managerMenus = managerMenuService.queryManagerMenuDataTables(managerMenu);
        managerMenu.setRecordsTotal(managerMenuService.queryManagerMenuCount(managerMenu));
        return toDataTable(managerMenus,managerMenu);
    }

    @ResponseBody
    @RequestMapping(value = "/newMenu")
    public String newMenu(ManagerMenu managerMenu){
        ServiceResult serviceResult = new ServiceResult(false);
        try {
            managerMenuService.insertManagerMenus(managerMenu);
            serviceResult.setResult(true);
            serviceResult.setMsg("新增成功");
        }catch (Exception e){
            logger.error("新增模块失败",e);
            serviceResult.setMsg("新增失败");
        }
        return toResult(serviceResult);
    }
}
