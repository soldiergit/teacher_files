package com.teacherfiles.models.sys.service.sysrole.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.sysrole.RoleDao;
import com.teacherfiles.models.sys.model.SysRoleEntity;
import com.teacherfiles.models.sys.service.sysrole.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: RoleService
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void save(SysRoleEntity sysRoleEntity) {
        roleDao.save(sysRoleEntity);
    }

    @Override
    public void delete(SysRoleEntity sysRoleEntity) {
        roleDao.delete(sysRoleEntity);
    }

    @Override
    public void update(SysRoleEntity sysRoleEntity) {
        roleDao.update(sysRoleEntity);
    }

    @Override
    public SysRoleEntity findById(SysRoleEntity sysRoleEntity) {
        return roleDao.findById(sysRoleEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<SysRoleEntity> pageBean) {
        return roleDao.findByPage(key, pageBean);
    }

    @Override
    public List<SysRoleEntity> findByOtherRole() {
        return roleDao.findByOtherRole();
    }

    @Override
    public List<SysRoleEntity> findBySysAdmin() {
        return roleDao.findBySysAdmin();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        roleDao.deleteBatch(Ids);
    }
}
