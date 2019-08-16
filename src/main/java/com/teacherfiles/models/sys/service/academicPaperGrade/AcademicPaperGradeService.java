package com.teacherfiles.models.sys.service.academicPaperGrade;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.AcademicPaperGradeEntity;

import java.util.List;

/**
 * @author soldier
 * @title: AcademicPaperService
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface AcademicPaperGradeService {

    /**
     * 添加
     * @param academicPaperGradeEntity
     */
    public void save(AcademicPaperGradeEntity academicPaperGradeEntity);

    /**
     * 删除
     * @param academicPaperGradeEntity
     */
    public void delete(AcademicPaperGradeEntity academicPaperGradeEntity);

    /**
     * 修改
     * @param academicPaperGradeEntity
     */
    public void update(AcademicPaperGradeEntity academicPaperGradeEntity);

    /**
     * 查询
     * @param academicPaperGradeEntity
     * @return
     */
    public AcademicPaperGradeEntity findById(AcademicPaperGradeEntity academicPaperGradeEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<AcademicPaperGradeEntity> pageBean);

    /**
     * 查询全部，用于下拉框渲染
     * @return
     */
    public List<AcademicPaperGradeEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
