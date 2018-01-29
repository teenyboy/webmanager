package com.wh.webmanager.service.impl;

import com.wh.webmanager.dao.WebContentMapper;
import com.wh.webmanager.domain.WebContent;
import com.wh.webmanager.service.WebContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WebContentServiceImpl implements WebContentService {

    @Resource
    private WebContentMapper webContentMapper;

    @Override
    public WebContent queryWebContentByWeMId(Long webManagerId) {
        return webContentMapper.queryWebContentByWeMId(webManagerId);
    }

    @Override
    public WebContent queryWebContentByid(Long id) {
        return webContentMapper.queryWebContentById(id);
    }

    public int insertWebContent(WebContent webContent){
        return webContentMapper.insert(webContent);
    }

    public int updateWebContent(WebContent webContent){
        return webContentMapper.updateByPrimaryKeySelective(webContent);
    }
}
