package com.wh.webmanager.service;

import com.wh.webmanager.domain.ServiceResult;
import com.wh.webmanager.domain.WebContent;
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
     * 插入内容
     * @param webContent
     * @param webManager
     * @return
     */
    ServiceResult addContent(WebContent webContent, WebManager webManager);

    /**
     * 插入内容
     * @param webContent
     * @param webManager
     * @return
     */
    ServiceResult updateContent(WebContent webContent, WebManager webManager);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    WebManager queryWebManagerById(Long id);

    /**
     * 删除内容
     * @param id
     * @return
     */
    ServiceResult delContent(Long id);

    /**
     * 置顶内容
     * @param id
     * @return
     */
    ServiceResult makeUp(Long id);
}
