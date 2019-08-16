package com.teacherfiles.models.sys.service.dept.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.dept.DeptDao;
import com.teacherfiles.models.sys.model.DeptEntity;
import com.teacherfiles.models.sys.service.dept.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: DeptServiceImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:39
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;
    @Override
    public void save(DeptEntity deptEntity) {
        deptDao.save(deptEntity);
    }

    @Override
    public void delete(DeptEntity deptEntity) {
        deptDao.delete(deptEntity);
    }

    @Override
    public void update(DeptEntity deptEntity) {
        deptDao.update(deptEntity);
    }

    @Override
    public DeptEntity findById(DeptEntity deptEntity) {
        return deptDao.findById(deptEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<DeptEntity> pageBean) {
        return deptDao.findByPage(key, pageBean);
    }

    @Override
    public PageBean findByDept(Integer deptId, String key, PageBean<DeptEntity> pageBean) {
        return deptDao.findByDept(deptId, key, pageBean);
    }

    @Override
    public List<DeptEntity> findAll() {
        return deptDao.findAll();
    }

    @Override
    public List<DeptEntity> findSubordinate(Integer parentId) {
        return deptDao.findSubordinate(parentId);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        deptDao.deleteBatch(Ids);
    }
}
