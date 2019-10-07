package com.teacherfiles.models.sys.dao.teacherItemAnnex;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.TeacherItemAnnexEntity;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherItemDao
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface TeacherItemAnnexDao {

    /**
     * 添加
     * @param teacherItemAnnexEntity
     */
    public void save(TeacherItemAnnexEntity teacherItemAnnexEntity);

    /**
     * 删除
     * @param teacherItemAnnexEntity
     */
    public void delete(TeacherItemAnnexEntity teacherItemAnnexEntity);

    /**
     * 修改
     * @param teacherItemAnnexEntity
     */
    public void update(TeacherItemAnnexEntity teacherItemAnnexEntity);

    /**
     * 根据教师项目查询所有附件
     * @param itemId
     */
    public List<TeacherItemAnnexEntity> findByTeacherItemId(Integer itemId);

    /**
     * 查询
     * @param teacherItemAnnexEntity
     */
    public TeacherItemAnnexEntity findById(TeacherItemAnnexEntity teacherItemAnnexEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     */
    public PageBean findByPage(String key, PageBean<TeacherItemAnnexEntity> pageBean);


    /**
     * 查询
     * @return
     */
    public List<TeacherItemAnnexEntity> findAll();
    
    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
