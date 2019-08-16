package com.teacherfiles.models.sys.dao.competition;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.CompetitionEntity;

import java.util.List;

/**
 * @author soldier
 * @title: CompetitionDao
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface CompetitionDao {

    /**
     * 添加
     * @param competitionEntity
     */
    public void save(CompetitionEntity competitionEntity);

    /**
     * 删除
     * @param competitionEntity
     */
    public void delete(CompetitionEntity competitionEntity);

    /**
     * 修改
     * @param competitionEntity
     */
    public void update(CompetitionEntity competitionEntity);

    /**
     * 查询
     * @param competitionEntity
     * @return
     */
    public CompetitionEntity findById(CompetitionEntity competitionEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<CompetitionEntity> pageBean);

    /**
     * 教师查询指导的所有比赛
     * @param teacherId
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByTeacher(Integer teacherId, String key, PageBean<CompetitionEntity> pageBean);

    /**
     * 教师查询参加的所有比赛
     * @param teacherName
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByAwardee(String teacherName, String key, PageBean<CompetitionEntity> pageBean);

    /**
     * 部门领导查询本部门所有比赛
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByDept(Integer deptId, String key, PageBean<CompetitionEntity> pageBean);

    /**
     * 批量导出
     * @param deptId
     * @param Ids
     * @param teacherId
     * @return
     */
    public List<CompetitionEntity> findByExport(Integer deptId, String[] Ids, Integer teacherId);
    
    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
