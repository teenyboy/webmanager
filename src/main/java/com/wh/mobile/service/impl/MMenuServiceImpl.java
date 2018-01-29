package com.wh.mobile.service.impl;

import com.wh.mobile.service.MMenuService;
import com.wh.webmanager.dao.ManagerMenuMapper;
import com.wh.webmanager.domain.ManagerMenu;
import com.wh.webmanager.domain.enums.YnEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MMenuServiceImpl implements MMenuService {

    @Autowired
    private ManagerMenuMapper managerMenuMapper;

    @Override
    public List<ManagerMenu> queryMenus() {
        ManagerMenu managerMenu = new ManagerMenu();
        managerMenu.setYn(YnEnum.Y.getValue());
        return managerMenuMapper.queryManagerMenus(managerMenu);
    }
}
