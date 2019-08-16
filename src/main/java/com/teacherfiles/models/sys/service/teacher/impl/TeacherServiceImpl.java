package com.teacherfiles.models.sys.service.teacher.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.teacher.TeacherDao;
import com.teacherfiles.models.sys.model.TeacherEntity;
import com.teacherfiles.models.sys.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherService
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public void save(TeacherEntity teacherEntity) {
        teacherDao.save(teacherEntity);
    }

    @Override
    public void delete(TeacherEntity teacherEntity) {
        teacherDao.delete(teacherEntity);
    }

    @Override
    public void update(TeacherEntity teacherEntity) {
        teacherDao.update(teacherEntity);
    }

    @Override
    public TeacherEntity findById(TeacherEntity teacherEntity) {
        return teacherDao.findById(teacherEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherEntity> pageBean) {
        return teacherDao.findByPage(key, pageBean);
    }

    @Override
    public PageBean findByDept(Integer deptId, String key, PageBean<TeacherEntity> pageBean) {
        return teacherDao.findByDept(deptId, key, pageBean);
    }

    @Override
    public PageBean findDeptAdmin(String key, PageBean<TeacherEntity> pageBean) {
        return teacherDao.findDeptAdmin(key, pageBean);
    }

    @Override
    public List<TeacherEntity> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public List<TeacherEntity> findAllByDept(Integer deptId) {
        return teacherDao.findAllByDept(deptId);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teacherDao.deleteBatch(Ids);
    }
}
