package com.teacherfiles.models.sys.action.teacherItemAnnex;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.TeacherItemAnnexEntity;
import com.teacherfiles.models.sys.service.teacherItemAnnex.TeacherItemAnnexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherItemAnnexAnnexAction
 * @projectName teacher_files
 * @date 19-7-22下午7:31
 * @Email： 583403411@qq.com
 * @description:
 */
public class TeacherItemAnnexAction extends ActionSupport implements ModelDriven<TeacherItemAnnexEntity> {

    @Autowired
    private TeacherItemAnnexService teacherItemAnnexService;
    //日志
    private static Logger logger = Logger.getLogger(TeacherItemAnnexAction.class);
    //模型驱动
    private TeacherItemAnnexEntity teacherItemAnnexEntity = new TeacherItemAnnexEntity();
    //分页
    private PageBean<TeacherItemAnnexEntity> pageBean = new PageBean<>();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //批量删除id
    private String ids;

    @Override
    public TeacherItemAnnexEntity getModel() {
        return teacherItemAnnexEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        teacherItemAnnexService.save(teacherItemAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        teacherItemAnnexService.delete(teacherItemAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        teacherItemAnnexService.update(teacherItemAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {

        TeacherItemAnnexEntity byId = teacherItemAnnexService.findById(teacherItemAnnexEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = teacherItemAnnexService.findByPage(key, new PageBean<TeacherItemAnnexEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 根据教师项目查询所有附件
     */
    public String findByTeacherItemId() {

        List<TeacherItemAnnexEntity> byTeacherItemId = teacherItemAnnexService.findByTeacherItemId(teacherItemAnnexEntity.getItemId());

        r = R.ok().put("data", byTeacherItemId);

        logger.info("查询附件列表：" + r);

        return SUCCESS;
    }


    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        teacherItemAnnexService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////
    public TeacherItemAnnexEntity getTeacherItemAnnexEntity() {
        return teacherItemAnnexEntity;
    }

    public void setTeacherItemAnnexEntity(TeacherItemAnnexEntity teacherItemAnnexEntity) {
        this.teacherItemAnnexEntity = teacherItemAnnexEntity;
    }

    public PageBean<TeacherItemAnnexEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TeacherItemAnnexEntity> pageBean) {
        this.pageBean = pageBean;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}