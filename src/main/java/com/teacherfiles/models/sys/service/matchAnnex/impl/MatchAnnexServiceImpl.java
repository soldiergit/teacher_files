package com.teacherfiles.models.sys.service.matchAnnex.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.matchAnnex.MatchAnnexDao;
import com.teacherfiles.models.sys.model.MatchAnnexEntity;
import com.teacherfiles.models.sys.service.matchAnnex.MatchAnnexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author soldier
 * @title: MatchAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class MatchAnnexServiceImpl implements MatchAnnexService {

    @Autowired
    private MatchAnnexDao MatchAnnexDao;

    @Override
    public void save(MatchAnnexEntity matchAnnexEntity) {
        MatchAnnexDao.save(matchAnnexEntity);
    }

    @Override
    public void delete(MatchAnnexEntity matchAnnexEntity) {
        MatchAnnexDao.delete(matchAnnexEntity);
    }

    @Override
    public void update(MatchAnnexEntity matchAnnexEntity) {
        MatchAnnexDao.update(matchAnnexEntity);
    }

    @Override
    public MatchAnnexEntity findById(MatchAnnexEntity matchAnnexEntity) {
        return MatchAnnexDao.findById(matchAnnexEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<MatchAnnexEntity> pageBean) {
        return MatchAnnexDao.findByPage(key, pageBean);
    }

    @Override
    public List<MatchAnnexEntity> findByMatchId(Integer matchId) {
        return MatchAnnexDao.findByMatchId(matchId);
    }

    @Override
    public List<MatchAnnexEntity> findAll() {
        return MatchAnnexDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        MatchAnnexDao.deleteBatch(Ids);
    }
}
