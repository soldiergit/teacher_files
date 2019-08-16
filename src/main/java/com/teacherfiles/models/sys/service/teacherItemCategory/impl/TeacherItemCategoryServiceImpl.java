package com.teacherfiles.models.sys.service.teacherItemCategory.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.teacherItemCategory.TeacherItemCategoryDao;
import com.teacherfiles.models.sys.model.TeacherItemCategoryEntity;
import com.teacherfiles.models.sys.service.teacherItemCategory.TeacherItemCategoryService;
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
public class TeacherItemCategoryServiceImpl implements TeacherItemCategoryService {

    @Autowired
    private TeacherItemCategoryDao teacherItemCategoryDao;

    @Override
    public void save(TeacherItemCategoryEntity teacherItemCategoryEntity) {
        teacherItemCategoryDao.save(teacherItemCategoryEntity);
    }

    @Override
    public void delete(TeacherItemCategoryEntity teacherItemCategoryEntity) {
        teacherItemCategoryDao.delete(teacherItemCategoryEntity);
    }

    @Override
    public void update(TeacherItemCategoryEntity teacherItemCategoryEntity) {
        teacherItemCategoryDao.update(teacherItemCategoryEntity);
    }

    @Override
    public TeacherItemCategoryEntity findById(TeacherItemCategoryEntity teacherItemCategoryEntity) {
        return teacherItemCategoryDao.findById(teacherItemCategoryEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherItemCategoryEntity> pageBean) {
        return teacherItemCategoryDao.findByPage(key, pageBean);
    }

    @Override
    public List<TeacherItemCategoryEntity> findAll() {
        return teacherItemCategoryDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teacherItemCategoryDao.deleteBatch(Ids);
    }
}
