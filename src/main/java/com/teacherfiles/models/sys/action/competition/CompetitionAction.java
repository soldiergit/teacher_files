package com.teacherfiles.models.sys.action.competition;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.*;
import com.teacherfiles.models.sys.service.competitionPrizeGrade.CompetitionPrizeGradeService;
import com.teacherfiles.models.sys.service.competitionPrizeLevel.CompetitionPrizeLevelService;
import com.teacherfiles.models.sys.service.dept.DeptService;
import com.teacherfiles.models.sys.service.match.MatchService;
import com.teacherfiles.models.sys.service.competition.CompetitionService;
import com.teacherfiles.models.sys.service.teacher.TeacherService;
import com.teacherfiles.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soldier
 * @title: TeacherItemAction
 * @projectName teacher_files
 * @date 19-7-5下午5:16
 * @Email： 583403411@qq.com
 * @description:
 */
public class CompetitionAction extends ActionSupport implements ModelDriven<CompetitionEntity> {

    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private CompetitionPrizeLevelService prizeLevelService;
    @Autowired
    private CompetitionPrizeGradeService prizeGradeService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DeptService deptService;
    //日志
    private static Logger logger = Logger.getLogger(CompetitionAction.class);
    //模型驱动
    private CompetitionEntity competitionEntity = new CompetitionEntity();
    //分页
    private PageBean<CompetitionEntity> pageBean = new PageBean<>();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //赛事id
    private Integer matchId;
    //指导老师id
    private Integer teacherId;
    //获奖人姓名
    private String teacherName;
    //批量删除id
    private String ids;
    //获奖级别id
    private Integer prizeLevelId;
    //获奖等次id
    private Integer prizeGradeId;
    //竞赛项目获奖时间
    private String itemPrizeTime;
    //竞赛项目竞赛时间
    private String itemMatchTime;
    //竞赛项目申请
    private String itemApplyTime;
    //导出标识 1-系统管理员导出整个表 2-部门领导和部门负责人导出本学院全部 3-教师个人导出全部 4-根据id导出
    private Integer exportCode;

