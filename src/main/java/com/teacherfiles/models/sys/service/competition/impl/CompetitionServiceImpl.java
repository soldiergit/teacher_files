package com.teacherfiles.models.sys.service.competition.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.competition.CompetitionDao;
import com.teacherfiles.models.sys.model.CompetitionEntity;
import com.teacherfiles.models.sys.service.competition.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: CompetitionServiceImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:48
 */
@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionDao competitionDao;

    @Override
    public void save(CompetitionEntity competitionEntity) {
        competitionDao.save(competitionEntity);
    }

    @Override
    public void delete(CompetitionEntity competitionEntity) {
        competitionDao.delete(competitionEntity);
    }

    @Override
    public void update(CompetitionEntity competitionEntity) {
        competitionDao.update(competitionEntity);
    }

    @Override
    public CompetitionEntity findById(CompetitionEntity competitionEntity) {
        return competitionDao.findById(competitionEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<CompetitionEntity> pageBean) {
        return competitionDao.findByPage(key, pageBean);
    }

    @Override
    public PageBean findByTeacher(Integer teacherId, String key, PageBean<CompetitionEntity> pageBean) {
        return competitionDao.findByTeacher(teacherId, key, pageBean);
    }

    @Override
    public PageBean findByAwardee(String teacherName, String key, PageBean<CompetitionEntity> pageBean) {
        return competitionDao.findByAwardee(teacherName, key, pageBean);
    }

    @Override
    public PageBean findByDept(Integer deptId, String key, PageBean<CompetitionEntity> pageBean) {
        return competitionDao.findByDept(deptId, key, pageBean);
    }

    @Override
    public List<CompetitionEntity> findByExport(Integer deptId, String[] Ids, Integer teacherId) {
        return competitionDao.findByExport(deptId, Ids, teacherId);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        competitionDao.deleteBatch(Ids);
    }
}
