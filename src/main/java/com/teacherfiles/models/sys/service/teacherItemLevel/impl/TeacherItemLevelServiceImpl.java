package com.teacherfiles.models.sys.service.teacherItemLevel.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.teacherItemLevel.TeacherItemLevelDao;
import com.teacherfiles.models.sys.model.TeacherItemLevelEntity;
import com.teacherfiles.models.sys.service.teacherItemLevel.TeacherItemLevelService;
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
public class TeacherItemLevelServiceImpl implements TeacherItemLevelService {

    @Autowired
    private TeacherItemLevelDao teacherItemLevelDao;

    @Override
    public void save(TeacherItemLevelEntity teacherItemLevelEntity) {
        teacherItemLevelDao.save(teacherItemLevelEntity);
    }

    @Override
    public void delete(TeacherItemLevelEntity teacherItemLevelEntity) {
        teacherItemLevelDao.delete(teacherItemLevelEntity);
    }

    @Override
    public void update(TeacherItemLevelEntity teacherItemLevelEntity) {
        teacherItemLevelDao.update(teacherItemLevelEntity);
    }

    @Override
    public TeacherItemLevelEntity findById(TeacherItemLevelEntity teacherItemLevelEntity) {
        return teacherItemLevelDao.findById(teacherItemLevelEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherItemLevelEntity> pageBean) {
        return teacherItemLevelDao.findByPage(key, pageBean);
    }

    @Override
    public List<TeacherItemLevelEntity> findAll() {
        return teacherItemLevelDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teacherItemLevelDao.deleteBatch(Ids);
    }
}
