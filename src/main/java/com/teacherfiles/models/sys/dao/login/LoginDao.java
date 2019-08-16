package com.teacherfiles.models.sys.dao.login;

import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.SysUserEntity;

/**
 * @author soldier
 * @title: LoginDao
 * @projectName teacher_files
 * @date 19-7-4下午11:27
 */
public interface LoginDao {

    /**
     * 用户登入
     * @param sysUserEntity
     * @return
     */
    R userLogin(SysUserEntity sysUserEntity);

    /**
     * 修改密码
     * @param sysUserEntity
     * @return
     */
    R updatePassword(SysUserEntity sysUserEntity, String newPassword);
}
