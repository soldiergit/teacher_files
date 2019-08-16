package com.teacherfiles.models.sys.service.competitionPrizeLevel.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.competitionPrizeLevel.CompetitionPrizeLevelDao;
import com.teacherfiles.models.sys.model.CompetitionPrizeLevelEntity;
import com.teacherfiles.models.sys.service.competitionPrizeLevel.CompetitionPrizeLevelService;
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
public class CompetitionPrizeLevelServiceImpl implements CompetitionPrizeLevelService {

    @Autowired
    private CompetitionPrizeLevelDao competitionPrizeLevelDao;

    @Override
    public void save(CompetitionPrizeLevelEntity competitionPrizeLevelEntity) {
        competitionPrizeLevelDao.save(competitionPrizeLevelEntity);
    }

    @Override
    public void delete(CompetitionPrizeLevelEntity competitionPrizeLevelEntity) {
        competitionPrizeLevelDao.delete(competitionPrizeLevelEntity);
    }

    @Override
    public void update(CompetitionPrizeLevelEntity competitionPrizeLevelEntity) {
        competitionPrizeLevelDao.update(competitionPrizeLevelEntity);
    }

    @Override
    public CompetitionPrizeLevelEntity findById(CompetitionPrizeLevelEntity competitionPrizeLevelEntity) {
        return competitionPrizeLevelDao.findById(competitionPrizeLevelEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<CompetitionPrizeLevelEntity> pageBean) {
        return competitionPrizeLevelDao.findByPage(key, pageBean);
    }

    @Override
    public List<CompetitionPrizeLevelEntity> findAll() {
        return competitionPrizeLevelDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        competitionPrizeLevelDao.deleteBatch(Ids);
    }
}
