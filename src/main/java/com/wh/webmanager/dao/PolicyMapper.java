package com.wh.webmanager.dao;

import com.wh.webmanager.domain.Policy;

public interface PolicyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Policy record);

    int insertSelective(Policy record);

    Policy selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Policy record);

    int updateByPrimaryKey(Policy record);
}