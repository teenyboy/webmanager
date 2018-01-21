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
}
