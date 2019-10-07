package com.teacherfiles.models.sys.service.teacherItemAnnex;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.model.TeacherItemAnnexEntity;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherItemAnnexService
 * @projectName teacher_files
 * @date 19-7-22下午7:15
 * @Email： 583403411@qq.com
 * @description:
 */
public interface TeacherItemAnnexService {

    /**
     * 添加
     * @param teacherItemAnnexEntity
     */
    public void save(TeacherItemAnnexEntity teacherItemAnnexEntity);

    /**
     * 删除
     * @param teacherItemAnnexEntity
     */
    public void delete(TeacherItemAnnexEntity teacherItemAnnexEntity);

    /**
     * 修改
     * @param teacherItemAnnexEntity
     */
    public void update(TeacherItemAnnexEntity teacherItemAnnexEntity);

    /**
     * 查询
     * @param teacherItemAnnexEntity
     */
    public TeacherItemAnnexEntity findById(TeacherItemAnnexEntity teacherItemAnnexEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     */
    public PageBean findByPage(String key, PageBean<TeacherItemAnnexEntity> pageBean);


    /**
     * 根据教师项目查询所有附件
     * @param itemId
     * @return
     */
    public List<TeacherItemAnnexEntity> findByTeacherItemId(Integer itemId);

    /**
     * 查询
     * @return
     */
    public List<TeacherItemAnnexEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
