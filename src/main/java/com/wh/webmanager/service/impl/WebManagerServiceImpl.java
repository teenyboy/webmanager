package com.wh.webmanager.service.impl;

import com.wh.webmanager.dao.WebManagerMapper;
import com.wh.webmanager.domain.WebManager;
import com.wh.webmanager.service.WebManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WebManagerServiceImpl implements WebManagerService{

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
    public int insertWebManager(WebManager record) {
        return webManagerMapper.insert(record);
    }

    @Override
    public WebManager queryNewWebManager(WebManager webManager) {
        return webManagerMapper.queryNewWebManager(webManager);
    }

    @Override
    public int updateWebManager(WebManager webManager) {
        return webManagerMapper.updateByPrimaryKeySelective(webManager);
    }

    @Override
    public Integer queryMaxGradeWebManager() {
        return webManagerMapper.queryMaxGradeWebManager();
    }
}
