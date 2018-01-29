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
import org.springframework.util.StringUtils;
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
        view.addAttribute("webmName",name);
        view.addAttribute("webmId",id);
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
    public String addOrUpdateView(Long id,Integer webmId,String webmName,Model view){
        if(id != null){
            if(id!=0){
                WebContent webContent = webContentService.queryWebContentByWeMId(id);
                view.addAttribute("webContent",webContent);
            }
        }else {
            return "error";
        }
        view.addAttribute("updateId",id);
        view.addAttribute("webmId",webmId);
        view.addAttribute("webmName",webmName);
        return "/sys/webmanager/addorupdateview";
    }

    @ResponseBody
    @RequestMapping(value = "/addOrUpdateContent")
    public String addOrUpdateContent(WebContent webContent){
        ServiceResult serviceResult = null;
        try {
            webContent.setYn(YnEnum.Y.getValue());
            if(!StringUtils.isEmpty(webContent.getId())){
                webContent.setUpdatetime(new Date());
                webContentService.updateWebContent(webContent);
                serviceResult = new ServiceResult(true,"更新成功");
            }else{
                webContent.setCreatetime(new Date());
                webContentService.insertWebContent(webContent);
                serviceResult = new ServiceResult(false,"新增成功");
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
