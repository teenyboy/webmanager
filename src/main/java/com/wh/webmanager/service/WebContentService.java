package com.wh.webmanager.service;

import com.wh.webmanager.domain.WebContent;

public interface WebContentService {

    /**
     * 根据
     * @param webManagerId
     * @return
     */
    public WebContent queryWebContentByWeMId(Integer webManagerId);
}
