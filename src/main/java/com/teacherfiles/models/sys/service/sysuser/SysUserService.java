package com.teacherfiles.models.sys.service.sysuser;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.SysUserEntity;

/**
 * @author soldier
 * @title: UserService
 * @projectName teacher_files
 * @date 19-7-4下午11:27
 */
public interface SysUserService {
    /**
     * 添加
     * @param sysUserEntity
     */
    public void saveUser(SysUserEntity sysUserEntity);

    /**
     * 删除
     * @param sysUserEntity
     */
    public void deleteUser(SysUserEntity sysUserEntity);

    /**
     * 修改
     * @param sysUserEntity
     */
    public void updateUser(SysUserEntity sysUserEntity);

    /**
     * 查询
     * @param sysUserEntity
     * @return
     */
    public SysUserEntity findOneUser(SysUserEntity sysUserEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findAllUser(String key, PageBean<SysUserEntity> pageBean);
    /**
     * 批量删除
     * @param userIds
     */
    public void deleteAllUser(String[] userIds);
}
