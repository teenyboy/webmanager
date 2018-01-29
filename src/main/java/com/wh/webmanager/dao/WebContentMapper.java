package com.wh.webmanager.dao;

import com.wh.webmanager.domain.WebContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WebContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WebContent record);

    int insertSelective(WebContent record);

    WebContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WebContent record);

    int updateByPrimaryKey(WebContent record);

    @Select("select * from webcontent where yn = 1 and webmid = #{webMId}")
    WebContent queryWebContentByWeMId(Long webMId);

    @Select("select * from webcontent where yn = 1 and id = #{id}")
    WebContent queryWebContentById(Long id);
}