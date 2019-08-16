package com.teacherfiles.models.sys.action.competitionPrizeLevel;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.CompetitionPrizeLevelEntity;
import com.teacherfiles.models.sys.service.competitionPrizeLevel.CompetitionPrizeLevelService;
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
public class CompetitionPrizeLevelAction extends ActionSupport implements ModelDriven<CompetitionPrizeLevelEntity> {

    @Autowired
    private CompetitionPrizeLevelService competitionPrizeLevelService;
    //日志
    private static Logger logger = Logger.getLogger(CompetitionPrizeLevelAction.class);
    //模型驱动
    private CompetitionPrizeLevelEntity competitionPrizeLevelEntity = new CompetitionPrizeLevelEntity();
    //分页
    private PageBean<CompetitionPrizeLevelEntity> pageBean = new PageBean<>();
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
    public CompetitionPrizeLevelEntity getModel() {
        return competitionPrizeLevelEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save(){

        competitionPrizeLevelService.save(competitionPrizeLevelEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete(){

        logger.info("删除教师："+competitionPrizeLevelEntity);

        competitionPrizeLevelService.delete(competitionPrizeLevelEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update(){

        competitionPrizeLevelService.update(competitionPrizeLevelEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById(){

        CompetitionPrizeLevelEntity one = competitionPrizeLevelService.findById(competitionPrizeLevelEntity);

        r = R.ok().put("data",one);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage(){

        logger.info("搜索："+key);

        PageBean all = competitionPrizeLevelService.findByPage(key, new PageBean<CompetitionPrizeLevelEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",all.getRows()).put("count",all.getTotal());

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findAll(){

        logger.info("搜索："+key);

        List<CompetitionPrizeLevelEntity> all = competitionPrizeLevelService.findAll();

        r = R.ok().put("data",all);

        logger.info("查询列表："+r);

        return SUCCESS;
    }


    /**
     * 批量删除
     */
    public String deleteBatch(){

        String[] id = ids.split(",");

        competitionPrizeLevelService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////

    public CompetitionPrizeLevelEntity getCompetitionPrizeLevelEntity() {
        return competitionPrizeLevelEntity;
    }

    public void setCompetitionPrizeLevelEntity(CompetitionPrizeLevelEntity competitionPrizeLevelEntity) {
        this.competitionPrizeLevelEntity = competitionPrizeLevelEntity;
    }

    public PageBean<CompetitionPrizeLevelEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<CompetitionPrizeLevelEntity> pageBean) {
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
