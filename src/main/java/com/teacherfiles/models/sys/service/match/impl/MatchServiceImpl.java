package com.teacherfiles.models.sys.service.match.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.match.MatchDao;
import com.teacherfiles.models.sys.model.MatchEntity;
import com.teacherfiles.models.sys.service.match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author soldier
 * @title: MatchDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchDao matchDao;

    @Override
    public void save(MatchEntity matchEntity) {
        matchDao.save(matchEntity);
    }

    @Override
    public void delete(MatchEntity matchEntity) {
        matchDao.delete(matchEntity);
    }

    @Override
    public void update(MatchEntity matchEntity) {
        matchDao.update(matchEntity);
    }

    @Override
    public MatchEntity findById(MatchEntity matchEntity) {
        return matchDao.findById(matchEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<MatchEntity> pageBean) {
        return matchDao.findByPage(key, pageBean);
    }

    @Override
    public PageBean findByTeacher(Integer teacherId, String key, PageBean<MatchEntity> pageBean) {
        return matchDao.findByTeacher(teacherId, key, pageBean);
    }

    @Override
    public List<MatchEntity> findAll() {
        return matchDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        matchDao.deleteBatch(Ids);
    }
}
