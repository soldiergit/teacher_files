package com.teacherfiles.models.sys.service.academicPaper;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.AcademicPaperEntity;

/**
 * @author soldier
 * @title: AcademicPaperService
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface AcademicPaperService {

    /**
     * 添加
     * @param academicPaperEntity
     */
    public void save(AcademicPaperEntity academicPaperEntity);

    /**
     * 删除
     * @param academicPaperEntity
     */
    public void delete(AcademicPaperEntity academicPaperEntity);

    /**
     * 修改
     * @param academicPaperEntity
     */
    public void update(AcademicPaperEntity academicPaperEntity);

    /**
     * 查询
     * @param academicPaperEntity
     * @return
     */
    public AcademicPaperEntity findById(AcademicPaperEntity academicPaperEntity);

    /**
     * 查询--管理员
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<AcademicPaperEntity> pageBean);

    /**
     * 查询--部门领导和管理员
     * @param deptId
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByDept(Integer deptId, String key, PageBean<AcademicPaperEntity> pageBean);

    /**
     * 教师查询自己的学术论文
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByAuthor(Integer teacherId, String key, PageBean<AcademicPaperEntity> pageBean);

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
