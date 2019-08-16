package com.teacherfiles.models.sys.action.student;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.StudentEntity;
import com.teacherfiles.models.sys.service.competition.CompetitionService;
import com.teacherfiles.models.sys.service.student.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author soldier
 * @title: TeacherItemAction
 * @projectName teacher_files
 * @date 19-7-5下午5:16
 * @Email： 583403411@qq.com
 * @description:
 */
public class StudentAction extends ActionSupport implements ModelDriven<StudentEntity> {

    @Autowired
    private StudentService studentService;
    //日志
    private static Logger logger = Logger.getLogger(StudentAction.class);
    //模型驱动
    private StudentEntity studentEntity = new StudentEntity();
    //分页
    private PageBean<StudentEntity> pageBean = new PageBean<>();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //批量删除id
    private String ids;

    @Override
    public StudentEntity getModel() {
        return studentEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        studentService.save(studentEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        studentService.delete(studentEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        studentService.update(studentEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById() {

        StudentEntity byId = studentService.findById(studentEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = studentService.findByPage(key, new PageBean<StudentEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 根据项目查询学生查询
     */
    public String findByRace() {

        PageBean byPage = studentService.findByRace(studentEntity.getItemId(), key, new PageBean<StudentEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }


    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        studentService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public PageBean<StudentEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<StudentEntity> pageBean) {
        this.pageBean = pageBean;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
