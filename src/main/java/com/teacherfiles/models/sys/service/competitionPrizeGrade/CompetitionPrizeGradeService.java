package com.teacherfiles.models.sys.service.competitionPrizeGrade;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.CompetitionPrizeGradeEntity;

import java.util.List;

/**
 * @author soldier
 * @title: AcademicPaperService
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface CompetitionPrizeGradeService {

    /**
     * 添加
     * @param competitionPrizeGradeEntity
     */
    public void save(CompetitionPrizeGradeEntity competitionPrizeGradeEntity);

    /**
     * 删除
     * @param competitionPrizeGradeEntity
     */
    public void delete(CompetitionPrizeGradeEntity competitionPrizeGradeEntity);

    /**
     * 修改
     * @param competitionPrizeGradeEntity
     */
    public void update(CompetitionPrizeGradeEntity competitionPrizeGradeEntity);

    /**
     * 查询
     * @param competitionPrizeGradeEntity
     * @return
     */
    public CompetitionPrizeGradeEntity findById(CompetitionPrizeGradeEntity competitionPrizeGradeEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<CompetitionPrizeGradeEntity> pageBean);

    /**
     * 查询全部，用于下拉框渲染
     * @return
     */
    public List<CompetitionPrizeGradeEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
