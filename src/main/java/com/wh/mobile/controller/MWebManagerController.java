package com.wh.mobile.controller;

import com.alibaba.fastjson.JSON;
import com.wh.webmanager.controller.BaseController;
import com.wh.webmanager.domain.ServiceResult;
import com.wh.webmanager.domain.WebManager;
import com.wh.webmanager.domain.enums.YnEnum;
import com.wh.webmanager.service.WebManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/m/webmanager",method = {RequestMethod.GET,RequestMethod.POST})
public class MWebManagerController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(MWebManagerController.class);

    @Resource
    private WebManagerService webManagerService;

    @RequestMapping(value = "/")
    public String index(Long id,Model view){
        view.addAttribute("menuid",id);
        return "mobile/mwebmanager";
    }


    @ResponseBody
    @RequestMapping(value = "/queryWebManager")
    public String queryWebManager(WebManager webManager){
        ServiceResult serviceResult = null;
        try {
            webManager.setYn(YnEnum.Y.getValue());
            List<WebManager> webManagers = webManagerService.queryWebManagers(webManager);
            serviceResult = new ServiceResult(true, JSON.toJSONString(webManagers));
        }catch (Exception e){
            logger.error("查询信息出错",e);
            serviceResult = new ServiceResult(false,"暂停服务");
        }
        return toResult(serviceResult);
    }

}
