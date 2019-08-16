package com.teacherfiles.models.sys.service.academicPaper.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.academicPaper.AcademicPaperDao;
import com.teacherfiles.models.sys.model.AcademicPaperEntity;
import com.teacherfiles.models.sys.service.academicPaper.AcademicPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author soldier
 * @title: ServiceImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Service
public class AcademicPaperServiceImpl implements AcademicPaperService {

    @Autowired
    private AcademicPaperDao academicPaperDao;

    @Override
    public void save(AcademicPaperEntity academicPaperEntity) {
        academicPaperDao.save(academicPaperEntity);
    }

    @Override
    public void delete(AcademicPaperEntity academicPaperEntity) {
        academicPaperDao.delete(academicPaperEntity);
    }

    @Override
    public void update(AcademicPaperEntity academicPaperEntity) {
        academicPaperDao.update(academicPaperEntity);
    }

    @Override
    public AcademicPaperEntity findById(AcademicPaperEntity academicPaperEntity) {
        return academicPaperDao.findById(academicPaperEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<AcademicPaperEntity> pageBean) {
        return academicPaperDao.findByPage(key,pageBean);
    }

    @Override
    public PageBean findByDept(Integer deptId, String key, PageBean<AcademicPaperEntity> pageBean) {
        return academicPaperDao.findByDept(deptId, key, pageBean);
    }

    @Override
    public PageBean findByAuthor(Integer teacherId, String key, PageBean<AcademicPaperEntity> pageBean) {
        return academicPaperDao.findByAuthor(teacherId, key, pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        academicPaperDao.deleteBatch(Ids);
    }
}
