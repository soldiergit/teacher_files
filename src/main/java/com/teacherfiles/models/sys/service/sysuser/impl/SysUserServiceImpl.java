package com.teacherfiles.models.sys.service.sysuser.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.sysuser.UserDao;
import com.teacherfiles.models.sys.model.SysUserEntity;
import com.teacherfiles.models.sys.service.sysuser.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author soldier
 * @title: UserServiceImpl
 * @projectName teacher_files
 * @date 19-7-4下午11:27
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private UserDao userDao;

    /**
     * 添加用户
     * @param sysUserEntity
     */
    @Override
    public void saveUser(SysUserEntity sysUserEntity) {
        userDao.addUser(sysUserEntity);
    }

    /**
     * 删除用户
     * @param sysUserEntity
     */
    @Override
    public void deleteUser(SysUserEntity sysUserEntity) {
        userDao.deleteUser(sysUserEntity);
    }

    /**
     * 更新用户
     * @param sysUserEntity
     */
    @Override
    public void updateUser(SysUserEntity sysUserEntity) {
        userDao.updateUser(sysUserEntity);
    }

    /**
     * 选择一个
     * @param sysUserEntity
     * @return
     */
    @Override
    public SysUserEntity findOneUser(SysUserEntity sysUserEntity) {
        return userDao.findOneUser(sysUserEntity);
    }

    /**
     * 分页查询
     * @param key
     * @param pageBean
     * @return
     */
    @Override
    public PageBean findAllUser(String key, PageBean<SysUserEntity> pageBean) {
        return userDao.findAllUser(key,pageBean);
    }

    /**
     * 批量删除
     * @param userIds
     */
    @Override
    public void deleteAllUser(String[] userIds) {
        userDao.deleteAllUser(userIds);
    }
}
