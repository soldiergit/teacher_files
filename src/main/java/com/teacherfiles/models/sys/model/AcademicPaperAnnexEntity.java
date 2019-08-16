package com.teacherfiles.models.sys.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author soldier
 * @title: AcademicPaperAnnexEntity
 * @projectName stu_return_late_DB
 * @date 19-7-18下午2:56
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "academic_paper_annex", schema = "teacher_files", catalog = "")
public class AcademicPaperAnnexEntity {
    private int paperAnnexId;
    private String filePath;
    private String fileName;
    private String fileType;
    private Integer paperId;  //伪外键
//    private AcademicPaperEntity paper;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_annex_id", nullable = false)
    public int getPaperAnnexId() {
        return paperAnnexId;
    }

    public void setPaperAnnexId(int paperAnnexId) {
        this.paperAnnexId = paperAnnexId;
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
    @Column(name = "paper_id", nullable = true)
    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicPaperAnnexEntity that = (AcademicPaperAnnexEntity) o;
        return paperAnnexId == that.paperAnnexId &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(paperId, that.paperId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paperAnnexId, filePath, fileName, fileType, paperId);
    }

//    @ManyToOne(targetEntity = AcademicPaperEntity.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "paper_paper_id")
//    public AcademicPaperEntity getPaper() {
//        return paper;
//    }
//
//    public void setPaper(AcademicPaperEntity paper) {
//        this.paper = paper;
//    }
}
