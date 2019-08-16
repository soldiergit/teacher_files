package com.teacherfiles.models.sys.action.sysmenu;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.SysMenuEntity;
import com.teacherfiles.models.sys.model.SysRoleEntity;
import com.teacherfiles.models.sys.model.TeacherEntity;
import com.teacherfiles.models.sys.service.sysmenu.MenuService;
import com.teacherfiles.models.sys.service.sysrole.SysRoleService;
import com.teacherfiles.models.sys.service.teacher.TeacherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author soldier
 * @title: MenuAction
 * @projectName teacher_files
 * @date 19-7-5下午6:00
 * @Email： 583403411@qq.com
 * @description:
 */
public class MenuAction extends ActionSupport implements ModelDriven<SysMenuEntity> {

    @Autowired
    private MenuService menuService;
    @Autowired
    private TeacherService teacherService;
    //日志
    private static Logger logger = Logger.getLogger(MenuAction.class);
    //模型驱动
    private SysMenuEntity sysMenuEntity = new SysMenuEntity();
    //分页
    private PageBean<SysMenuEntity> pageBean = new PageBean<>();
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
    //用户的角色id
    private String roleIds;

    @Override
    public SysMenuEntity getModel() {
        return sysMenuEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        sysMenuEntity.setSpread((byte) 0);

        menuService.save(sysMenuEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        menuService.delete(sysMenuEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        sysMenuEntity.setSpread((byte) 0);

        menuService.update(sysMenuEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById() {


        SysMenuEntity byId = menuService.findById(sysMenuEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = menuService.findByPage(key, new PageBean<SysMenuEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 查询全部
     */
    public String findAll() {

        List all = menuService.findAll();

        r = R.ok().put("data", all);

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        menuService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 获取菜单
     * @return
     */
    public String getMenuByRoleIds() {

        logger.info("roleIds：" + roleIds);
        if (roleIds==null || "null".equals(roleIds)) return SUCCESS;
        String[] id = roleIds.split(",");

        List<SysMenuEntity> menuByRoleType = menuService.getMenuByRoleType(id);

        r = R.ok().put("contentManagement", menuByRoleType);

        logger.info("菜单信息：" + r);

        return SUCCESS;

    }

    /////////////////////////////////////////

    public SysMenuEntity getSysMenuEntity() {
        return sysMenuEntity;
    }

    public void setSysMenuEntity(SysMenuEntity sysMenuEntity) {
        this.sysMenuEntity = sysMenuEntity;
    }

    public PageBean<SysMenuEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<SysMenuEntity> pageBean) {
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

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
