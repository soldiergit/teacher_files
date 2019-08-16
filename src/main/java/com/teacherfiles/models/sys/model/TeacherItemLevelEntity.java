package com.teacherfiles.models.sys.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName: teacher_files_db
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-8-3 下午5:34
 * @Describe:
 **/
@Entity
@Table(name = "teacher_item_level", schema = "teacher_files", catalog = "")
public class TeacherItemLevelEntity {
    private int id;
    private String title;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherItemLevelEntity that = (TeacherItemLevelEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
