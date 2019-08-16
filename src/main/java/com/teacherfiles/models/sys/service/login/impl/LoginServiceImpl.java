package com.teacherfiles.models.sys.service.login.impl;

import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.dao.login.LoginDao;
import com.teacherfiles.models.sys.model.SysUserEntity;
import com.teacherfiles.models.sys.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author soldier
 * @title: LoginServiceImpl
 * @projectName teacher_files
 * @date 19-7-4下午11:29
 */
@Repository
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public R userLogin(SysUserEntity sysUserEntity) {
        return loginDao.userLogin(sysUserEntity);
    }

    @Override
    public R updatePassword(SysUserEntity sysUserEntity, String newPassword) {
        return loginDao.updatePassword(sysUserEntity, newPassword);
    }
}
