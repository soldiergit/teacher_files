package com.teacherfiles.models.sys.action.teacherItemLevel;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.TeacherItemLevelEntity;
import com.teacherfiles.models.sys.service.teacherItemLevel.TeacherItemLevelService;
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
public class TeacherItemLevelAction extends ActionSupport implements ModelDriven<TeacherItemLevelEntity> {

    @Autowired
    private TeacherItemLevelService teacherItemLevelService;
    //日志
    private static Logger logger = Logger.getLogger(TeacherItemLevelAction.class);
    //模型驱动
    private TeacherItemLevelEntity teacherItemLevelEntity = new TeacherItemLevelEntity();
    //分页
    private PageBean<TeacherItemLevelEntity> pageBean = new PageBean<>();
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
    public TeacherItemLevelEntity getModel() {
        return teacherItemLevelEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save(){

        teacherItemLevelService.save(teacherItemLevelEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete(){

        logger.info("删除教师："+teacherItemLevelEntity);

        teacherItemLevelService.delete(teacherItemLevelEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update(){

        teacherItemLevelService.update(teacherItemLevelEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById(){

        TeacherItemLevelEntity oneRole = teacherItemLevelService.findById(teacherItemLevelEntity);

        r = R.ok().put("data",oneRole);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage(){

        logger.info("搜索："+key);

        PageBean allRole = teacherItemLevelService.findByPage(key, new PageBean<TeacherItemLevelEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",allRole.getRows()).put("count",allRole.getTotal());

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findAll(){

        logger.info("搜索："+key);

        List<TeacherItemLevelEntity> all = teacherItemLevelService.findAll();

        r = R.ok().put("data",all);

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch(){

        String[] id = ids.split(",");

        teacherItemLevelService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }
    
    /////////////////////////////////////////

    public TeacherItemLevelEntity getTeacherItemLevelEntity() {
        return teacherItemLevelEntity;
    }

    public void setTeacherItemLevelEntity(TeacherItemLevelEntity teacherItemLevelEntity) {
        this.teacherItemLevelEntity = teacherItemLevelEntity;
    }

    public PageBean<TeacherItemLevelEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TeacherItemLevelEntity> pageBean) {
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
