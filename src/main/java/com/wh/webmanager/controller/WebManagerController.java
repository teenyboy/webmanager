package com.wh.webmanager.controller;

import com.wh.webmanager.domain.ManagerMenu;
import com.wh.webmanager.domain.ServiceResult;
import com.wh.webmanager.domain.WebContent;
import com.wh.webmanager.domain.WebManager;
import com.wh.webmanager.domain.enums.YnEnum;
import com.wh.webmanager.service.ManagerMenuService;
import com.wh.webmanager.service.WebContentService;
import com.wh.webmanager.service.WebManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/webmanager",method = {RequestMethod.GET,RequestMethod.POST})
public class WebManagerController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WebManagerController.class);

    @Resource
    private WebManagerService webManagerService;
    @Resource
    private WebContentService webContentService;
    @Resource
    private ManagerMenuService managerMenuService;

    @RequestMapping(value = "/")
    public String index(Long id,Model view){
        ManagerMenu managerMenu = managerMenuService.queryMenuById(id);
        view.addAttribute("menuName",managerMenu.getName());
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
    public String addOrUpdateView(Long webmid,Integer menuId,Model view){
        if(webmid != null){
            if(webmid!=0){
                WebContent webContent = webContentService.queryWebContentByWebMId(webmid);
                view.addAttribute("content",webContent.getContent());
                view.addAttribute("webContentId",webContent.getId());
                WebManager webManager = webManagerService.queryWebManagerById(webmid);
                view.addAttribute("title",webManager.getTitle());
            }
        }else {
            return "error";
        }
        view.addAttribute("webmid",webmid);
        view.addAttribute("menuId",menuId);
        return "/sys/webmanager/addorupdateview";
    }

    @ResponseBody
    @RequestMapping(value = "/addOrUpdateContent")
    public String addOrUpdateContent(WebContent webContent,WebManager webManager,Long webContentId){
        ServiceResult serviceResult = null;
        try {
            webContent.setYn(YnEnum.Y.getValue());
            webManager.setYn(YnEnum.Y.getValue());
            if(webContent.getWebmid() == 0){
                serviceResult = webManagerService.addContent(webContent,webManager);
            }else{
                webContent.setId(webContentId);
                webManager.setId(webContent.getWebmid());
                serviceResult = webManagerService.updateContent(webContent,webManager);
            }
        }catch (Exception e){
            logger.error("增加/更新内容出错",e);
            serviceResult =  new ServiceResult(false,"增加/更新内容出错");
        }
        return toResult(serviceResult);
    }

    @ResponseBody
    @RequestMapping(value = "/delWebContent",method = {RequestMethod.GET,RequestMethod.POST})
    public String delWebContent(Long id){
        ServiceResult serviceResult = null;
        try {
            serviceResult = webManagerService.delContent(id);
        }catch (Exception e){
            logger.error("删除失败",e);
            serviceResult =  new ServiceResult(false,"删除失败");
        }
        return toResult(serviceResult);
    }

    @ResponseBody
    @RequestMapping(value = "/makeUp")
    public String makeUp(Long id){
        ServiceResult serviceResult = null;
        try {
            serviceResult = webManagerService.makeUp(id);
        }catch (Exception e){
            logger.error("删除失败",e);
            serviceResult =  new ServiceResult(false,"操作失败");
        }
        return toResult(serviceResult);
    }
}
