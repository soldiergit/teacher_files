package com.teacherfiles.models.sys.action.matchAnnex;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.MatchAnnexEntity;
import com.teacherfiles.models.sys.service.matchAnnex.MatchAnnexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author soldier
 * @title: MatchAnnexAnnexAction
 * @projectName teacher_files
 * @date 19-7-22下午7:31
 * @Email： 583403411@qq.com
 * @description:
 */
public class MatchAnnexAction extends ActionSupport implements ModelDriven<MatchAnnexEntity> {

    @Autowired
    private MatchAnnexService matchAnnexService;
    //日志
    private static Logger logger = Logger.getLogger(MatchAnnexAction.class);
    //模型驱动
    private MatchAnnexEntity matchAnnexEntity = new MatchAnnexEntity();
    //分页
    private PageBean<MatchAnnexEntity> pageBean = new PageBean<>();
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
    public MatchAnnexEntity getModel() {
        return matchAnnexEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        matchAnnexService.save(matchAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        matchAnnexService.delete(matchAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        matchAnnexService.update(matchAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {

        MatchAnnexEntity byId = matchAnnexService.findById(matchAnnexEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = matchAnnexService.findByPage(key, new PageBean<MatchAnnexEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 根据赛事查询所有附件
     */
    public String findByMatchId() {

        List<MatchAnnexEntity> byMatchId = matchAnnexService.findByMatchId(matchAnnexEntity.getMatchId());

        r = R.ok().put("data", byMatchId);

        logger.info("查询附件列表：" + r);

        return SUCCESS;
    }


    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        matchAnnexService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////
    public MatchAnnexEntity getMatchAnnexEntity() {
        return matchAnnexEntity;
    }

    public void setMatchAnnexEntity(MatchAnnexEntity matchAnnexEntity) {
        this.matchAnnexEntity = matchAnnexEntity;
    }

    public PageBean<MatchAnnexEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<MatchAnnexEntity> pageBean) {
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