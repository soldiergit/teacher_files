package com.teacherfiles.models.sys.action.academicPaper;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.AcademicPaperAnnexEntity;
import com.teacherfiles.models.sys.model.AcademicPaperEntity;
import com.teacherfiles.models.sys.model.AcademicPaperGradeEntity;
import com.teacherfiles.models.sys.model.TeacherEntity;
import com.teacherfiles.models.sys.service.academicPaper.AcademicPaperService;
import com.teacherfiles.models.sys.service.academicPaperAnnex.AcademicPaperAnnexService;
import com.teacherfiles.models.sys.service.academicPaperGrade.AcademicPaperGradeService;
import com.teacherfiles.models.sys.service.teacher.TeacherService;
import com.teacherfiles.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author soldier
 * @title: AcademicPaperAction
 * @projectName teacher_files
 * @date 19-7-5下午5:16
 * @Email： 583403411@qq.com
 * @description:
 */
public class AcademicPaperAction extends ActionSupport implements ModelDriven<AcademicPaperEntity> {

    @Autowired
    private AcademicPaperService academicPaperService;
    @Autowired
    private AcademicPaperAnnexService academicPaperAnnexService;
    @Autowired
    private AcademicPaperGradeService academicPaperGradeService;
    @Autowired
    private TeacherService teacherService;
    //日志
    private static Logger logger = Logger.getLogger(AcademicPaperAction.class);
    //模型驱动
    private AcademicPaperEntity academicPaperEntity = new AcademicPaperEntity();
    //分页
    private PageBean<AcademicPaperEntity> pageBean = new PageBean<>();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //作者id
    private Integer teacherId;
    //批量删除id
    private String ids;
    //论文等级id
    private Integer paperGradeId;
    //论文发布时间
    private String paperPublishTime;
    //论文附件--可能是多个pdf或图片
    private String manyFilePath;
    //论文附件原名称
    private String manyFileName;
    //论文附件类型（后缀）
    private String manyFileType;
    //用户部门id
    private Integer deptId;

    @Override
    public AcademicPaperEntity getModel() {
        return academicPaperEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        academicPaperEntity.setPublishTime(new java.sql.Date(DateUtil.string2Date(paperPublishTime,"yyyy-MM-dd").getTime()));

        //论文等级
        AcademicPaperGradeEntity paperGrade_result = new AcademicPaperGradeEntity();
        paperGrade_result.setId(paperGradeId);
        paperGrade_result = academicPaperGradeService.findById(paperGrade_result);
        academicPaperEntity.setPaperGrade(paperGrade_result);

        //作者信息
        TeacherEntity teacherEntity_result = new TeacherEntity();
        teacherEntity_result.setTeacherId(teacherId);
        teacherEntity_result = teacherService.findById(teacherEntity_result);
        academicPaperEntity.setTeacher(teacherEntity_result);

        academicPaperService.save(academicPaperEntity); //1、先保存论文

        //论文附件
        if (manyFileName != null && !"".equals(manyFileName)) {
            AcademicPaperAnnexEntity paperAnnexEntity;
            String[] filePath = manyFilePath.split(",");
            String[] fileName = manyFileName.split(",");
            String[] fileType = manyFileType.split(",");
            for (int i=0; i<fileName.length; i++) {
                paperAnnexEntity = new AcademicPaperAnnexEntity();
                paperAnnexEntity.setFilePath(filePath[i]);  //文件现在的路径
                paperAnnexEntity.setFileName(fileName[i]);  //文件原来的名称
                paperAnnexEntity.setFileType(fileType[i]);  //文件后缀，如: .pdf .jpg
                paperAnnexEntity.setPaperId(academicPaperEntity.getPaperId());  //2、获取论文id
                academicPaperAnnexService.save(paperAnnexEntity);   //3、再保存附件
            }
        }

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        academicPaperService.delete(academicPaperEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        academicPaperEntity.setPublishTime(new java.sql.Date(DateUtil.string2Date(paperPublishTime,"yyyy-MM-dd").getTime()));

        //论文等级
        AcademicPaperGradeEntity paperGrade_result = new AcademicPaperGradeEntity();
        paperGrade_result.setId(paperGradeId);
        paperGrade_result = academicPaperGradeService.findById(paperGrade_result);
        academicPaperEntity.setPaperGrade(paperGrade_result);

        //作者信息
        TeacherEntity teacherEntity_result = new TeacherEntity();
        teacherEntity_result.setTeacherId(teacherId);
        teacherEntity_result = teacherService.findById(teacherEntity_result);
        academicPaperEntity.setTeacher(teacherEntity_result);

        academicPaperService.update(academicPaperEntity);

        //用户重新上传的附件
        if (manyFileName != null && !"".equals(manyFileName)) {
            AcademicPaperAnnexEntity paperAnnexEntity;
            String[] filePath = manyFilePath.split(",");
            String[] fileName = manyFileName.split(",");
            String[] fileType = manyFileType.split(",");
            for (int i=0; i<fileName.length; i++) {
                paperAnnexEntity = new AcademicPaperAnnexEntity();
                paperAnnexEntity.setFilePath(filePath[i]);  //文件现在的路径
                paperAnnexEntity.setFileName(fileName[i]);  //文件原来的名称
                paperAnnexEntity.setFileType(fileType[i]);  //文件后缀，如: .pdf .jpg
                paperAnnexEntity.setPaperId(academicPaperEntity.getPaperId());  //2、获取论文id
                academicPaperAnnexService.save(paperAnnexEntity);   //3、再保存附件
            }
        }

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {

        AcademicPaperEntity byId = academicPaperService.findById(academicPaperEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询--管理员
     */
    public String findByPage() {

        PageBean byPage = academicPaperService.findByPage(key, new PageBean<AcademicPaperEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 查询--部门领导和管理员
     */
    public String findByDept() {

        PageBean byDept = academicPaperService.findByDept(deptId, key, new PageBean<AcademicPaperEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byDept.getRows()).put("count", byDept.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 教师查询自己的学术论文
     */
    public String findByAuthor() {

        PageBean byAuthor = academicPaperService.findByAuthor(teacherId, key, new PageBean<AcademicPaperEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byAuthor.getRows()).put("count", byAuthor.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        academicPaperService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////

    public AcademicPaperEntity getAcademicPaperEntity() {
        return academicPaperEntity;
    }

    public void setAcademicPaperEntity(AcademicPaperEntity academicPaperEntity) {
        this.academicPaperEntity = academicPaperEntity;
    }

    public PageBean<AcademicPaperEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<AcademicPaperEntity> pageBean) {
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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getPaperGradeId() {
        return paperGradeId;
    }

    public void setPaperGradeId(Integer paperGradeId) {
        this.paperGradeId = paperGradeId;
    }

    public String getPaperPublishTime() {
        return paperPublishTime;
    }

    public void setPaperPublishTime(String paperPublishTime) {
        this.paperPublishTime = paperPublishTime;
    }

    public String getManyFilePath() {
        return manyFilePath;
    }

    public void setManyFilePath(String manyFilePath) {
        this.manyFilePath = manyFilePath;
    }

    public String getManyFileName() {
        return manyFileName;
    }

    public void setManyFileName(String manyFileName) {
        this.manyFileName = manyFileName;
    }

    public String getManyFileType() {
        return manyFileType;
    }

    public void setManyFileType(String manyFileType) {
        this.manyFileType = manyFileType;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
