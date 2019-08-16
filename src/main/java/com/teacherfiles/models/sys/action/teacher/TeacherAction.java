package com.teacherfiles.models.sys.action.teacher;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.DeptEntity;
import com.teacherfiles.models.sys.model.SysUserEntity;
import com.teacherfiles.models.sys.model.TeacherEntity;
import com.teacherfiles.models.sys.service.dept.DeptService;
import com.teacherfiles.models.sys.service.sysrole.SysRoleService;
import com.teacherfiles.models.sys.service.sysuser.SysUserService;
import com.teacherfiles.models.sys.service.teacher.TeacherService;
import com.teacherfiles.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author soldier
 * @title: TeacherAction
 * @projectName teacher_files
 * @date 19-7-5下午6:09
 * @Email： 583403411@qq.com
 * @description:
 */
public class TeacherAction extends ActionSupport implements ModelDriven<TeacherEntity> {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private DeptService deptService;
    //日志
    private static Logger logger = Logger.getLogger(TeacherAction.class);
    //模型驱动
    private TeacherEntity teacherEntity = new TeacherEntity();
    //分页
    private PageBean<TeacherEntity> pageBean = new PageBean<>();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //部门id
    private Integer deptId;
    //批量删除id
    private String ids;
    //教师对应的用户id
    private Integer userId;
    //生日
    private String theTeacherBirth;
    //入职时间
    private String theTeacherEntryTime;

    @Override
    public TeacherEntity getModel() {
        return teacherEntity;
    }

    /**
     * 添加
     */
    public String save(){

        if (teacherEntity.getCanLook() == null) teacherEntity.setCanLook(1);//是否允许除系统管理员之外的角色查看

        teacherEntity.setTeacherBirth(new java.sql.Date(DateUtil.string2Date(theTeacherBirth,"yyyy-MM-dd").getTime()));
        teacherEntity.setEntryTime(new java.sql.Date(DateUtil.string2Date(theTeacherEntryTime,"yyyy-MM-dd").getTime()));

        //获取部门信息
        DeptEntity deptEntity_result = new DeptEntity();
        deptEntity_result.setDeptId(deptId);
        deptEntity_result = deptService.findById(deptEntity_result);
        teacherEntity.setDept(deptEntity_result);

        teacherService.save(teacherEntity);//保存教师

        //新增用户
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setLoginAccount(teacherEntity.getTeacherCode());
        sysUserEntity.setLoginPassword("123456");
        sysUserEntity.setUserName(teacherEntity.getTeacherName());
        sysUserEntity.setUserStatus(1);
        sysUserEntity.setTeacher(teacherEntity);
        sysUserService.saveUser(sysUserEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete(){

        logger.info("删除教师："+teacherEntity);

       teacherService.delete(teacherEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update(){

        //获取部门信息
        DeptEntity deptEntity_result = new DeptEntity();
        deptEntity_result.setDeptId(deptId);
        deptEntity_result = deptService.findById(deptEntity_result);
        teacherEntity.setDept(deptEntity_result);

        if (teacherEntity.getCanLook() == null) teacherEntity.setCanLook(1);//是否允许除系统管理员之外的角色查看
        teacherEntity.setTeacherBirth(new java.sql.Date(DateUtil.string2Date(theTeacherBirth,"yyyy-MM-dd").getTime()));
        teacherEntity.setEntryTime(new java.sql.Date(DateUtil.string2Date(theTeacherEntryTime,"yyyy-MM-dd").getTime()));

        teacherService.update(teacherEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改教师角色
     */
    public String updateRole(){

        //获取原角色信息
        TeacherEntity teacherEntity_result = teacherService.findById(teacherEntity);
        teacherEntity_result.setRoleIds(teacherEntity.getRoleIds());
        teacherEntity_result.setRoleNames(teacherEntity.getRoleNames());

        teacherService.update(teacherEntity_result);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findById(){

        TeacherEntity oneTeacher = teacherService.findById(teacherEntity);

        r = R.ok().put("data",oneTeacher);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage(){

        logger.info("搜索："+key);

        PageBean allTeacher = teacherService.findByPage(key, new PageBean<TeacherEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",allTeacher.getRows()).put("count",allTeacher.getTotal());

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 根据部门查询
     */
    public String findByDept(){

        //如果是系统管理员,部门为空
        PageBean byDeptTeacher = teacherService.findByDept(deptId, key, new PageBean<TeacherEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",byDeptTeacher.getRows()).put("count",byDeptTeacher.getTotal());

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 根据部门查询
     */
    public String findDeptAdmin(){

        //如果是系统管理员,部门为空
        PageBean allDeptAdmin = teacherService.findDeptAdmin(key, new PageBean<TeacherEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",allDeptAdmin.getRows()).put("count",allDeptAdmin.getTotal());

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 系统管理员--查询所以教师
     */
    public String findAll(){

        List list = teacherService.findAll();

        r = R.ok().put("data",list);

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 部门管理员和领导--查询所以教师
     */
    public String findAllByDept(){

        List list = teacherService.findAllByDept(deptId);

        r = R.ok().put("data",list);

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch(){

        String[] id = ids.split(",");

        teacherService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }
    
    /////////////////////////////////////////

    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    public PageBean<TeacherEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TeacherEntity> pageBean) {
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

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTheTeacherBirth() {
        return theTeacherBirth;
    }

    public void setTheTeacherBirth(String theTeacherBirth) {
        this.theTeacherBirth = theTeacherBirth;
    }

    public String getTheTeacherEntryTime() {
        return theTeacherEntryTime;
    }

    public void setTheTeacherEntryTime(String theTeacherEntryTime) {
        this.theTeacherEntryTime = theTeacherEntryTime;
    }
}
