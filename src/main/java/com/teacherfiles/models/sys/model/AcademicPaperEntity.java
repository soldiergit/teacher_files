package com.teacherfiles.models.sys.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @author soldier
 * @title: AcademicPaperEntity
 * @projectName teacher_files
 * @date 19-7-5下午9:49
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "academic_paper", schema = "teacher_files", catalog = "")
public class AcademicPaperEntity {
    private int paperId;
    private String paperName;
    private Integer paperType;
    private String paperTitle;
    private Integer teacherType;
    private Integer signUnit;
    private String periodicalName;
    private String periodicalNumber;
    private Date publishTime;
    private TeacherEntity teacher;  //单向一对一，可以从关联的一方去查询另一方，却不能反向查询
    private AcademicPaperGradeEntity paperGrade;  //单向一对一，可以从关联的一方去查询另一方，却不能反向查询

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_id", nullable = false)
    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    @Basic
    @Column(name = "paper_name", nullable = true, length = 255)
    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    @Basic
    @Column(name = "paper_type", nullable = true)
    public Integer getPaperType() {
        return paperType;
    }

    public void setPaperType(Integer paperType) {
        this.paperType = paperType;
    }

    @Basic
    @Column(name = "paper_title", nullable = true, length = 255)
    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    @Basic
    @Column(name = "teacher_type", nullable = true)
    public Integer getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(Integer teacherType) {
        this.teacherType = teacherType;
    }

    @Basic
    @Column(name = "sign_unit", nullable = true)
    public Integer getSignUnit() {
        return signUnit;
    }

    public void setSignUnit(Integer signUnit) {
        this.signUnit = signUnit;
    }

    @Basic
    @Column(name = "periodical_name", nullable = true, length = 255)
    public String getPeriodicalName() {
        return periodicalName;
    }

    public void setPeriodicalName(String periodicalName) {
        this.periodicalName = periodicalName;
    }

    @Basic
    @Column(name = "periodical_number", nullable = true, length = 255)
    public String getPeriodicalNumber() {
        return periodicalNumber;
    }

    public void setPeriodicalNumber(String periodicalNumber) {
        this.periodicalNumber = periodicalNumber;
    }

    @Basic
    @Column(name = "publish_time", nullable = true)
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicPaperEntity that = (AcademicPaperEntity) o;
        return paperId == that.paperId &&
                Objects.equals(paperName, that.paperName) &&
                Objects.equals(paperType, that.paperType) &&
                Objects.equals(paperTitle, that.paperTitle) &&
                Objects.equals(teacherType, that.teacherType) &&
                Objects.equals(signUnit, that.signUnit) &&
                Objects.equals(periodicalName, that.periodicalName) &&
                Objects.equals(periodicalNumber, that.periodicalNumber) &&
                Objects.equals(publishTime, that.publishTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paperId, paperName, paperType, paperTitle, teacherType, signUnit, periodicalName, periodicalNumber, publishTime);
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

    @OneToOne
    @JoinColumn(name = "paper_grade_id")
    public AcademicPaperGradeEntity getPaperGrade() {
        return paperGrade;
    }

    public void setPaperGrade(AcademicPaperGradeEntity paperGrade) {
        this.paperGrade = paperGrade;
    }
}
