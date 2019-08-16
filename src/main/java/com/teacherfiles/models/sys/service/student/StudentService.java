package com.teacherfiles.models.sys.service.student;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.StudentEntity;

/**
 * @author soldier
 * @title: StudentService
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface StudentService {

    /**
     * 添加
     * @param studentEntity
     */
    public void save(StudentEntity studentEntity);

    /**
     * 删除
     * @param studentEntity
     */
    public void delete(StudentEntity studentEntity);

    /**
     * 修改
     * @param studentEntity
     */
    public void update(StudentEntity studentEntity);

    /**
     * 查询
     * @param studentEntity
     * @return
     */
    public StudentEntity findById(StudentEntity studentEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<StudentEntity> pageBean);

    /**
     * 根据项目查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByRace(Integer itemId, String key, PageBean<StudentEntity> pageBean);


    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
