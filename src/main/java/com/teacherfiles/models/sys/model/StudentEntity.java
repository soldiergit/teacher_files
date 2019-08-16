package com.teacherfiles.models.sys.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author soldier
 * @title: StudentEntity
 * @projectName stu_return_late_DB
 * @date 19-7-12下午5:56
 * @Email： 583403411@qq.com
 * @description: 弃用--原用于竞赛
 */
@Entity
@Table(name = "student", schema = "teacher_files", catalog = "")
public class StudentEntity {
    private int studentId;
    private String studentCode;
    private String studentName;
    private String studentPhone;
    private String studentEmail;
    private String major;
    private String className;
    private String jobContent;
    private Integer isPerson;
    private Integer itemId;//学生竞赛作品id
    private String itemName;//冗余数据
//    private CompetitionEntity raceItem;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "student_code", nullable = true, length = 255)
    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    @Basic
    @Column(name = "student_name", nullable = true, length = 255)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "student_phone", nullable = true, length = 255)
    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    @Basic
    @Column(name = "student_email", nullable = true, length = 255)
    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    @Basic
    @Column(name = "major", nullable = true, length = 255)
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Basic
    @Column(name = "class_name", nullable = true, length = 255)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "job_content", nullable = true, length = 255)
    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    @Basic
    @Column(name = "is_person", nullable = true)
    public Integer getIsPerson() {
        return isPerson;
    }

    public void setIsPerson(Integer isPerson) {
        this.isPerson = isPerson;
    }

    @Basic
    @Column(name = "item_id", nullable = true)
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "item_name", nullable = true, length = 255)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return studentId == that.studentId &&
                Objects.equals(studentCode, that.studentCode) &&
                Objects.equals(studentName, that.studentName) &&
                Objects.equals(studentPhone, that.studentPhone) &&
                Objects.equals(studentEmail, that.studentEmail) &&
                Objects.equals(major, that.major) &&
                Objects.equals(className, that.className) &&
                Objects.equals(jobContent, that.jobContent) &&
                Objects.equals(isPerson, that.isPerson) &&
                Objects.equals(itemId, that.itemId) &&
                Objects.equals(itemName, that.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentCode, studentName, studentPhone, studentEmail, major, className, jobContent, isPerson, itemId, itemName);
    }
//
//    @ManyToOne(cascade=CascadeType.ALL)
//    public CompetitionEntity getRaceItem() {
//        return raceItem;
//    }
//
//    public void setRaceItem(CompetitionEntity raceItem) {
//        this.raceItem = raceItem;
//    }
}
