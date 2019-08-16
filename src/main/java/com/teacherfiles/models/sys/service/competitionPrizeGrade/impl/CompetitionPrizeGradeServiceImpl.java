package com.teacherfiles.models.sys.service.competitionPrizeGrade.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.competitionPrizeGrade.CompetitionPrizeGradeDao;
import com.teacherfiles.models.sys.model.CompetitionPrizeGradeEntity;
import com.teacherfiles.models.sys.service.competitionPrizeGrade.CompetitionPrizeGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: teacher_files
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-8-3 下午5:58
 * @Describe:
 **/
@Service
public class CompetitionPrizeGradeServiceImpl implements CompetitionPrizeGradeService {

    @Autowired
    private CompetitionPrizeGradeDao competitionPrizeGradeDao;

    @Override
    public void save(CompetitionPrizeGradeEntity competitionPrizeGradeEntity) {
        competitionPrizeGradeDao.save(competitionPrizeGradeEntity);
    }

    @Override
    public void delete(CompetitionPrizeGradeEntity competitionPrizeGradeEntity) {
        competitionPrizeGradeDao.delete(competitionPrizeGradeEntity);
    }

    @Override
    public void update(CompetitionPrizeGradeEntity competitionPrizeGradeEntity) {
        competitionPrizeGradeDao.update(competitionPrizeGradeEntity);
    }

    @Override
    public CompetitionPrizeGradeEntity findById(CompetitionPrizeGradeEntity competitionPrizeGradeEntity) {
        return competitionPrizeGradeDao.findById(competitionPrizeGradeEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<CompetitionPrizeGradeEntity> pageBean) {
        return competitionPrizeGradeDao.findByPage(key, pageBean);
    }

    @Override
    public List<CompetitionPrizeGradeEntity> findAll() {
        return competitionPrizeGradeDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        competitionPrizeGradeDao.deleteBatch(Ids);
    }
}
