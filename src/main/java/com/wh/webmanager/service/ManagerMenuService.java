package com.wh.webmanager.service;

import com.wh.webmanager.domain.ManagerMenu;

import java.util.List;

public interface ManagerMenuService {

    /**
     * 插入新增模块(未填写序号，则自动放到最后，填写序号，插到相应序号)
     * @param managerMenu
     */
    public void insertManagerMenus(ManagerMenu managerMenu);

    /**
     * 查询模块填充列表
     * @param managerMenu
     * @return
     */
    public List<ManagerMenu> queryManagerMenuDataTables(ManagerMenu managerMenu);

    /**
     * 查询模块总量
     * @param managerMenu
     * @return
     */
    public Integer queryManagerMenuCount(ManagerMenu managerMenu);
}
