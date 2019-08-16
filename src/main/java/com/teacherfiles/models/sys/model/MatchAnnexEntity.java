package com.teacherfiles.models.sys.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author soldier
 * @title: SysMatchAnnexEntity
 * @projectName stu_return_late_DB
 * @date 19-7-18下午2:56
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "sys_match_annex", schema = "teacher_files", catalog = "")
public class MatchAnnexEntity {
    private int matchAnnexId;
    private String filePath;
    private String fileName;
    private String fileType;
    private Integer matchId;  //伪外键
//    private MatchEntity match;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_annex_id", nullable = false)
    public int getMatchAnnexId() {
        return matchAnnexId;
    }

    public void setMatchAnnexId(int matchAnnexId) {
        this.matchAnnexId = matchAnnexId;
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
    @Column(name = "match_id", nullable = true)
    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchAnnexEntity that = (MatchAnnexEntity) o;
        return matchAnnexId == that.matchAnnexId &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(matchId, that.matchId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchAnnexId, filePath, fileName, fileType, matchId);
    }
//
//    @ManyToOne(targetEntity = MatchEntity.class, cascade=CascadeType.ALL)
//    @JoinColumn(name = "match_match_id")
//    public MatchEntity getMatch() {
//        return match;
//    }
//
//    public void setMatch(MatchEntity match) {
//        this.match = match;
//    }
}
