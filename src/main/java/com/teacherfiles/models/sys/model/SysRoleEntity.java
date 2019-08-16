package com.teacherfiles.models.sys.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author soldier
 * @title: SysRoleEntity
 * @projectName teacher_files
 * @date 19-7-5下午9:49
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "sys_role", schema = "teacher_files", catalog = "")
public class SysRoleEntity {
    private int roleId;
    private String roleName;
    private Integer canLook;  //是否允许除系统管理员之外的角色查看
    private Set<SysMenuEntity> menuSet;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name", nullable = true, length = 255)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "can_look", nullable = true)
    public Integer getCanLook() {
        return canLook;
    }

    public void setCanLook(Integer canLook) {
        this.canLook = canLook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRoleEntity that = (SysRoleEntity) o;
        return roleId == that.roleId &&
                Objects.equals(roleName, that.roleName) &&
                Objects.equals(canLook, that.canLook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, canLook);
    }

    /**
     * FetchType.LAZY：懒加载，加载一个实体时，定义懒加载的属性不会马上从数据库中加载
     * FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_menu", catalog = "", schema = "teacher_files", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "menu_id"))
    public Set<SysMenuEntity> getMenuSet() {
        return menuSet;
    }

    public void setMenuSet(Set<SysMenuEntity> menuSet) {
        this.menuSet = menuSet;
    }
}
