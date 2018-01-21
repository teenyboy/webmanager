package com.wh.webmanager.controller;

import com.wh.webmanager.domain.WebContent;
import com.wh.webmanager.domain.WebManager;
import com.wh.webmanager.domain.enums.YnEnum;
import com.wh.webmanager.service.WebContentService;
import com.wh.webmanager.service.WebManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/webmanager",method = {RequestMethod.GET,RequestMethod.POST})
public class WebManagerController extends BaseController {

    @Resource
    private WebManagerService webManagerService;
    @Resource
    private WebContentService webContentService;

    @RequestMapping(value = "/")
    public String index(Integer id,String name,Model view){
        view.addAttribute("menuName",name);
        view.addAttribute("menuId",id);
        return "/sys/webmanager/webmanager";
    }

    @ResponseBody
    @RequestMapping(value = "/queryWebs")
    public Map<String, Object> queryWebs(WebManager webManager) {
        webManager.setYn(YnEnum.Y.getValue());
        List<WebManager> webManagers = webManagerService.queryWebManagers(webManager);
        webManager.setRecordsTotal(webManagerService.queryWebManagerCount(webManager));
        return toDataTable(webManagers, webManager);
    }

    @RequestMapping(value = "/addOrUpdateView")
    public String addOrUpdateView(Integer id,Integer menuId,String menuName,Model view){
        if(id != null){
            if(id!=0){
                WebContent webContent = webContentService.queryWebContentByWeMId(id);
                view.addAttribute("webContent",webContent);
            }
        }else {
            return "error";
        }
        view.addAttribute("id",id);
        view.addAttribute("menuId",menuId);
        view.addAttribute("menuName",menuName);
        return "/sys/webmanager/addorupdateview";
    }
}
