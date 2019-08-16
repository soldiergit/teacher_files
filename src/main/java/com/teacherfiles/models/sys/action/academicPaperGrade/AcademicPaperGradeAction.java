package com.teacherfiles.models.sys.action.academicPaperGrade;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.AcademicPaperGradeEntity;
import com.teacherfiles.models.sys.service.academicPaperGrade.AcademicPaperGradeService;
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
public class AcademicPaperGradeAction extends ActionSupport implements ModelDriven<AcademicPaperGradeEntity> {

    @Autowired
    private AcademicPaperGradeService academicPaperGradeService;
    //日志
    private static Logger logger = Logger.getLogger(AcademicPaperGradeAction.class);
    //模型驱动
    private AcademicPaperGradeEntity academicPaperGradeEntity = new AcademicPaperGradeEntity();
    //分页
    private PageBean<AcademicPaperGradeEntity> pageBean = new PageBean<>();
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
    public AcademicPaperGradeEntity getModel() {
        return academicPaperGradeEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save(){

        academicPaperGradeService.save(academicPaperGradeEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete(){

        logger.info("删除教师："+academicPaperGradeEntity);

        academicPaperGradeService.delete(academicPaperGradeEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update(){

        academicPaperGradeService.update(academicPaperGradeEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById(){

        AcademicPaperGradeEntity onePaperGrade = academicPaperGradeService.findById(academicPaperGradeEntity);

        r = R.ok().put("data",onePaperGrade);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage(){

        logger.info("搜索："+key);

        PageBean all = academicPaperGradeService.findByPage(key, new PageBean<AcademicPaperGradeEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",all.getRows()).put("count",all.getTotal());

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findAll(){

        logger.info("搜索："+key);

        List<AcademicPaperGradeEntity> all = academicPaperGradeService.findAll();

        r = R.ok().put("data",all);

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch(){

        String[] id = ids.split(",");

        academicPaperGradeService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }
    
    /////////////////////////////////////////

    public AcademicPaperGradeEntity getAcademicPaperGradeEntity() {
        return academicPaperGradeEntity;
    }

    public void setAcademicPaperGradeEntity(AcademicPaperGradeEntity academicPaperGradeEntity) {
        this.academicPaperGradeEntity = academicPaperGradeEntity;
    }

    public PageBean<AcademicPaperGradeEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<AcademicPaperGradeEntity> pageBean) {
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
