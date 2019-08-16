package com.teacherfiles.models.sys.dao.teacher;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.TeacherEntity;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherDao
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface TeacherDao {

    /**
     * 添加
     * @param teacherEntity
     */
    public void save(TeacherEntity teacherEntity);

    /**
     * 删除
     * @param teacherEntity
     */
    public void delete(TeacherEntity teacherEntity);

    /**
     * 修改
     * @param teacherEntity
     */
    public void update(TeacherEntity teacherEntity);

    /**
     * 查询
     * @param teacherEntity
     * @return
     */
    public TeacherEntity findById(TeacherEntity teacherEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<TeacherEntity> pageBean);

    /**
     * 根据部门查询
     * @param deptId
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByDept(Integer deptId, String key, PageBean<TeacherEntity> pageBean);

    /**
     * 系统管理员查询部门管理员
     * @return
     */
    public PageBean findDeptAdmin(String key, PageBean<TeacherEntity> pageBean);

    /**
     * 查询全部，用于系统管理员下拉框渲染
     * @return
     */
    public List<TeacherEntity> findAll();

    /**
     * 查询全部，用于部门负责人和领导下拉框渲染
     * @return
     */
    public List<TeacherEntity> findAllByDept(Integer deptId);
    
    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
