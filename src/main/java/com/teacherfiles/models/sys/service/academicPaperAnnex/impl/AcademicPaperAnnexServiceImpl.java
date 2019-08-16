package com.teacherfiles.models.sys.service.academicPaperAnnex.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.academicPaperAnnex.AcademicPaperAnnexDao;
import com.teacherfiles.models.sys.model.AcademicPaperAnnexEntity;
import com.teacherfiles.models.sys.service.academicPaperAnnex.AcademicPaperAnnexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: AcademicPaperAnnexServiceImpl
 * @projectName teacher_files
 * @date 19-7-22下午7:17
 * @Email： 583403411@qq.com
 * @description:
 */
@Service
public class AcademicPaperAnnexServiceImpl implements AcademicPaperAnnexService {

    @Autowired
    private AcademicPaperAnnexDao academicPaperAnnexDao;

    @Override
    public void save(AcademicPaperAnnexEntity academicPaperAnnexEntity) {
        academicPaperAnnexDao.save(academicPaperAnnexEntity);
    }

    @Override
    public void delete(AcademicPaperAnnexEntity academicPaperAnnexEntity) {
        academicPaperAnnexDao.delete(academicPaperAnnexEntity);
    }

    @Override
    public void update(AcademicPaperAnnexEntity academicPaperAnnexEntity) {
        academicPaperAnnexDao.update(academicPaperAnnexEntity);
    }

    @Override
    public List<AcademicPaperAnnexEntity> findByPaperId(Integer paperId) {
        return academicPaperAnnexDao.findByPaperId(paperId);
    }

    @Override
    public AcademicPaperAnnexEntity findById(AcademicPaperAnnexEntity academicPaperAnnexEntity) {
        return academicPaperAnnexDao.findById(academicPaperAnnexEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<AcademicPaperAnnexEntity> pageBean) {
        return academicPaperAnnexDao.findByPage(key, pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        academicPaperAnnexDao.deleteBatch(Ids);
    }
}
