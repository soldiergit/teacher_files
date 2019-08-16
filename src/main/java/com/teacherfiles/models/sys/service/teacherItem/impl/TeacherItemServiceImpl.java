package com.teacherfiles.models.sys.service.teacherItem.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.teacherItem.TeacherItemDao;
import com.teacherfiles.models.sys.model.TeacherItemEntity;
import com.teacherfiles.models.sys.service.teacherItem.TeacherItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherItemServiceImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:53
 */
@Service
public class TeacherItemServiceImpl implements TeacherItemService {

    @Autowired
    private TeacherItemDao teacherItemDao;

    @Override
    public void save(TeacherItemEntity teacherItemEntity) {
        teacherItemDao.save(teacherItemEntity);
    }

    @Override
    public void delete(TeacherItemEntity teacherItemEntity) {
        teacherItemDao.delete(teacherItemEntity);
    }

    @Override
    public void update(TeacherItemEntity teacherItemEntity) {
        teacherItemDao.update(teacherItemEntity);
    }

    @Override
    public TeacherItemEntity findById(TeacherItemEntity teacherItemEntity) {
        return teacherItemDao.findById(teacherItemEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherItemEntity> pageBean) {
        return teacherItemDao.findByPage(key, pageBean);
    }

    @Override
    public PageBean findByPerson(Integer itemPersonId, String key, PageBean<TeacherItemEntity> pageBean) {
        return teacherItemDao.findByPerson(itemPersonId, key, pageBean);
    }

    @Override
    public PageBean findByMember(String memberId, String key, PageBean<TeacherItemEntity> pageBean) {
        return teacherItemDao.findByMember(memberId, key, pageBean);
    }

    @Override
    public PageBean findByDept(Integer deptId, String key, PageBean<TeacherItemEntity> pageBean) {
        return teacherItemDao.findByDept(deptId, key, pageBean);
    }

    @Override
    public List<TeacherItemEntity> findByExport(Integer deptId, String[] Ids, Integer itemPersonId) {
        return teacherItemDao.findByExport(deptId, Ids, itemPersonId);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teacherItemDao.deleteBatch(Ids);
    }
}
