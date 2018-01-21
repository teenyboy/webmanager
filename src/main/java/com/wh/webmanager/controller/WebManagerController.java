package com.wh.webmanager.controller;

import com.wh.webmanager.domain.WebManager;
import com.wh.webmanager.domain.enums.YnEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/webmanager",method = {RequestMethod.GET,RequestMethod.POST})
public class WebManagerController extends BaseController {

    @RequestMapping(value = "/")
    public String index(Integer id,String name,Model view){
        view.addAttribute("webName",name);
        view.addAttribute("webManagerId",id);
        return "/sys/webmanager/webmanager";
    }

//    @ResponseBody
//    @RequestMapping(value = "/queryWebs")
//    public Map<String, Object> queryWebs(WebManager webManager) {
//        webManager.setYn(YnEnum.Y.getValue());
//        List<WebManager> webManagers = managerMenuService.queryManagerMenus(webManager);
//        webManager.setRecordsTotal(managerMenuService.queryManagerMenuCount(webManager));
//        return toDataTable(webManagers, webManager);
//    }

}
