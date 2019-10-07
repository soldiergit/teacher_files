package com.teacherfiles.models.sys.service.teacherItemAnnex.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.teacherItemAnnex.TeacherItemAnnexDao;
import com.teacherfiles.models.sys.model.TeacherItemAnnexEntity;
import com.teacherfiles.models.sys.service.teacherItemAnnex.TeacherItemAnnexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherItemAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class TeacherItemAnnexServiceImpl implements TeacherItemAnnexService {

    @Autowired
    private TeacherItemAnnexDao teacherItemAnnexDao;

    @Override
    public void save(TeacherItemAnnexEntity teacherItemAnnexEntity) {
        teacherItemAnnexDao.save(teacherItemAnnexEntity);
    }

    @Override
    public void delete(TeacherItemAnnexEntity teacherItemAnnexEntity) {
        teacherItemAnnexDao.delete(teacherItemAnnexEntity);
    }

    @Override
    public void update(TeacherItemAnnexEntity teacherItemAnnexEntity) {
        teacherItemAnnexDao.update(teacherItemAnnexEntity);
    }

    @Override
    public TeacherItemAnnexEntity findById(TeacherItemAnnexEntity teacherItemAnnexEntity) {
        return teacherItemAnnexDao.findById(teacherItemAnnexEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherItemAnnexEntity> pageBean) {
        return teacherItemAnnexDao.findByPage(key, pageBean);
    }

    @Override
    public List<TeacherItemAnnexEntity> findByTeacherItemId(Integer itemId) {
        return teacherItemAnnexDao.findByTeacherItemId(itemId);
    }

    @Override
    public List<TeacherItemAnnexEntity> findAll() {
        return teacherItemAnnexDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teacherItemAnnexDao.deleteBatch(Ids);
    }
}
