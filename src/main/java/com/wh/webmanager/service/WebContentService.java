package com.wh.webmanager.service;

import com.wh.webmanager.domain.WebContent;

public interface WebContentService {

    /**
     * 根据菜单id查询web内容
     * @param webManagerId
     * @return
     */
    public WebContent queryWebContentByWeMId(Long webManagerId);

    /**
     * 根据内容id查询web内容
     * @return
     */
    public WebContent queryWebContentByid(Long id);

    /**
     * 增加web内容
     * @param webContent
     * @return
     */
    public int insertWebContent(WebContent webContent);

    /**
     * 更新web内容
     * @param webContent
     * @return
     */
    public int updateWebContent(WebContent webContent);
}
