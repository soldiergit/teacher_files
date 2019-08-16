package com.teacherfiles.models.sys.action.sysrole;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.SysMenuEntity;
import com.teacherfiles.models.sys.model.SysRoleEntity;
import com.teacherfiles.models.sys.service.sysmenu.MenuService;
import com.teacherfiles.models.sys.service.sysrole.SysRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author soldier
 * @title: RoleAction
 * @projectName Role_files
 * @date 19-7-5下午6:09
 * @Email： 583403411@qq.com
 * @description:
 */
public class RoleAction extends ActionSupport implements ModelDriven<SysRoleEntity> {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private MenuService menuService;
    //日志
    private static Logger logger = Logger.getLogger(RoleAction.class);
    //模型驱动
    private SysRoleEntity sysRoleEntity = new SysRoleEntity();
    //分页
    private PageBean<SysRoleEntity> pageBean = new PageBean<>();
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
    public SysRoleEntity getModel() {
        return sysRoleEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save(){

        sysRoleService.save(sysRoleEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete(){

        logger.info("删除教师："+sysRoleEntity);

        sysRoleService.delete(sysRoleEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update(){

        //角色-菜单集合不变
        SysRoleEntity roleEntity_result = sysRoleService.findById(sysRoleEntity);
        sysRoleEntity.setMenuSet(roleEntity_result.getMenuSet());

        sysRoleService.update(sysRoleEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 角色-菜单控制
     */
    public String updateMenu(){

        //修改菜单
        Set<SysMenuEntity> menuSet = new HashSet<>();
        String[] id = ids.split(",");
        SysMenuEntity menuEntity;
        for (String ID : id) {
            menuEntity = new SysMenuEntity();
            menuEntity.setMenuId(Integer.valueOf(ID));
            menuEntity = menuService.findById(menuEntity);
            menuSet.add(menuEntity);
        }
        sysRoleEntity.setMenuSet(menuSet);

        sysRoleService.update(sysRoleEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById(){

        SysRoleEntity oneRole = sysRoleService.findById(sysRoleEntity);

        r = R.ok().put("data",oneRole);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage(){

        logger.info("搜索："+key);

        PageBean allRole = sysRoleService.findByPage(key, new PageBean<SysRoleEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data",allRole.getRows()).put("count",allRole.getTotal());

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 用于添加教师和修改教师角色时的复选框渲染
     */
    public String findByOtherRole(){

        List list = sysRoleService.findByOtherRole();

        r = R.ok().put("data",list);

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 用于系统管理员查看角色列表
     */
    public String findBySysAdmin(){

        List list = sysRoleService.findBySysAdmin();

        r = R.ok().put("data",list);

        logger.info("查询列表："+r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch(){

        String[] id = ids.split(",");

        sysRoleService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }
    
    /////////////////////////////////////////

    public SysRoleEntity getSysRoleEntity() {
        return sysRoleEntity;
    }

    public void setSysRoleEntity(SysRoleEntity sysRoleEntity) {
        this.sysRoleEntity = sysRoleEntity;
    }

    public PageBean<SysRoleEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<SysRoleEntity> pageBean) {
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
