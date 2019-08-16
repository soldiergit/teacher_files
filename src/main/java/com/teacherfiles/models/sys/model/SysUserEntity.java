package com.teacherfiles.models.sys.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author soldier
 * @title: SysUserEntity
 * @projectName teacher_files
 * @date 19-7-5下午9:49
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "sys_user", schema = "teacher_files", catalog = "")
public class SysUserEntity {
    private int userId;
    private String loginAccount;
    private String loginPassword;
    private String userName;
    private Integer userStatus;
    private TeacherEntity teacher;  //单向一对一，可以从关联的一方去查询另一方，却不能反向查询
//    private Integer roleType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "login_account", nullable = true, length = 255)
    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    @Basic
    @Column(name = "login_password", nullable = true, length = 255)
    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_status", nullable = true)
    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
//    @Basic
//    @Column(name = "role_type", nullable = true)
//    public Integer getRoleType() {
//        return roleType;
//    }
//
//    public void setRoleType(Integer roleType) {
//        this.roleType = roleType;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserEntity that = (SysUserEntity) o;
        return userId == that.userId &&
                Objects.equals(loginAccount, that.loginAccount) &&
                Objects.equals(loginPassword, that.loginPassword) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userStatus, that.userStatus);
//                Objects.equals(roleType, that.roleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, loginAccount, loginPassword, userName, userStatus);
    }

    /**
     * 这样子就可以实现单向一对一了，对方实体中什么都不用该，因为只是单向的
     */
    @OneToOne
    @JoinColumn(name = "teacher_id")
    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }
}
