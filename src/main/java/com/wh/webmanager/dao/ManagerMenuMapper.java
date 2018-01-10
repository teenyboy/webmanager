package com.wh.webmanager.dao;

import com.wh.webmanager.domain.ManagerMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ManagerMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManagerMenu record);

    int insertSelective(ManagerMenu record);

    ManagerMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerMenu record);

    int updateByPrimaryKey(ManagerMenu record);

    @Select("select * from managermenu")
    List<ManagerMenu> queryManagerMenus();
}