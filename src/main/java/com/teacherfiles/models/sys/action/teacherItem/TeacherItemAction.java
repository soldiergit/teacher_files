package com.teacherfiles.models.sys.action.teacherItem;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.*;
import com.teacherfiles.models.sys.service.teacherItem.TeacherItemService;
import com.teacherfiles.models.sys.service.teacher.TeacherService;
import com.teacherfiles.models.sys.service.teacherItemAnnex.TeacherItemAnnexService;
import com.teacherfiles.models.sys.service.teacherItemCategory.TeacherItemCategoryService;
import com.teacherfiles.models.sys.service.teacherItemLevel.TeacherItemLevelService;
import com.teacherfiles.utils.DateUtil;
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
public class TeacherItemAction extends ActionSupport implements ModelDriven<TeacherItemEntity> {

    @Autowired
    private TeacherItemService teacherItemService;
    @Autowired
    private TeacherItemAnnexService itemAnnexService;
    @Autowired
    private TeacherItemCategoryService itemCategoryService;
    @Autowired
    private TeacherItemLevelService itemLevelService;
    @Autowired
    private TeacherService teacherService;
    //日志
    private static Logger logger = Logger.getLogger(TeacherItemAction.class);
    //模型驱动
    private TeacherItemEntity teacherItemEntity = new TeacherItemEntity();
    //分页
    private PageBean<TeacherItemEntity> pageBean = new PageBean<>();
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
    //教师项目类别id
    private Integer itemCategoryId;
    //教师项目级别id
    private Integer itemLevelId;
    //主持人id
    private Integer itemPersonId;
    //某个项目成员id--用于该成员登录系查看自己加入的项目
    private String memberId;
    //科研项目立项时间
    private String itemCreateTime;
    //科研项目起始时间
    private String itemStartTime;
    //科研项目结束时间
    private String itemEndTime;
    //负责人部门id
    private Integer deptId;
    //导出标识 1-系统管理员导出整个表 2-部门领导和部门负责人导出本学院全部 3-教师个人导出全部 4-根据id导出
    private Integer exportCode;
    //教师项附件路径--可能是多个pdf或图片
    private String manyFilePath;
    //教师项目附件原名称
    private String manyFileName;
    //教师项目附件类型（后缀）
    private String manyFileType;

