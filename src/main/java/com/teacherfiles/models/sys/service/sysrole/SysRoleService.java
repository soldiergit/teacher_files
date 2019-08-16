package com.teacherfiles.models.sys.service.sysrole;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.SysRoleEntity;

import java.util.List;

/**
 * @author soldier
 * @title: RoleService
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface SysRoleService {

    /**
     * 添加
     * @param sysRoleEntity
     */
    public void save(SysRoleEntity sysRoleEntity);

    /**
     * 删除
     * @param sysRoleEntity
     */
    public void delete(SysRoleEntity sysRoleEntity);

    /**
     * 修改
     * @param sysRoleEntity
     */
    public void update(SysRoleEntity sysRoleEntity);

    /**
     * 查询
     * @param sysRoleEntity
     * @return
     */
    public SysRoleEntity findById(SysRoleEntity sysRoleEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<SysRoleEntity> pageBean);

    /**
     * 用于添加教师和修改教师角色时的复选框渲染
     * @return
     */
    public List<SysRoleEntity> findByOtherRole();

    /**
     * 用于系统管理员查看角色列表
     * @return
     */
    public List<SysRoleEntity> findBySysAdmin();
    
    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
