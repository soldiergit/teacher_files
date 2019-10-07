package com.teacherfiles.models.sys.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author soldier
 * @title: TeacherItemAnnexEntity
 * @projectName stu_return_late_DB
 * @date 19-7-18下午2:56
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "teacher_item_annex", schema = "teacher_files", catalog = "")
public class TeacherItemAnnexEntity {
    private int itemAnnexId;
    private String filePath;
    private String fileName;
    private String fileType;
    private Integer itemId;  //伪外键


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_annex_id", nullable = false)
    public int getItemAnnexId() {
        return itemAnnexId;
    }

    public void setItemAnnexId(int itemAnnexId) {
        this.itemAnnexId = itemAnnexId;
    }

    @Basic
    @Column(name = "file_path", nullable = true, length = 255)
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Basic
    @Column(name = "file_name", nullable = true, length = 255)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "file_type", nullable = true, length = 255)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "item_id", nullable = true)
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherItemAnnexEntity that = (TeacherItemAnnexEntity) o;
        return itemAnnexId == that.itemAnnexId &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemAnnexId, filePath, fileName, fileType, itemId);
    }
}
