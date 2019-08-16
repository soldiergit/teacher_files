package com.teacherfiles.models.sys.service.teacherItemCategory;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.TeacherItemCategoryEntity;

import java.util.List;

/**
 * @author soldier
 * @title: AcademicPaperService
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface TeacherItemCategoryService {

    /**
     * 添加
     * @param teacherItemCategoryEntity
     */
    public void save(TeacherItemCategoryEntity teacherItemCategoryEntity);

    /**
     * 删除
     * @param teacherItemCategoryEntity
     */
    public void delete(TeacherItemCategoryEntity teacherItemCategoryEntity);

    /**
     * 修改
     * @param teacherItemCategoryEntity
     */
    public void update(TeacherItemCategoryEntity teacherItemCategoryEntity);

    /**
     * 查询
     * @param teacherItemCategoryEntity
     * @return
     */
    public TeacherItemCategoryEntity findById(TeacherItemCategoryEntity teacherItemCategoryEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<TeacherItemCategoryEntity> pageBean);

    /**
     * 查询全部，用于下拉框渲染
     * @return
     */
    public List<TeacherItemCategoryEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
