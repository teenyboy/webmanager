package com.wh.webmanager.service.impl;

import com.wh.webmanager.dao.WebManagerMapper;
import com.wh.webmanager.domain.ServiceResult;
import com.wh.webmanager.domain.WebContent;
import com.wh.webmanager.domain.WebManager;
import com.wh.webmanager.domain.enums.YnEnum;
import com.wh.webmanager.service.WebContentService;
import com.wh.webmanager.service.WebManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class WebManagerServiceImpl implements WebManagerService{

    @Resource
    private WebContentService webContentService;
    @Resource
    private WebManagerMapper webManagerMapper;

    @Override
    public List<WebManager> queryWebManagers(WebManager webManager) {
        return webManagerMapper.queryWebManagers(webManager);
    }

    @Override
    public Integer queryWebManagerCount(WebManager webManager) {
        return webManagerMapper.queryWebManagerCount(webManager);
    }

    @Override
    @Transactional
    public ServiceResult addContent(WebContent webContent, WebManager webManager) {
        ServiceResult serviceResult = null;

        Integer maxGrade = webManagerMapper.queryMaxGradeWebManager();
        if(maxGrade == null){
            maxGrade = 0;
        }
        webManager.setGrade(maxGrade+1);
        webManager.setCreatetime(new Date());
        int result = webManagerMapper.insert(webManager);
        if(result == 1){
            WebManager queryWebManager = new WebManager();
            queryWebManager.setYn(YnEnum.Y.getValue());
            queryWebManager.setGrade(webManager.getGrade());
            WebManager newWebManager = webManagerMapper.queryNewWebManager(queryWebManager);//查询最新插入的信息
            webContent.setWebmid(newWebManager.getId());
            webContent.setCreatetime(new Date());
            int webContentResult = webContentService.insertWebContent(webContent);
            if(webContentResult == 1){
                serviceResult = new ServiceResult(true,"新增文本成功");
            }else{
                serviceResult = new ServiceResult(false,"新增文本失败");
            }
        }else {
            serviceResult = new ServiceResult(false,"新增信息失败");
        }
        return serviceResult;
    }

    @Override
    @Transactional
    public ServiceResult updateContent(WebContent webContent, WebManager webManager) {
        ServiceResult serviceResult = null;
        webManager.setUpdatetime(new Date());
        int result = webManagerMapper.updateByPrimaryKeySelective(webManager);
        if(result == 1){
            webContent.setUpdatetime(new Date());
            int webContentResult = webContentService.updateWebContent(webContent);
            if(webContentResult == 1){
                serviceResult = new ServiceResult(true,"更新web文本成功");
            }else {
                serviceResult = new ServiceResult(false,"更新web文本失败");
            }
        }else {
            serviceResult = new ServiceResult(false,"更新web信息失败");
        }
        return serviceResult;
    }

    @Override
    public WebManager queryWebManagerById(Long id) {
        return  webManagerMapper.queryWebManagerById(id);
    }

    @Override
    @Transactional
    public ServiceResult delContent(Long id) {
        ServiceResult serviceResult = null;
        WebManager webManager = webManagerMapper.queryWebManagerById(id);
        if(webManager!= null){
            WebContent webContent = webContentService.queryWebContentByWebMId(webManager.getId());
            if(webContent!= null){
                webContent.setYn(YnEnum.N.getValue());
                webContent.setUpdatetime(new Date());
                webContentService.updateWebContent(webContent);
            }
            webManager.setYn(YnEnum.N.getValue());
            webManager.setUpdatetime(new Date());
            webManagerMapper.updateByPrimaryKeySelective(webManager);
            serviceResult =  new ServiceResult(true,"删除成功");
        }else{
            serviceResult =  new ServiceResult(false,"已删除，刷新页面");
        }
        return serviceResult;
    }

    @Override
    @Transactional
    public ServiceResult makeUp(Long id) {

        int startIndex = 1;
        List<WebManager> webManagers = webManagerMapper.queryMoreWebManager();
        for(int i = 0;i<webManagers.size();i++){
            if(webManagers.get(i).getGrade() == startIndex && !webManagers.get(i).getId().equals(id)){
                webManagers.get(i).setGrade(startIndex+1);
                webManagerMapper.updateByPrimaryKeySelective(webManagers.get(i));
                startIndex++;
            }else {
                break;
            }
        }

        WebManager webManager = new WebManager();
        webManager.setId(id);
        webManager.setGrade(1);
        webManagerMapper.updateByPrimaryKeySelective(webManager);

        return new ServiceResult(true,"置顶成功");
    }
}
