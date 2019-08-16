package com.teacherfiles.models.sys.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author soldier
 * @title: DeptEntity
 * @projectName teacher_files
 * @date 19-7-5下午9:49
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "dept", schema = "teacher_files", catalog = "")
public class DeptEntity {
    private int deptId;
    private String deptName;
    private String deptPerson;//部门负责人--自己输入
    private String deptPhone;//部门电话
    private DeptEntity parent;//上级部门
    private Set<DeptEntity> children;//下级部门

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id", nullable = false)
    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "dept_name", nullable = true, length = 255)
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Basic
    @Column(name = "dept_person", nullable = true, length = 255)
    public String getDeptPerson() {
        return deptPerson;
    }

    public void setDeptPerson(String deptPerson) {
        this.deptPerson = deptPerson;
    }

    @Basic
    @Column(name = "dept_phone", nullable = true, length = 255)
    public String getDeptPhone() {
        return deptPhone;
    }

    public void setDeptPhone(String deptPhone) {
        this.deptPhone = deptPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptEntity that = (DeptEntity) o;
        return deptId == that.deptId &&
                Objects.equals(deptName, that.deptName) &&
                Objects.equals(deptPerson, that.deptPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptId, deptName, deptPerson);
    }

    @ManyToOne(cascade=CascadeType.ALL)
    public DeptEntity getParent() {
        return parent;
    }

    public void setParent(DeptEntity parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    public Set<DeptEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<DeptEntity> children) {
        this.children = children;
    }

}
