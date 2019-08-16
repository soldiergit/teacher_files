package com.teacherfiles.models.sys.action.match;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.MatchAnnexEntity;
import com.teacherfiles.models.sys.model.MatchEntity;
import com.teacherfiles.models.sys.model.TeacherEntity;
import com.teacherfiles.models.sys.service.match.MatchService;
import com.teacherfiles.models.sys.service.matchAnnex.MatchAnnexService;
import com.teacherfiles.models.sys.service.teacher.TeacherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherItemAction
 * @projectName teacher_files
 * @date 19-7-5下午5:16
 * @Email： 583403411@qq.com
 * @description:
 */
public class MatchAction extends ActionSupport implements ModelDriven<MatchEntity> {

    @Autowired
    private MatchService matchService;
    @Autowired
    private MatchAnnexService matchAnnexService;
    @Autowired
    private TeacherService teacherService;
    //日志
    private static Logger logger = Logger.getLogger(MatchAction.class);
    //模型驱动
    private MatchEntity matchEntity = new MatchEntity();
    //分页
    private PageBean<MatchEntity> pageBean = new PageBean<>();
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
    //赛事信息附件路径--可能是多个pdf或图片
    private String manyFilePath;
    //赛事信息附件原名称
    private String manyFileName;
    //赛事信息附件类型（后缀）
    private String manyFileType;
    //赛事负责老师id
    private Integer teacherId;
    //赛事时间
//    private String theMatchTime;

    @Override
    public MatchEntity getModel() {
        return matchEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        //负责人信息
        TeacherEntity teacher_result = new TeacherEntity();
        teacher_result.setTeacherId(teacherId);
        teacher_result = teacherService.findById(teacher_result);
        matchEntity.setTeacher(teacher_result);

//        matchEntity.setMatchTime(new java.sql.Date(DateUtil.string2Date(theMatchTime,"yyyy-MM-dd").getTime()));

        matchService.save(matchEntity); //1、先保存赛事

        //赛事信息附件
        if (manyFileName != null && !"".equals(manyFileName)) {
            MatchAnnexEntity matchAnnexEntity;
            String[] filePath = manyFilePath.split(",");
            String[] fileName = manyFileName.split(",");
            String[] fileType = manyFileType.split(",");
            for (int i=0; i<fileName.length; i++) {
                matchAnnexEntity = new MatchAnnexEntity();
                matchAnnexEntity.setFilePath(filePath[i]);  //文件现在的路径
                matchAnnexEntity.setFileName(fileName[i]);  //文件原来的名称
                matchAnnexEntity.setFileType(fileType[i]);  //文件后缀，如: .pdf .jpg
                matchAnnexEntity.setMatchId(matchEntity.getMatchId());  //2、获取赛事id
                matchAnnexService.save(matchAnnexEntity);   //3、再保存附件
            }
        }

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        matchService.delete(matchEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        //负责人会变（负责人信息一起改变）
        TeacherEntity teacher_result = new TeacherEntity();
        teacher_result.setTeacherId(teacherId);
        teacher_result = teacherService.findById(teacher_result);
        matchEntity.setTeacher(teacher_result);

        //用户重新上传的附件
        if (manyFileName != null && !"".equals(manyFileName)) {
            MatchAnnexEntity matchAnnexEntity;
            String[] filePath = manyFilePath.split(",");
            String[] fileName = manyFileName.split(",");
            String[] fileType = manyFileType.split(",");
            for (int i=0; i<fileName.length; i++) {
                matchAnnexEntity = new MatchAnnexEntity();
                matchAnnexEntity.setFilePath(filePath[i]);  //文件现在的路径
                matchAnnexEntity.setFileName(fileName[i]);  //文件原来的名称
                matchAnnexEntity.setFileType(fileType[i]);  //文件后缀，如: .pdf .jpg
                matchAnnexEntity.setMatchId(matchEntity.getMatchId());  //2、获取赛事id
                matchAnnexService.save(matchAnnexEntity);   //3、再保存附件
            }
        }

        matchService.update(matchEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById() {

        MatchEntity byId = matchService.findById(matchEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = matchService.findByPage(key, new PageBean<MatchEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByTeacher() {

        PageBean byTeacher = matchService.findByTeacher(teacherId, key, new PageBean<MatchEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byTeacher.getRows()).put("count", byTeacher.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 获取全部，用于下拉框渲染
     */
    public String findAll() {

        List<MatchEntity> list = matchService.findAll();

        r = R.ok().put("data", list);

        logger.info("查询列表：" + r);

        return SUCCESS;
    }


    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        matchService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////


    public PageBean<MatchEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<MatchEntity> pageBean) {
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

    public MatchEntity getMatchEntity() {
        return matchEntity;
    }

    public void setMatchEntity(MatchEntity matchEntity) {
        this.matchEntity = matchEntity;
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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
//    public String getTheMatchTime() {
//        return theMatchTime;
//    }
//
//    public void setTheMatchTime(String theMatchTime) {
//        this.theMatchTime = theMatchTime;
//    }
}
