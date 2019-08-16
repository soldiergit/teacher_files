package com.teacherfiles.models.sys.service.academicPaperGrade.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.academicPaperGrade.AcademicPaperGradeDao;
import com.teacherfiles.models.sys.model.AcademicPaperGradeEntity;
import com.teacherfiles.models.sys.service.academicPaperGrade.AcademicPaperGradeService;
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
public class AcademicPaperGradeServiceImpl implements AcademicPaperGradeService {

    @Autowired
    private AcademicPaperGradeDao academicPaperGradeDao;

    @Override
    public void save(AcademicPaperGradeEntity academicPaperGradeEntity) {
        academicPaperGradeDao.save(academicPaperGradeEntity);
    }

    @Override
    public void delete(AcademicPaperGradeEntity academicPaperGradeEntity) {
        academicPaperGradeDao.delete(academicPaperGradeEntity);
    }

    @Override
    public void update(AcademicPaperGradeEntity academicPaperGradeEntity) {
        academicPaperGradeDao.update(academicPaperGradeEntity);
    }

    @Override
    public AcademicPaperGradeEntity findById(AcademicPaperGradeEntity academicPaperGradeEntity) {
        return academicPaperGradeDao.findById(academicPaperGradeEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<AcademicPaperGradeEntity> pageBean) {
        return academicPaperGradeDao.findByPage(key, pageBean);
    }

    @Override
    public List<AcademicPaperGradeEntity> findAll() {
        return academicPaperGradeDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        academicPaperGradeDao.deleteBatch(Ids);
    }
}
