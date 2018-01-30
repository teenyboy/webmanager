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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/menu", method = {RequestMethod.GET, RequestMethod.POST})
public class ManagerMenuController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger("ManagerMenuController.class");

    @Autowired
    private ManagerMenuService managerMenuService;

    @RequestMapping(value = "/")
    public String menu() {
        return "/sys/menu/menumanager";
    }

    @ResponseBody
    @RequestMapping(value = "/queryMenus")
    public Map<String, Object> queryMenus(ManagerMenu managerMenu) {
        managerMenu.setYn(YnEnum.Y.getValue());
        List<ManagerMenu> managerMenus = managerMenuService.queryManagerMenus(managerMenu);
        managerMenu.setRecordsTotal(managerMenuService.queryManagerMenuCount(managerMenu));
        return toDataTable(managerMenus, managerMenu);
    }

    @ResponseBody
    @RequestMapping(value = "/delMenus")
    public String delMenus(Long id) {
        ServiceResult serviceResult;
        try {
            ManagerMenu managerMenu = managerMenuService.queryMenuById(id);
            if(managerMenu == null){
                serviceResult =  new ServiceResult(false,"已删除，请刷新");
            }else{
                managerMenu.setUpdatetime(new Date());
                managerMenu.setYn(YnEnum.N.getValue());
                managerMenuService.updateByPrimaryKeySelective(managerMenu);
                serviceResult = new ServiceResult(true, "删除成功");
            }
        } catch (Exception e) {
            logger.error("删除模块失败", e);
            serviceResult = new ServiceResult(false, "删除失败");
        }
        return toResult(serviceResult);
    }

    @ResponseBody
    @RequestMapping("/queryMenuById")
    public String queryMenuById(Long id){
        ServiceResult serviceResult;
        ManagerMenu managerMenu = managerMenuService.queryMenuById(id);
        if(managerMenu == null){
            serviceResult = new ServiceResult(false, "查询失败");
        }else{
            serviceResult = new ServiceResult(true, JSON.toJSONString(managerMenu));
        }
        return toResult(serviceResult);
    }

    @ResponseBody
    @RequestMapping(value = "/addOrUpdateMenu")
    public String addOrUpdateMenu(ManagerMenu managerMenu, String opertionStyle) {
        ServiceResult serviceResult;
        try {
            if (!opertionStyle.equals("add") && !opertionStyle.equals("update")) {
                serviceResult = new ServiceResult(false, "操作失败");
                return toResult(serviceResult);
            }
            managerMenuService.dealGradeManagerMenu(managerMenu);
            managerMenu.setYn(YnEnum.Y.getValue());
            if (opertionStyle.equals("add")) {
                managerMenu.setCreatetime(new Date());
                managerMenuService.insertManagerMenus(managerMenu);
                serviceResult = new ServiceResult(true, "新增成功");
            } else {
                managerMenu.setUpdatetime(new Date());
                managerMenuService.updateByPrimaryKeySelective(managerMenu);
                serviceResult = new ServiceResult(true, "更新成功");
            }
        } catch (Exception e) {
            if (opertionStyle.equals("add")) {
                logger.error("新增模块失败", e);
                serviceResult = new ServiceResult(false, "新增失败");
            } else {
                logger.error("更新模块失败", e);
                serviceResult = new ServiceResult(false, "更新失败");
            }
        }
        return toResult(serviceResult);
    }
}
