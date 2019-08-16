package com.teacherfiles.models.sys.action.dept;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.DeptEntity;
import com.teacherfiles.models.sys.model.TeacherEntity;
import com.teacherfiles.models.sys.service.dept.DeptService;
import com.teacherfiles.models.sys.service.teacher.TeacherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author soldier
 * @title: DeptAction
 * @projectName teacher_files
 * @date 19-7-5下午5:16
 * @Email： 583403411@qq.com
 * @description:
 */
public class DeptAction extends ActionSupport implements ModelDriven<DeptEntity> {

    @Autowired
    private DeptService deptService;
    @Autowired
    private TeacherService teacherService;
    //日志
    private static Logger logger = Logger.getLogger(DeptAction.class);
    //模型驱动
    private DeptEntity deptEntity = new DeptEntity();
    //分页
    private PageBean<DeptEntity> pageBean = new PageBean<>();
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
    //上级部门
    private String parentId;
    //部门成员id
    private String deptMembers;
    //用户id
    private Integer teacherId;

    @Override
    public DeptEntity getModel() {
        return deptEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        //上级部门
        if (parentId!=null && parentId!="") {
            DeptEntity dept_result = new DeptEntity();
            dept_result.setDeptId(Integer.valueOf(parentId));
            dept_result = deptService.findById(dept_result);
            deptEntity.setParent(dept_result);
        }

        deptService.save(deptEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        deptService.delete(deptEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        //上级部门
        if (parentId!=null && parentId!="") {
            DeptEntity dept_result = new DeptEntity();
            dept_result.setDeptId(Integer.valueOf(parentId));
            dept_result = deptService.findById(dept_result);
            deptEntity.setParent(dept_result);
        }

        deptService.update(deptEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {

        DeptEntity byId = deptService.findById(deptEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询--系统管理员
     */
    public String findByPage() {

        PageBean byPage = deptService.findByPage(key, new PageBean<DeptEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 查询本学院的教研室--部门管理员和领导
     */
    public String findByDept() {

        PageBean byPage = deptService.findByDept(deptEntity.getDeptId(), key, new PageBean<DeptEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 获取全部，用于下拉框渲染
     */
    public String findAll() {

        List<DeptEntity> list = deptService.findAll();

        r = R.ok().put("data", list);

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 根据一级部门查询全部从属部门，用于下拉框渲染
     */
    public String findSubordinate() {

        List<DeptEntity> subordinate = deptService.findSubordinate(deptEntity.getDeptId());

        r = R.ok().put("data", subordinate);

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        deptService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////

    public DeptEntity getDeptEntity() {
        return deptEntity;
    }

    public void setDeptEntity(DeptEntity deptEntity) {
        this.deptEntity = deptEntity;
    }

    public PageBean<DeptEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<DeptEntity> pageBean) {
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

    public String getDeptMembers() {
        return deptMembers;
    }

    public void setDeptMembers(String deptMembers) {
        this.deptMembers = deptMembers;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