    @Override
    public CompetitionEntity getModel() {
        return competitionEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

//        competitionEntity.setMatchTime(new java.sql.Date(DateUtil.string2Date(itemMatchTime,"yyyy-MM-dd").getTime()));
//        competitionEntity.setApplyTime(new java.sql.Date(DateUtil.string2Date(itemApplyTime,"yyyy-MM-dd").getTime()));
        competitionEntity.setPrizeTime(new java.sql.Date(DateUtil.string2Date(itemPrizeTime,"yyyy").getTime()));

        //获奖信息
        CompetitionPrizeLevelEntity prizeLevel_result = new CompetitionPrizeLevelEntity();
        CompetitionPrizeGradeEntity prizeGrade_result = new CompetitionPrizeGradeEntity();
        prizeLevel_result.setId(prizeLevelId);
        prizeGrade_result.setId(prizeGradeId);
        prizeLevel_result = prizeLevelService.findById(prizeLevel_result);
        prizeGrade_result = prizeGradeService.findById(prizeGrade_result);
        competitionEntity.setPrizeLevel(prizeLevel_result);
        competitionEntity.setPrizeGrade(prizeGrade_result);

        //赛事的信息
        MatchEntity matchEntity_result = new MatchEntity();
        matchEntity_result.setMatchId(matchId);
        matchEntity_result = matchService.findById(matchEntity_result);
        competitionEntity.setMatch(matchEntity_result);

        //指导老师的姓名及部门信息
        TeacherEntity teacherEntity_result = new TeacherEntity();
        teacherEntity_result.setTeacherId(teacherId);
        teacherEntity_result = teacherService.findById(teacherEntity_result);
        competitionEntity.setDeptId(teacherEntity_result.getDept().getDeptId());
        competitionEntity.setDeptName(teacherEntity_result.getDept().getDeptName());
        /**
         * 只有大设赛才有指导老师
         */
        if (matchEntity_result.getMatchId() == 1) {
            competitionEntity.setTeacher(teacherEntity_result);
        } else {
            competitionEntity.setTeacher(null);
        }

        competitionService.save(competitionEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        competitionService.delete(competitionEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        competitionEntity.setPrizeTime(new java.sql.Date(DateUtil.string2Date(itemPrizeTime,"yyyy").getTime()));

        //获奖信息
        CompetitionPrizeLevelEntity prizeLevel_result = new CompetitionPrizeLevelEntity();
        CompetitionPrizeGradeEntity prizeGrade_result = new CompetitionPrizeGradeEntity();
        prizeLevel_result.setId(prizeLevelId);
        prizeGrade_result.setId(prizeGradeId);
        prizeLevel_result = prizeLevelService.findById(prizeLevel_result);
        prizeGrade_result = prizeGradeService.findById(prizeGrade_result);
        competitionEntity.setPrizeLevel(prizeLevel_result);
        competitionEntity.setPrizeGrade(prizeGrade_result);

        //赛事的信息
        MatchEntity matchEntity_result = new MatchEntity();
        matchEntity_result.setMatchId(matchId);
        matchEntity_result = matchService.findById(matchEntity_result);
        competitionEntity.setMatch(matchEntity_result);

        //指导老师的姓名及部门信息
        TeacherEntity teacherEntity_result = new TeacherEntity();
        teacherEntity_result.setTeacherId(teacherId);
        teacherEntity_result = teacherService.findById(teacherEntity_result);
        competitionEntity.setDeptId(teacherEntity_result.getDept().getDeptId());
        competitionEntity.setDeptName(teacherEntity_result.getDept().getDeptName());
        /**
         * 只有大设赛才有指导老师
         */
        if (matchEntity_result.getMatchId() == 1) {
            competitionEntity.setTeacher(teacherEntity_result);
        } else {
            competitionEntity.setTeacher(null);
        }

        competitionService.update(competitionEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById() {

        CompetitionEntity byId = competitionService.findById(competitionEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = competitionService.findByPage(key, new PageBean<CompetitionEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 教师查询指导的所有比赛
     */
    public String findByTeacher() {

        PageBean byPage = competitionService.findByTeacher(teacherId, key, new PageBean<CompetitionEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 获奖老师查询
     */
    public String findByAwardee() {

        PageBean byAwardee = competitionService.findByAwardee(teacherName, key, new PageBean<CompetitionEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byAwardee.getRows()).put("count", byAwardee.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 部门管理员和领导查询本部门所有比赛
     */
    public String findByDept() {

        PageBean byPage = competitionService.findByDept(competitionEntity.getDeptId(), key, new PageBean<CompetitionEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 列表批量导出
     */
    public String findByExport() {

        /**
         * 1-系统管理员导出整个表 2-部门领导和部门负责人导出本学院全部 3-教师个人导出全部指导 4-根据id导出
         */
        Integer deptId =null;   //部门约束
        String[] id = null;     //主键约束
        Integer thisTeacherId = null;  //指导老师约束
        if (exportCode == 1) {
            deptId = null;
            id = null;
            thisTeacherId = null;
        } else if (exportCode == 2) {
            deptId = competitionEntity.getDeptId();
            id = null;
            thisTeacherId = null;
        } else if (exportCode == 3) {
            deptId = null;
            id = null;
            thisTeacherId = teacherId;
        } else if (exportCode == 4) {
            deptId = null;
            id = ids.split(",");
            thisTeacherId = null;
        }

        List<CompetitionEntity> list = competitionService.findByExport(deptId, id, thisTeacherId);

        r = R.ok().put("data", list);

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        competitionService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////

    public CompetitionEntity getCompetitionEntity() {
        return competitionEntity;
    }

    public void setCompetitionEntity(CompetitionEntity competitionEntity) {
        this.competitionEntity = competitionEntity;
    }

    public PageBean<CompetitionEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<CompetitionEntity> pageBean) {
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

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getPrizeLevelId() {
        return prizeLevelId;
    }

    public void setPrizeLevelId(Integer prizeLevelId) {
        this.prizeLevelId = prizeLevelId;
    }

    public Integer getPrizeGradeId() {
        return prizeGradeId;
    }

    public void setPrizeGradeId(Integer prizeGradeId) {
        this.prizeGradeId = prizeGradeId;
    }

    public String getItemPrizeTime() {
        return itemPrizeTime;
    }

    public void setItemPrizeTime(String itemPrizeTime) {
        this.itemPrizeTime = itemPrizeTime;
    }

    public String getItemMatchTime() {
        return itemMatchTime;
    }

    public void setItemMatchTime(String itemMatchTime) {
        this.itemMatchTime = itemMatchTime;
    }

    public String getItemApplyTime() {
        return itemApplyTime;
    }

    public void setItemApplyTime(String itemApplyTime) {
        this.itemApplyTime = itemApplyTime;
    }

    public Integer getExportCode() {
        return exportCode;
    }

    public void setExportCode(Integer exportCode) {
        this.exportCode = exportCode;
    }
}
