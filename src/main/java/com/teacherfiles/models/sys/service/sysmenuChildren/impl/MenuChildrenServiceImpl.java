package com.teacherfiles.models.sys.service.sysmenuChildren.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.sysmenuChildren.MenuChildrenDao;
import com.teacherfiles.models.sys.model.SysMenuChildrenEntity;
import com.teacherfiles.models.sys.service.sysmenuChildren.MenuChildrenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: MenuServiceImpl
 * @projectName teacher_files
 * @date 19-7-5上午10:57
 */
@Service
public class MenuChildrenServiceImpl implements MenuChildrenService {

    @Autowired
    private MenuChildrenDao menuChildrenDao;

    @Override
    public void save(SysMenuChildrenEntity sysMenuChildrenEntity) {
        menuChildrenDao.save(sysMenuChildrenEntity);
    }

    @Override
    public void delete(SysMenuChildrenEntity sysMenuChildrenEntity) {
        menuChildrenDao.delete(sysMenuChildrenEntity);
    }

    @Override
    public void update(SysMenuChildrenEntity sysMenuChildrenEntity) {
        menuChildrenDao.update(sysMenuChildrenEntity);
    }

    @Override
    public SysMenuChildrenEntity findById(SysMenuChildrenEntity sysMenuChildrenEntity) {
        return menuChildrenDao.findById(sysMenuChildrenEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<SysMenuChildrenEntity> pageBean) {
        return menuChildrenDao.findByPage(key, pageBean);
    }

    @Override
    public List<SysMenuChildrenEntity> findAll() {
        return menuChildrenDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        menuChildrenDao.deleteBatch(Ids);
    }
}
