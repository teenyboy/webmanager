package com.wh.webmanager.service.impl;

import com.wh.webmanager.dao.ManagerMenuMapper;
import com.wh.webmanager.domain.ManagerMenu;
import com.wh.webmanager.domain.enums.YnEnum;
import com.wh.webmanager.service.ManagerMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class ManagerMenuServiceImpl implements ManagerMenuService {

    @Autowired
    ManagerMenuMapper managerMenuMapper;

    @Override
    public void insertManagerMenus(ManagerMenu managerMenu) {
        managerMenuMapper.insert(managerMenu);
    }


    @Override
    public List<ManagerMenu> queryManagerMenus(ManagerMenu managerMenu) {
        return managerMenuMapper.queryManagerMenus(managerMenu);
    }

    @Override
    public Integer queryManagerMenuCount(ManagerMenu managerMenu) {
        return managerMenuMapper.queryManagerMenuCount(managerMenu);
    }

    @Override
    public Integer deleteByPrimaryKey(Long id) {
        return managerMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void dealGradeManagerMenu(ManagerMenu managerMenu) {

        /**
         * 未填写序号
         */
        if(managerMenu.getGrade() == null){
            Integer maxGrade = managerMenuMapper.queryMaxGrade();
            if(maxGrade == null){
                maxGrade = 0;
            }
            managerMenu.setGrade(maxGrade+1);
            return;
        }

        Integer grade = managerMenu.getGrade();
        List<ManagerMenu> managerMenus = managerMenuMapper.queryMoreGradeManagerMenus(grade);

        for (int i = 0; i < managerMenus.size(); i++) {
            if (managerMenus.get(i).getGrade().equals(grade + i)) {
                managerMenus.get(i).setGrade(managerMenus.get(i).getGrade() + 1);
                managerMenus.get(i).setUpdatetime(new Date());
                managerMenus.get(i).setYn(YnEnum.Y.getValue());
                managerMenuMapper.updateByPrimaryKey(managerMenus.get(i));
            } else {
                break;
            }
        }
    }

    @Override
    public Integer updateByPrimaryKeySelective(ManagerMenu record) {
        return managerMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ManagerMenu queryMenuById(Long id) {
        return managerMenuMapper.queryMenuById(id);
    }


}
