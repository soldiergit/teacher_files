package com.teacherfiles.models.sys.dao.dept;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.DeptEntity;

import java.util.List;

/**
 * @author soldier
 * @title: DeptDao
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface DeptDao {

    /**
     * 添加
     * @param deptEntity
     */
    public void save(DeptEntity deptEntity);

    /**
     * 删除
     * @param deptEntity
     */
    public void delete(DeptEntity deptEntity);

    /**
     * 修改
     * @param deptEntity
     */
    public void update(DeptEntity deptEntity);

    /**
     * 查询
     * @param deptEntity
     * @return
     */
    public DeptEntity findById(DeptEntity deptEntity);

    /**
     * 查询--系统管理员
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<DeptEntity> pageBean);

    /**
     * 部门管理员及领导
     * @param deptId
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByDept(Integer deptId, String key, PageBean<DeptEntity> pageBean);

    /**
     * 查询全部一级部门，用于下拉框渲染
     * @return
     */
    public List<DeptEntity> findAll();

    /**
     * 根据一级部门查询全部从属部门，用于下拉框渲染
     * @return
     */
    public List<DeptEntity> findSubordinate(Integer parentId);
    
    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
