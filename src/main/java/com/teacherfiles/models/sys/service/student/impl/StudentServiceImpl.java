package com.teacherfiles.models.sys.service.student.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.student.StudentDao;
import com.teacherfiles.models.sys.model.StudentEntity;
import com.teacherfiles.models.sys.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author soldier
 * @title: StudentServiceImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:48
 */
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public void save(StudentEntity studentEntity) {
        studentDao.save(studentEntity);
    }

    @Override
    public void delete(StudentEntity studentEntity) {
        studentDao.delete(studentEntity);
    }

    @Override
    public void update(StudentEntity studentEntity) {
        studentDao.update(studentEntity);
    }

    @Override
    public StudentEntity findById(StudentEntity studentEntity) {
        return studentDao.findById(studentEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<StudentEntity> pageBean) {
        return studentDao.findByPage(key, pageBean);
    }

    @Override
    public PageBean findByRace(Integer itemId, String key, PageBean<StudentEntity> pageBean) {
        return studentDao.findByRace(itemId, key, pageBean);
    }


    @Override
    public void deleteBatch(String[] Ids) {
        studentDao.deleteBatch(Ids);
    }
}
