package com.teacherfiles.models.sys.service.sysmenuChildren;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.SysMenuChildrenEntity;

import java.util.List;

/**
 * @author soldier
 * @title: MenuService
 * @projectName teacher_files
 * @date 19-7-5上午10:50
 */
public interface MenuChildrenService {

    /**
     * 添加
     */
    public void save(SysMenuChildrenEntity sysMenuChildrenEntity);

    /**
     * 删除
     */
    public void delete(SysMenuChildrenEntity sysMenuChildrenEntity);

    /**
     * 修改
     */
    public void update(SysMenuChildrenEntity sysMenuChildrenEntity);

    /**
     * 查询
     */
    public SysMenuChildrenEntity findById(SysMenuChildrenEntity sysMenuChildrenEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<SysMenuChildrenEntity> pageBean);

    /**
     * 查询全部
     * @return
     */
    public List<SysMenuChildrenEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
