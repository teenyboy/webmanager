package com.wh.webmanager.service.impl;

import com.wh.webmanager.dao.ManagerMenuMapper;
import com.wh.webmanager.domain.ManagerMenu;
import com.wh.webmanager.service.ManagerMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerMenuServiceImpl implements ManagerMenuService{

    @Autowired
    ManagerMenuMapper managerMenuMapper;

    @Override
    public List<ManagerMenu> queryManagerMenus() {
        return managerMenuMapper.queryManagerMenus();
    }
}
