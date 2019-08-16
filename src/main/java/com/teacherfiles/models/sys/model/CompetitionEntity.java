package com.teacherfiles.models.sys.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @author soldier
 * @title: CompetitionEntity
 * @projectName teacher_files
 * @date 19-7-5下午9:49
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "competition", schema = "teacher_files", catalog = "")
public class CompetitionEntity {
    private int itemId;
    private String itemName;
    private Date prizeTime;
    private String prizeImg;
    private Integer deptId;//保存添加用户的部门id
    private String deptName;//冗余数据
    private String awardee;  //获奖人
    private MatchEntity match;  //所属赛事；单向一对一，可以从关联的一方去查询另一方，却不能反向查询
    private TeacherEntity teacher;  //指导老师；单向一对一，可以从关联的一方去查询另一方，却不能反向查询
    private CompetitionPrizeGradeEntity prizeGrade;  //单向一对一，可以从关联的一方去查询另一方，却不能反向查询
    private CompetitionPrizeLevelEntity prizeLevel;  //单向一对一，可以从关联的一方去查询另一方，却不能反向查询

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
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

    @Basic
    @Column(name = "prize_time", nullable = true)
    public Date getPrizeTime() {
        return prizeTime;
    }

    public void setPrizeTime(Date prizeTime) {
        this.prizeTime = prizeTime;
    }

    @Basic
    @Column(name = "prize_img", nullable = true, length = 255)
    public String getPrizeImg() {
        return prizeImg;
    }

    public void setPrizeImg(String prizeImg) {
        this.prizeImg = prizeImg;
    }

    @Basic
    @Column(name = "dept_id", nullable = true)
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
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
    @Column(name = "awardee", nullable = true, length = 255)
    public String getAwardee() {
        return awardee;
    }

    public void setAwardee(String awardee) {
        this.awardee = awardee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetitionEntity that = (CompetitionEntity) o;
        return itemId == that.itemId &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(prizeTime, that.prizeTime) &&
                Objects.equals(prizeImg, that.prizeImg) &&
                Objects.equals(prizeLevel, that.prizeLevel) &&
                Objects.equals(prizeGrade, that.prizeGrade) &&
                Objects.equals(deptId, that.deptId) &&
                Objects.equals(deptName, that.deptName) &&
                Objects.equals(awardee, that.awardee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, prizeTime, prizeImg, prizeLevel, prizeGrade, deptId, deptName, awardee);
    }

    /**
     * 这样子就可以实现单向一对一了，对方实体中什么都不用该，因为只是单向的
     */
    @OneToOne
    @JoinColumn(name = "match_id")
    public MatchEntity getMatch() {
        return match;
    }

    public void setMatch(MatchEntity match) {
        this.match = match;
    }

    @OneToOne
    @JoinColumn(name = "teacher_id")
    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    @OneToOne
    @JoinColumn(name = "prize_grade_id")
    public CompetitionPrizeGradeEntity getPrizeGrade() {
        return prizeGrade;
    }

    public void setPrizeGrade(CompetitionPrizeGradeEntity prizeGrade) {
        this.prizeGrade = prizeGrade;
    }

    @OneToOne
    @JoinColumn(name = "prize_level_id")
    public CompetitionPrizeLevelEntity getPrizeLevel() {
        return prizeLevel;
    }

    public void setPrizeLevel(CompetitionPrizeLevelEntity prizeLevel) {
        this.prizeLevel = prizeLevel;
    }
}
