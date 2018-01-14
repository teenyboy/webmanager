package com.wh.webmanager.service.impl;

import com.wh.webmanager.dao.ManagerMenuMapper;
import com.wh.webmanager.domain.ManagerMenu;
import com.wh.webmanager.domain.enums.YnEnum;
import com.wh.webmanager.service.ManagerMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ManagerMenuServiceImpl implements ManagerMenuService {

    @Autowired
    ManagerMenuMapper managerMenuMapper;

    @Override
    public void insertManagerMenus(ManagerMenu managerMenu) {

        Integer num = managerMenuMapper.queryManagerMenuMaxGrade();
        managerMenu.setYn(YnEnum.Y.getValue());
        Integer grade = managerMenu.getGrade();
        if (StringUtils.isEmpty(grade) ) {
            managerMenu.setGrade(num + 1);
            managerMenuMapper.insert(managerMenu);
        } else {
            managerMenuMapper.insert(managerMenu);
            List<ManagerMenu> managerMenus = managerMenuMapper.queryMoreGradeManagerMenus(grade);
            for(int i = 0;i<managerMenus.size();i++){
                if(managerMenu.getGrade().equals(grade+i)){
                    managerMenu.setGrade(managerMenu.getGrade()+1);
                    managerMenuMapper.updateByPrimaryKey(managerMenu);
                }else {
                    break;
                }
            }
        }
    }

    @Override
    public List<ManagerMenu> queryManagerMenuDataTables(ManagerMenu managerMenu) {
        return managerMenuMapper.queryManagerMenus(managerMenu);
    }

    @Override
    public Integer queryManagerMenuCount(ManagerMenu managerMenu) {
        return managerMenuMapper.queryManagerMenuCount(managerMenu);
    }


}