    @Override
    public TeacherItemEntity getModel() {
        return teacherItemEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        teacherItemEntity.setCreateTime(new java.sql.Date(DateUtil.string2Date(itemCreateTime,"yyyy").getTime()));
        teacherItemEntity.setStartTime(new java.sql.Date(DateUtil.string2Date(itemStartTime,"yyyy-MM-dd").getTime()));
        teacherItemEntity.setEndTime(new java.sql.Date(DateUtil.string2Date(itemEndTime,"yyyy-MM-dd").getTime()));

        //项目类别及级别
        TeacherItemCategoryEntity itemCategory_result = new TeacherItemCategoryEntity();
        TeacherItemLevelEntity itemLevel_result = new TeacherItemLevelEntity();
        itemCategory_result.setId(itemCategoryId);
        itemLevel_result.setId(itemLevelId);
        itemCategory_result = itemCategoryService.findById(itemCategory_result);
        itemLevel_result = itemLevelService.findById(itemLevel_result);
        teacherItemEntity.setItemCategory(itemCategory_result);
        teacherItemEntity.setItemLevel(itemLevel_result);

        //负责人信息
        TeacherEntity teacher_result = new TeacherEntity();
        teacher_result.setTeacherId(itemPersonId);
        teacher_result = teacherService.findById(teacher_result);
        teacherItemEntity.setItemPerson(teacher_result);

        teacherItemService.save(teacherItemEntity);

        //教师项目附件
        if (manyFileName != null && !"".equals(manyFileName)) {
            TeacherItemAnnexEntity teacherItemAnnexEntity;
            String[] filePath = manyFilePath.split(",");
            String[] fileName = manyFileName.split(",");
            String[] fileType = manyFileType.split(",");
            for (int i=0; i<fileName.length; i++) {
                teacherItemAnnexEntity = new TeacherItemAnnexEntity();
                teacherItemAnnexEntity.setFilePath(filePath[i]);  //文件现在的路径
                teacherItemAnnexEntity.setFileName(fileName[i]);  //文件原来的名称
                teacherItemAnnexEntity.setFileType(fileType[i]);  //文件后缀，如: .pdf .jpg
                teacherItemAnnexEntity.setItemId(teacherItemEntity.getItemId());  //2、获取教师项目id
                itemAnnexService.save(teacherItemAnnexEntity);   //3、再保存附件
            }
        }

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        teacherItemService.delete(teacherItemEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        teacherItemEntity.setCreateTime(new java.sql.Date(DateUtil.string2Date(itemCreateTime,"yyyy").getTime()));
        teacherItemEntity.setStartTime(new java.sql.Date(DateUtil.string2Date(itemStartTime,"yyyy-MM-dd").getTime()));
        teacherItemEntity.setEndTime(new java.sql.Date(DateUtil.string2Date(itemEndTime,"yyyy-MM-dd").getTime()));

        //项目类别及级别
        TeacherItemCategoryEntity itemCategory_result = new TeacherItemCategoryEntity();
        TeacherItemLevelEntity itemLevel_result = new TeacherItemLevelEntity();
        itemCategory_result.setId(itemCategoryId);
        itemLevel_result.setId(itemLevelId);
        itemCategory_result = itemCategoryService.findById(itemCategory_result);
        itemLevel_result = itemLevelService.findById(itemLevel_result);
        teacherItemEntity.setItemCategory(itemCategory_result);
        teacherItemEntity.setItemLevel(itemLevel_result);

        //负责人信息
        TeacherEntity teacher_result = new TeacherEntity();
        teacher_result.setTeacherId(itemPersonId);
        teacher_result = teacherService.findById(teacher_result);
        teacherItemEntity.setItemPerson(teacher_result);

        //用户重新上传的附件
        if (manyFileName != null && !"".equals(manyFileName)) {
            TeacherItemAnnexEntity teacherItemAnnexEntity;
            String[] filePath = manyFilePath.split(",");
            String[] fileName = manyFileName.split(",");
            String[] fileType = manyFileType.split(",");
            for (int i=0; i<fileName.length; i++) {
                teacherItemAnnexEntity = new TeacherItemAnnexEntity();
                teacherItemAnnexEntity.setFilePath(filePath[i]);  //文件现在的路径
                teacherItemAnnexEntity.setFileName(fileName[i]);  //文件原来的名称
                teacherItemAnnexEntity.setFileType(fileType[i]);  //文件后缀，如: .pdf .jpg
                teacherItemAnnexEntity.setItemId(teacherItemEntity.getItemId());  //2、获取教师项目id
                itemAnnexService.save(teacherItemAnnexEntity);   //3、再保存附件
            }
        }

        teacherItemService.update(teacherItemEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById() {

        TeacherItemEntity byId = teacherItemService.findById(teacherItemEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = teacherItemService.findByPage(key, new PageBean<TeacherItemEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 科研项目负责人查询自己负责的项目
     */
    public String findByPerson() {

        PageBean byPage = teacherItemService.findByPerson(itemPersonId, key, new PageBean<TeacherItemEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 项目成员查询自己加入的项目
     */
    public String findByMember() {

        PageBean byPage = teacherItemService.findByMember(memberId, key, new PageBean<TeacherItemEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 部门管理员和领导查询本部门所有的项目
     */
    public String findByDept() {

        PageBean byDept = teacherItemService.findByDept(deptId, key, new PageBean<TeacherItemEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byDept.getRows()).put("count", byDept.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 列表批量导出
     */
    public String findByExport() {

        /**
         * 1-系统管理员导出整个表 2-部门领导和部门负责人导出本学院全部 3-教师个人导出全部指导 4-根据id导出
         */
        Integer thisDeptId =null;   //部门约束
        String[] id = null;     //主键约束
        Integer thisItemPersonId = null;  //主持人老师约束
        if (exportCode == 1) {
            thisDeptId = null;
            id = null;
            thisItemPersonId = null;
        } else if (exportCode == 2) {
            thisDeptId = deptId;
            id = null;
            thisItemPersonId = null;
        } else if (exportCode == 3) {
            thisDeptId = null;
            id = null;
            thisItemPersonId = itemPersonId;
        } else if (exportCode == 4) {
            thisDeptId = null;
            id = ids.split(",");
            thisItemPersonId = null;
        }

        List<TeacherItemEntity> list = teacherItemService.findByExport(thisDeptId, id, thisItemPersonId);

        r = R.ok().put("data", list);

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        teacherItemService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }


    /////////////////////////////////////////

    public TeacherItemEntity getTeacherItemEntity() {
        return teacherItemEntity;
    }

    public void setTeacherItemEntity(TeacherItemEntity teacherItemEntity) {
        this.teacherItemEntity = teacherItemEntity;
    }

    public PageBean<TeacherItemEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TeacherItemEntity> pageBean) {
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

    public Integer getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Integer itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public Integer getItemLevelId() {
        return itemLevelId;
    }

    public void setItemLevelId(Integer itemLevelId) {
        this.itemLevelId = itemLevelId;
    }

    public String getItemCreateTime() {
        return itemCreateTime;
    }

    public void setItemCreateTime(String itemCreateTime) {
        this.itemCreateTime = itemCreateTime;
    }

    public String getItemStartTime() {
        return itemStartTime;
    }

    public void setItemStartTime(String itemStartTime) {
        this.itemStartTime = itemStartTime;
    }

    public String getItemEndTime() {
        return itemEndTime;
    }

    public void setItemEndTime(String itemEndTime) {
        this.itemEndTime = itemEndTime;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getItemPersonId() {
        return itemPersonId;
    }

    public void setItemPersonId(Integer itemPersonId) {
        this.itemPersonId = itemPersonId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getExportCode() {
        return exportCode;
    }

    public void setExportCode(Integer exportCode) {
        this.exportCode = exportCode;
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
}
