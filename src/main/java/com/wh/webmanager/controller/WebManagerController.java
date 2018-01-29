package com.wh.webmanager.controller;

import com.wh.webmanager.domain.ServiceResult;
import com.wh.webmanager.domain.WebContent;
import com.wh.webmanager.domain.WebManager;
import com.wh.webmanager.domain.enums.YnEnum;
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
    public String addOrUpdateView(Long webmid,Integer menuId,Model view){
        if(webmid != null){
            if(webmid!=0){
                WebContent webContent = webContentService.queryWebContentByWebMId(webmid);
                view.addAttribute("webContent",webContent);
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
    public String addOrUpdateContent(WebContent webContent,WebManager webManager){
        ServiceResult serviceResult = null;
        try {
            webContent.setYn(YnEnum.Y.getValue());
            webManager.setYn(YnEnum.Y.getValue());

            if(webContent.getWebmid() == 0){
                Integer maxGrade = webManagerService.queryMaxGradeWebManager();
                if(maxGrade == null){
                    maxGrade = 0;
                }
                webManager.setGrade(maxGrade+1);
                webManager.setCreatetime(new Date());
                int result = webManagerService.insertWebManager(webManager);
                if(result == 1){
                    WebManager queryWebManager = new WebManager();
                    queryWebManager.setYn(YnEnum.Y.getValue());
                    queryWebManager.setGrade(webManager.getGrade());
                    WebManager newWebManager = webManagerService.queryNewWebManager(queryWebManager);
                    webContent.setWebmid(newWebManager.getId());
                    webContent.setCreatetime(new Date());
                    int webContentResult = webContentService.insertWebContent(webContent);
                    if(webContentResult == 1){
                        serviceResult = new ServiceResult(true,"新增web文本成功");
                    }else{
                        serviceResult = new ServiceResult(false,"新增web文本失败");
                    }
                }else {
                    serviceResult = new ServiceResult(false,"新增web信息失败");
                }
            }else{

                int result = webManagerService.updateWebManager(webManager);
                if(result == 1){
                    int webContentResult = webContentService.updateWebContent(webContent);
                    if(webContentResult == 1){
                        serviceResult = new ServiceResult(true,"更新web文本成功");
                    }else {
                        serviceResult = new ServiceResult(false,"更新web文本失败");
                    }
                }else {
                    serviceResult = new ServiceResult(false,"更新web信息失败");
                }
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
            WebContent webContent = webContentService.queryWebContentByid(id);
            if(webContent!= null){
                webContent.setYn(YnEnum.N.getValue());
                webContent.setUpdatetime(new Date());
                webContentService.updateWebContent(webContent);
                serviceResult =  new ServiceResult(true,"删除成功");
            }
            serviceResult =  new ServiceResult(false,"已删除，刷新页面");
        }catch (Exception e){
            logger.error("删除失败",e);
            serviceResult =  new ServiceResult(false,"删除失败");
        }
        return toResult(serviceResult);
    }
}
