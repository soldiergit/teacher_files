package com.teacherfiles.models.sys.service.academicPaperAnnex;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.AcademicPaperAnnexEntity;

import java.util.List;

/**
 * @author soldier
 * @title: AcademicPaperAnnexService
 * @projectName teacher_files
 * @date 19-7-22下午7:15
 * @Email： 583403411@qq.com
 * @description:
 */
public interface AcademicPaperAnnexService {

    /**
     * 添加
     * @param academicPaperAnnexEntity
     */
    public void save(AcademicPaperAnnexEntity academicPaperAnnexEntity);

    /**
     * 删除
     * @param academicPaperAnnexEntity
     */
    public void delete(AcademicPaperAnnexEntity academicPaperAnnexEntity);

    /**
     * 修改
     * @param academicPaperAnnexEntity
     */
    public void update(AcademicPaperAnnexEntity academicPaperAnnexEntity);

    /**
     * 根据论文查询所有附件
     * @param paperId
     * @return
     */
    public List<AcademicPaperAnnexEntity> findByPaperId(Integer paperId);

    /**
     * 查询
     * @param academicPaperAnnexEntity
     * @return
     */
    public AcademicPaperAnnexEntity findById(AcademicPaperAnnexEntity academicPaperAnnexEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<AcademicPaperAnnexEntity> pageBean);

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
