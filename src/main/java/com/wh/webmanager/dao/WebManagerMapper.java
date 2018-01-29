package com.wh.webmanager.dao;

import com.wh.webmanager.domain.WebManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WebManagerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WebManager record);

    int insertSelective(WebManager record);

    WebManager selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WebManager record);

    int updateByPrimaryKey(WebManager record);

    List<WebManager> queryWebManagers(WebManager webManager);

    Integer queryWebManagerCount(WebManager webManager);

    WebManager queryNewWebManager(WebManager webManager);

    @Select("select max(grade) from webmanager where yn = 1")
    Integer queryMaxGradeWebManager();
}