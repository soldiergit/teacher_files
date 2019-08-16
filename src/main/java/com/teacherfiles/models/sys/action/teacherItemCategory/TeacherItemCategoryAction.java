package com.teacherfiles.models.sys.action.teacherItemCategory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.TeacherItemCategoryEntity;
import com.teacherfiles.models.sys.service.teacherItemCategory.TeacherItemCategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author soldier
 * @title: RoleAction
 * @projectName Role_files
 * @date 19-7-5下午6:09
 * @Email： 583403411@qq.com
 * @description:
 */
public class TeacherItemCategoryAction extends ActionSupport implements ModelDriven<TeacherItemCategoryEntity> {

    @Autowired
    private TeacherItemCategoryService teacherItemCategoryService;
    //日志
    private static Logger logger = Logger.getLogger(TeacherItemCategoryAction.class);
    //模型驱动
    private TeacherItemCategoryEntity teacherItemCategoryEntity = new TeacherItemCategoryEntity();
    //分页
    private PageBean<TeacherItemCategoryEntity> pageBean = new PageBean<>();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //批量删除id/菜单控制id
    private String ids;

    @Override
    public TeacherItemCategoryEntity getModel() {
        return teacherItemCategoryEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save(){

        teacherItemCategoryService.save(teacherItemCategoryEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete(){

        logger.info("删除教师："+teacherItemCategoryEntity);

        teacherItemCategoryService.delete(teacherItemCategoryEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update(){

        teacherItemCategoryService.update(teacherItemCategoryEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById(){

        TeacherItemCategoryEntity oneRole = teacherItemCategoryService.findById(teacherItemCategoryEntity);

        r = R.ok().put("data",oneRole);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage(){

        logger.info("搜索："+key);

        PageBean allRole = teacherItemCategoryService.findByPage(key, new PageBean<TeacherItemCategoryEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",allRole.getRows()).put("count",allRole.getTotal());

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findAll(){

        logger.info("搜索："+key);

        List<TeacherItemCategoryEntity> all = teacherItemCategoryService.findAll();

        r = R.ok().put("data",all);

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch(){

        String[] id = ids.split(",");

        teacherItemCategoryService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }
    
    /////////////////////////////////////////

    public TeacherItemCategoryEntity getTeacherItemCategoryEntity() {
        return teacherItemCategoryEntity;
    }

    public void setTeacherItemCategoryEntity(TeacherItemCategoryEntity teacherItemCategoryEntity) {
        this.teacherItemCategoryEntity = teacherItemCategoryEntity;
    }

    public PageBean<TeacherItemCategoryEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TeacherItemCategoryEntity> pageBean) {
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
