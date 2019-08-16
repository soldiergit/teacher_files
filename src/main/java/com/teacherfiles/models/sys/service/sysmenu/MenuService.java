package com.teacherfiles.models.sys.service.sysmenu;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.SysMenuEntity;

import java.util.List;

/**
 * @author soldier
 * @title: MenuService
 * @projectName teacher_files
 * @date 19-7-5上午10:50
 */
public interface MenuService {

    /**
     * 添加
     */
    public void save(SysMenuEntity sysMenuEntity);

    /**
     * 删除
     */
    public void delete(SysMenuEntity sysMenuEntity);

    /**
     * 修改
     */
    public void update(SysMenuEntity sysMenuEntity);

    /**
     * 查询
     */
    public SysMenuEntity findById(SysMenuEntity sysMenuEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<SysMenuEntity> pageBean);

    /**
     * 查询全部
     * @return
     */
    public List<SysMenuEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

    /**
     * 用户登陆获取菜单
     * @param roleIds
     * @return
     */
    List<SysMenuEntity> getMenuByRoleType(String[] roleIds);
}
