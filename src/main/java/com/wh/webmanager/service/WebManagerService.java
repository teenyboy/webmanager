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

    /**
     * 新增消息
     * @param record
     * @return
     */
    int insertWebManager(WebManager record);

    /**
     * 查询最新消息
     * @return
     */
    public WebManager queryNewWebManager(WebManager webManager);

    /**
     * 更新消息
     * @param webManager
     * @return
     */
    int updateWebManager(WebManager webManager);

    /**
     * 查询最大grade
     * @return
     */
    Integer queryMaxGradeWebManager();
}
