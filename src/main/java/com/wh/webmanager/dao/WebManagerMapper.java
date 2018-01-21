package com.wh.webmanager.dao;

import com.wh.webmanager.domain.WebManager;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebManagerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WebManager record);

    int insertSelective(WebManager record);

    WebManager selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WebManager record);

    int updateByPrimaryKey(WebManager record);
}