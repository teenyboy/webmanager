package com.wh.webmanager.dao;

import com.wh.webmanager.domain.WebUser;

public interface WebUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WebUser record);

    int insertSelective(WebUser record);

    WebUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WebUser record);

    int updateByPrimaryKey(WebUser record);
}