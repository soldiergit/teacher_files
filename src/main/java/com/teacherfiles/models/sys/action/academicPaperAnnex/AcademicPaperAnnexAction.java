package com.teacherfiles.models.sys.action.academicPaperAnnex;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.action.academicPaper.AcademicPaperAction;
import com.teacherfiles.models.sys.model.AcademicPaperAnnexEntity;
import com.teacherfiles.models.sys.service.academicPaperAnnex.AcademicPaperAnnexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author soldier
 * @title: AcademicPaperAnnexAction
 * @projectName teacher_files
 * @date 19-7-22下午7:26
 * @Email： 583403411@qq.com
 * @description:
 */
public class AcademicPaperAnnexAction extends ActionSupport implements ModelDriven<AcademicPaperAnnexEntity> {
    @Autowired
    private AcademicPaperAnnexService academicPaperAnnexService;
    //日志
    private static Logger logger = Logger.getLogger(AcademicPaperAction.class);
    //模型驱动
    private AcademicPaperAnnexEntity academicPaperAnnexEntity = new AcademicPaperAnnexEntity();
    //分页
    private PageBean<AcademicPaperAnnexEntity> pageBean = new PageBean<>();
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
    public AcademicPaperAnnexEntity getModel() {
        return academicPaperAnnexEntity;
    }

    /////////////////////////////////////////
    /**
     * 添加
     */
    public String save() {

        academicPaperAnnexService.save(academicPaperAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        academicPaperAnnexService.delete(academicPaperAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        academicPaperAnnexService.update(academicPaperAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById() {

        AcademicPaperAnnexEntity byId = academicPaperAnnexService.findById(academicPaperAnnexEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = academicPaperAnnexService.findByPage(key, new PageBean<AcademicPaperAnnexEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 根据论文查询所有附件
     */
    public String findByPaperId() {

        List<AcademicPaperAnnexEntity> byPaperId = academicPaperAnnexService.findByPaperId(academicPaperAnnexEntity.getPaperId());

        r = R.ok().put("data", byPaperId);

        logger.info("查询附件列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        academicPaperAnnexService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////

    public AcademicPaperAnnexEntity getAcademicPaperAnnexEntity() {
        return academicPaperAnnexEntity;
    }

    public void setAcademicPaperAnnexEntity(AcademicPaperAnnexEntity academicPaperAnnexEntity) {
        this.academicPaperAnnexEntity = academicPaperAnnexEntity;
    }

    public PageBean<AcademicPaperAnnexEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<AcademicPaperAnnexEntity> pageBean) {
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
