package com.teacherfiles.models.sys.action.sysuser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.SysUserEntity;
import com.teacherfiles.models.sys.service.sysuser.SysUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author soldier
 * @title: SysUserAction
 * @projectName teacher_files
 * @description:
 * @date 19-7-5下午5:03
 */
public class SysUserAction extends ActionSupport implements ModelDriven<SysUserEntity> {

    @Autowired
    private SysUserService sysUserService;
    //日志
    private static Logger logger = Logger.getLogger(SysUserAction.class);
    //模型驱动
    private SysUserEntity sysUserEntity = new SysUserEntity();
    //分页
    private PageBean<SysUserEntity> pageBean = new PageBean<>();
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
    //教师id
    private Integer teacherId;
    @Override
    public SysUserEntity getModel() {
        return sysUserEntity;
    }

    /////////////////////////////////////////
    /**
     * 添加
     */
    public String saveSysUser(){

        sysUserService.saveUser(sysUserEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String deleteSysUser(){

        sysUserService.deleteUser(sysUserEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String updateSysUser(){

        sysUserService.updateUser(sysUserEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findOneSysUser(){

        SysUserEntity sysUserEntitys = sysUserService.findOneUser(sysUserEntity);

        r = R.ok().put("data",sysUserEntitys);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findAllUser(){

        PageBean allUser = sysUserService.findAllUser(key, new PageBean<SysUserEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",allUser.getRows());

        return SUCCESS;
    }
    /**
     * 批量删除
     */
    public String deleteAllSysUser(){


        String[] id = ids.split(",");

        sysUserService.deleteAllUser(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////

    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    public PageBean<SysUserEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<SysUserEntity> pageBean) {
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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
