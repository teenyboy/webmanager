package com.wh.webmanager.dao;

import com.wh.webmanager.domain.ManagerMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ManagerMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManagerMenu record);

    int insertSelective(ManagerMenu record);

    ManagerMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerMenu record);

    int updateByPrimaryKey(ManagerMenu record);

    @Select("select max(grade) from managermenu where yn = 1")
    Integer queryManagerMenuMaxGrade();

    Integer queryManagerMenuCount(ManagerMenu managerMenu);

    List<ManagerMenu> queryManagerMenus(ManagerMenu managerMenu);

    @Select("select * from managermenu where grade >= #{grade} and yn = 1 order by grade asc")
    List<ManagerMenu> queryMoreGradeManagerMenus(Integer grade);

    void updateMoreGradeList(List<ManagerMenu> managerMenus);

    @Select("select * from managermenu where id = #{id} and yn = 1")
    ManagerMenu queryMenuById(Long id);
}