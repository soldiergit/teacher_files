package com.teacherfiles.models.sys.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @author soldier
 * @title: TeacherItemEntity
 * @projectName teacher_files
 * @date 19-7-5下午9:49
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "teacher_item", schema = "teacher_files", catalog = "")
public class TeacherItemEntity {
    private int itemId;
    private String itemName;
    private Integer itemType;//项目类型--大类：教改和科研
    private String contractNumber;
    private Double itemMoney;
    private String itemMember;//成员，多个
    private String memberName;//冗余数据
    private Date createTime;
    private Date startTime;
    private Date endTime;
    private TeacherEntity itemPerson;  //项目负责人；单向一对一，可以从关联的一方去查询另一方，却不能反向查询
    private TeacherItemCategoryEntity itemCategory;  //项目级别和类别；单向一对一，可以从关联的一方去查询另一方，却不能反向查询
    private TeacherItemLevelEntity itemLevel;
    /**
     * 0:根据项目名称等String类型查询
     * 1：根据主持人id查询
     * 2：根据主持人所属教研室查询
     * 3：根据项目类型查询--itemType
     * 4：根据项目级别--itemCategory
     * 5：根据项目类别--itemLevel
     */
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
    @Column(name = "item_type", nullable = true)
    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    @Basic
    @Column(name = "contract_number", nullable = true, length = 255)
    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    @Basic
    @Column(name = "item_money", nullable = true, precision = 2)
    public Double getItemMoney() {
        return itemMoney;
    }

    public void setItemMoney(Double itemMoney) {
        this.itemMoney = itemMoney;
    }

    @Basic
    @Column(name = "item_member", nullable = true, length = 255)
    public String getItemMember() {
        return itemMember;
    }

    public void setItemMember(String itemMember) {
        this.itemMember = itemMember;
    }

    @Basic
    @Column(name = "member_name", nullable = true, length = 255)
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "start_time", nullable = true)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherItemEntity that = (TeacherItemEntity) o;
        return itemId == that.itemId &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(itemType, that.itemType) &&
                Objects.equals(contractNumber, that.contractNumber) &&
                Objects.equals(itemMoney, that.itemMoney) &&
                Objects.equals(memberName, that.memberName) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, itemType, contractNumber, itemMoney, itemMember, itemMember, createTime, startTime, endTime);
    }

    /**
     * 这样子就可以实现单向一对一了，对方实体中什么都不用该，因为只是单向的
     */
    @OneToOne
    @JoinColumn(name = "item_person")
    public TeacherEntity getItemPerson() {
        return itemPerson;
    }

    public void setItemPerson(TeacherEntity itemPerson) {
        this.itemPerson = itemPerson;
    }

    @OneToOne
    @JoinColumn(name = "item_category_id")
    public TeacherItemCategoryEntity getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(TeacherItemCategoryEntity itemCategory) {
        this.itemCategory = itemCategory;
    }

    @OneToOne
    @JoinColumn(name = "item_level_id")
    public TeacherItemLevelEntity getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(TeacherItemLevelEntity itemLevel) {
        this.itemLevel = itemLevel;
    }
}
