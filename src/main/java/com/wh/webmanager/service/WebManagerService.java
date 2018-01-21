package com.wh.webmanager.service;

import com.wh.webmanager.domain.WebManager;

import java.util.List;

public interface WebManagerService {

    /**
     * 查询网站内容明细
     * @param webManager
     * @return
     */
    public List<WebManager> queryWebManagers(WebManager webManager);

    /**
     * 查询网站内容数量
     * @param webManager
     * @return
     */
    public Integer queryWebManagerCount(WebManager webManager);


}
