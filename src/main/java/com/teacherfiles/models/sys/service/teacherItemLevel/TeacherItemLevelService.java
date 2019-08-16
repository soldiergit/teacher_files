package com.teacherfiles.models.sys.service.teacherItemLevel;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.TeacherItemLevelEntity;

import java.util.List;

/**
 * @author soldier
 * @title: AcademicPaperService
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface TeacherItemLevelService {

    /**
     * 添加
     * @param teacherItemLevelEntity
     */
    public void save(TeacherItemLevelEntity teacherItemLevelEntity);

    /**
     * 删除
     * @param teacherItemLevelEntity
     */
    public void delete(TeacherItemLevelEntity teacherItemLevelEntity);

    /**
     * 修改
     * @param teacherItemLevelEntity
     */
    public void update(TeacherItemLevelEntity teacherItemLevelEntity);

    /**
     * 查询
     * @param teacherItemLevelEntity
     * @return
     */
    public TeacherItemLevelEntity findById(TeacherItemLevelEntity teacherItemLevelEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<TeacherItemLevelEntity> pageBean);

    /**
     * 查询全部，用于下拉框渲染
     * @return
     */
    public List<TeacherItemLevelEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
