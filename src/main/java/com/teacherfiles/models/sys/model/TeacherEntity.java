package com.teacherfiles.models.sys.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @author soldier
 * @title: TeacherEntity
 * @projectName teacher_files
 * @date 19-7-5下午9:49
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "teacher", schema = "teacher_files", catalog = "")
public class TeacherEntity {
    private int teacherId;
    private String teacherCode;
    private String teacherName;
    private Integer teacherSex;
    private Date teacherBirth;
    private Date entryTime;//入职时间
    private String teacherImg;
    private String highEdu;
    private String firstEdu;
    private String technicalPost;
    private String administPost;
    private String teacherResume;
    private String other;
    private Integer canLook;  //是否允许除系统管理员之外的角色查看
    private String roleIds;//角色
    private String roleNames;//冗余数据
    private String unitIds;//所属教研室--即parent不为空的dept部门
    private DeptEntity dept;  //单向一对一，可以从关联的一方去查询另一方，却不能反向查询
//    private Set<TeacherItemEntity> researchSet;//科研项目

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id", nullable = false)
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "teacher_code", nullable = true, length = 255)
    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    @Basic
    @Column(name = "teacher_name", nullable = true, length = 255)
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Basic
    @Column(name = "teacher_sex", nullable = true)
    public Integer getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(Integer teacherSex) {
        this.teacherSex = teacherSex;
    }

    @Basic
    @Column(name = "teacher_birth", nullable = true)
    public Date getTeacherBirth() {
        return teacherBirth;
    }

    public void setTeacherBirth(Date teacherBirth) {
        this.teacherBirth = teacherBirth;
    }

    @Basic
    @Column(name = "entry_time", nullable = true)
    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    @Basic
    @Column(name = "teacher_img", nullable = true, length = 255)
    public String getTeacherImg() {
        return teacherImg;
    }

    public void setTeacherImg(String teacherImg) {
        this.teacherImg = teacherImg;
    }

    @Basic
    @Column(name = "high_edu", nullable = true, length = 255)
    public String getHighEdu() {
        return highEdu;
    }

    public void setHighEdu(String highEdu) {
        this.highEdu = highEdu;
    }

    @Basic
    @Column(name = "first_edu", nullable = true, length = 255)
    public String getFirstEdu() {
        return firstEdu;
    }

    public void setFirstEdu(String firstEdu) {
        this.firstEdu = firstEdu;
    }

    @Basic
    @Column(name = "technical_post", nullable = true, length = 255)
    public String getTechnicalPost() {
        return technicalPost;
    }

    public void setTechnicalPost(String technicalPost) {
        this.technicalPost = technicalPost;
    }

    @Basic
    @Column(name = "administ_post", nullable = true, length = 255)
    public String getAdministPost() {
        return administPost;
    }

    public void setAdministPost(String administPost) {
        this.administPost = administPost;
    }

    @Basic
    @Column(name = "teacher_resume", nullable = true, length = 2000)
    public String getTeacherResume() {
        return teacherResume;
    }

    public void setTeacherResume(String teacherResume) {
        this.teacherResume = teacherResume;
    }

    @Basic
    @Column(name = "other", nullable = true, length = 2000)
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Basic
    @Column(name = "can_look", nullable = true)
    public Integer getCanLook() {
        return canLook;
    }

    public void setCanLook(Integer canLook) {
        this.canLook = canLook;
    }

    @Basic
    @Column(name = "role_ids", nullable = true, length = 255)
    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    @Basic
    @Column(name = "role_names", nullable = true, length = 255)
    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    @Basic
    @Column(name = "unit_ids", nullable = true, length = 255)
    public String getUnitIds() {
        return unitIds;
    }

    public void setUnitIds(String unitIds) {
        this.unitIds = unitIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntity that = (TeacherEntity) o;
        return teacherId == that.teacherId &&
                Objects.equals(teacherCode, that.teacherCode) &&
                Objects.equals(teacherName, that.teacherName) &&
                Objects.equals(teacherSex, that.teacherSex) &&
                Objects.equals(teacherBirth, that.teacherBirth) &&
                Objects.equals(teacherImg, that.teacherImg) &&
                Objects.equals(highEdu, that.highEdu) &&
                Objects.equals(firstEdu, that.firstEdu) &&
                Objects.equals(technicalPost, that.technicalPost) &&
                Objects.equals(administPost, that.administPost) &&
                Objects.equals(teacherResume, that.teacherResume) &&
                Objects.equals(other, that.other) &&
                Objects.equals(roleNames, that.roleNames) &&
                Objects.equals(roleIds, that.roleIds) &&
                Objects.equals(unitIds, that.unitIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teacherCode, teacherName, teacherSex, teacherBirth, teacherImg, highEdu, firstEdu, technicalPost, administPost, teacherResume, other, roleIds, roleNames, unitIds);
    }

    /**
     * 这样子就可以实现单向一对一了，对方实体中什么都不用该，因为只是单向的
     */
    @OneToOne
    @JoinColumn(name = "dept_id")
    public DeptEntity getDept() {
        return dept;
    }

    public void setDept(DeptEntity dept) {
        this.dept = dept;
    }
//    @ManyToMany(mappedBy = "memberSet", fetch = FetchType.EAGER)
//    public Set<TeacherItemEntity> getResearchSet() {
//        return researchSet;
//    }
//
//    public void setResearchSet(Set<TeacherItemEntity> researchSet) {
//        this.researchSet = researchSet;
//    }
}
