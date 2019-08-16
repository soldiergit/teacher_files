package com.teacherfiles.models.sys.dao.competitionPrizeLevel;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.CompetitionPrizeLevelEntity;

import java.util.List;

/**
 * @author soldier
 * @title: AcademicPaperDao
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface CompetitionPrizeLevelDao {

    /**
     * 添加
     * @param competitionPrizeLevelEntity
     */
    public void save(CompetitionPrizeLevelEntity competitionPrizeLevelEntity);

    /**
     * 删除
     * @param competitionPrizeLevelEntity
     */
    public void delete(CompetitionPrizeLevelEntity competitionPrizeLevelEntity);

    /**
     * 修改
     * @param competitionPrizeLevelEntity
     */
    public void update(CompetitionPrizeLevelEntity competitionPrizeLevelEntity);

    /**
     * 查询
     * @param competitionPrizeLevelEntity
     * @return
     */
    public CompetitionPrizeLevelEntity findById(CompetitionPrizeLevelEntity competitionPrizeLevelEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<CompetitionPrizeLevelEntity> pageBean);

    /**
     * 查询全部，用于下拉框渲染
     * @return
     */
    public List<CompetitionPrizeLevelEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
