package com.wh.webmanager.service;

import com.wh.webmanager.domain.ManagerMenu;
import com.wh.webmanager.domain.ServiceResult;

import java.util.List;

public interface ManagerMenuService {

    /**
     * 插入新增模块(未填写序号，则自动放到最后，填写序号，插到相应序号)
     * @param managerMenu
     */
    public void insertManagerMenus(ManagerMenu managerMenu);

    /**
     * 更新模块
     * @param record
     * @return
     */
    public Integer updateByPrimaryKeySelective(ManagerMenu record);

    /**
     * 根据id查询栏目
     * @param id
     * @return
     */
    public ManagerMenu queryMenuById(Long id);

    /**
     * 查询模块填充列表
     * @param managerMenu
     * @return
     */
    public List<ManagerMenu> queryManagerMenus(ManagerMenu managerMenu);

    /**
     * 查询模块总量
     * @param managerMenu
     * @return
     */
    public Integer queryManagerMenuCount(ManagerMenu managerMenu);

    /**
     * 删除模块
     * @param id
     * @return
     */
    public Integer deleteByPrimaryKey(Long id);


    /**
     * 插入更新时处理菜单栏顺序
     * @param managerMenu
     */
    public void dealGradeManagerMenu(ManagerMenu managerMenu);
}
