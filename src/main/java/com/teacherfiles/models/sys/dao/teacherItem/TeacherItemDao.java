package com.teacherfiles.models.sys.dao.teacherItem;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.TeacherItemEntity;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherItemDao
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface TeacherItemDao {

    /**
     * 添加
     * @param teacherItemEntity
     */
    public void save(TeacherItemEntity teacherItemEntity);

    /**
     * 删除
     * @param teacherItemEntity
     */
    public void delete(TeacherItemEntity teacherItemEntity);

    /**
     * 修改
     * @param teacherItemEntity
     */
    public void update(TeacherItemEntity teacherItemEntity);

    /**
     * 查询
     * @param teacherItemEntity
     * @return
     */
    public TeacherItemEntity findById(TeacherItemEntity teacherItemEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<TeacherItemEntity> pageBean);

    /**
     * 科研项目负责人查询自己负责的项目
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPerson(Integer itemPersonId, String key, PageBean<TeacherItemEntity> pageBean);

    /**
     * 项目成员查询自己加入的项目
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByMember(String memberId, String key, PageBean<TeacherItemEntity> pageBean);

    /**
     * 部门领导查询本部门所有的科研项目
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByDept(Integer deptId, String key, PageBean<TeacherItemEntity> pageBean);

    /**
     * 批量导出
     * @param deptId
     * @param Ids
     * @param itemPersonId
     * @return
     */
    public List<TeacherItemEntity> findByExport(Integer deptId, String[] Ids, Integer itemPersonId);

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
