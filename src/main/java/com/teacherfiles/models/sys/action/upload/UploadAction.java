package com.teacherfiles.models.sys.action.upload;

import com.opensymphony.xwork2.ActionSupport;
import com.teacherfiles.common.vo.R;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.util.UUID;

/**
 * @author soldier
 * @title: Api
 * @projectName teacher_files
 * @date 19-7-7下午3:57
 * @Email： 583403411@qq.com
 * @description:
//  String filePath = ServletActionContext.getServletContext().getRealPath("/sysFile");
// :/usr/local/apache-tomcat-8.5.38/webapps/teacher_files_war/sysFile
 */
public class UploadAction extends ActionSupport {

    //单文件：  上传文件的控件名称;struts2用来封装页面文件域对应的文件内容  --控件名
    private File file;
    //单文件：  上传文件名称, 文件名称= 控件名+FileName;struts2用来封装该文件域对应的文件的文件名,xxxFileName,layui上传的文件域field默认值是file
    private String fileFileName;

    //日志
    private static Logger logger = Logger.getLogger(UploadAction.class);
    //返回集
    private R r = new R();

    /**
     *上传教师个人图片
     */
    public String uploadTeacherImg() {

        String realPath = ServletActionContext.getServletContext().getRealPath("/upload/teacherImg");
        String projectPath = ServletActionContext.getServletContext().getContextPath();//等于 /teacher_files_war

        // 获取原文件图片后缀，以最后的.作为截取(.jpg)
        String extName = fileFileName.substring(fileFileName.lastIndexOf("."));

        // 生成自定义的新文件名，这里以UUID.jpg|png|xxx作为格式（可选操作，也可以不自定义新文件名）
        String uuid = UUID.randomUUID().toString();

        //  文件保存路径
        File fileFolder = new File(realPath);
        if(!fileFolder.exists()) fileFolder.mkdirs();

        try {
            //修改文件名称
            fileFileName = uuid+extName;
            FileUtils.copyFile(file, new File(fileFolder, fileFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回前台路径，用于保存到数据库
        r = R.ok().put("src", projectPath+"/upload/teacherImg/"+fileFileName);

        logger.info("上传头像："+r);

        return SUCCESS;
    }

    /**
     *上传竞赛获奖图片
     */
    public String uploadPrizeImg() {

        String realPath = ServletActionContext.getServletContext().getRealPath("/upload/prizeImg");
        String projectPath = ServletActionContext.getServletContext().getContextPath();//等于 /teacher_files_war

        // 获取原文件图片后缀，以最后的.作为截取(.jpg)
        String extName = fileFileName.substring(fileFileName.lastIndexOf("."));

        // 生成自定义的新文件名，这里以UUID.jpg|png|xxx作为格式（可选操作，也可以不自定义新文件名）
        String uuid = UUID.randomUUID().toString();

        //  文件保存路径
        File fileFolder = new File(realPath);
        if(!fileFolder.exists()) fileFolder.mkdirs();

        try {
            //修改文件名称
            fileFileName = uuid+extName;
            FileUtils.copyFile(file, new File(fileFolder, fileFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回前台路径，用于保存到数据库
        r = R.ok().put("src", projectPath+"/upload/prizeImg/"+fileFileName);

        logger.info("上传获奖图片："+r);

        return SUCCESS;
    }

    /**
     * 上传文档
     */
    public String uploadFile() {

        String realPath = ServletActionContext.getServletContext().getRealPath("/upload/pdf");
        String projectPath = ServletActionContext.getServletContext().getContextPath();//等于 /teacher_files_war

        // 获取原文件图片后缀，以最后的.作为截取(.jpg)
        String extName = fileFileName.substring(fileFileName.lastIndexOf("."));

        // 生成自定义的新文件名，这里以UUID.jpg|png|xxx作为格式（可选操作，也可以不自定义新文件名）
        String uuid = UUID.randomUUID().toString();

        //  文件保存路径
        File fileFolder = new File(realPath);
        if(!fileFolder.exists()) fileFolder.mkdirs();

        try {
            //修改文件名称
            fileFileName = uuid+extName;
            FileUtils.copyFile(file, new File(fileFolder, fileFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回前台路径，用于保存到数据库
        r = R.ok().put("src", projectPath+"/upload/pdf/"+fileFileName);

        logger.info("上传文档："+r);

        return SUCCESS;
    }

    /**
     * 上传学术论文文档
     */
    public String uploadPaperFile() {

        String realPath = ServletActionContext.getServletContext().getRealPath("/upload/paperFile");
        String projectPath = ServletActionContext.getServletContext().getContextPath();//等于 /teacher_files_war

        // 获取原文件图片后缀，以最后的.作为截取(.jpg)
        String extName = fileFileName.substring(fileFileName.lastIndexOf("."));
        //保存原始文件名
        String oldFileName = fileFileName;

        // 生成自定义的新文件名，这里以UUID.jpg|png|xxx作为格式（可选操作，也可以不自定义新文件名）
        String uuid = UUID.randomUUID().toString();

        //  文件保存路径
        File fileFolder = new File(realPath);
        if(!fileFolder.exists()) fileFolder.mkdirs();

        try {
            //修改文件名称
            fileFileName = uuid+extName;
            FileUtils.copyFile(file, new File(fileFolder, fileFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回前台路径，用于保存到数据库
        r = R.ok().put("src", projectPath+"/upload/paperFile/"+fileFileName).put("fileName", oldFileName).put("fileType", extName);

        logger.info("上传学术论文文档："+r);

        return SUCCESS;
    }

    /**
     * 上传赛事文档
     */
    public String uploadMatchFile() {

        String realPath = ServletActionContext.getServletContext().getRealPath("/upload/matchFile");
        String projectPath = ServletActionContext.getServletContext().getContextPath();//等于 /teacher_files_war

        // 获取原文件图片后缀，以最后的.作为截取(.jpg)
        String extName = fileFileName.substring(fileFileName.lastIndexOf("."));
        //保存原始文件名
        String oldFileName = fileFileName;

        // 生成自定义的新文件名，这里以UUID.jpg|png|xxx作为格式（可选操作，也可以不自定义新文件名）
        String uuid = UUID.randomUUID().toString();

        //  文件保存路径
        File fileFolder = new File(realPath);
        if(!fileFolder.exists()) fileFolder.mkdirs();

        try {
            //修改文件名称
            fileFileName = uuid+extName;
            FileUtils.copyFile(file, new File(fileFolder, fileFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回前台，用于保存到数据库
        r = R.ok().put("src", projectPath+"/upload/matchFile/"+fileFileName).put("fileName", oldFileName).put("fileType", extName);

        logger.info("上传赛事文档："+r);

        return SUCCESS;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("");
        String filePath = file.getCanonicalPath();
        System.out.println(filePath);
    }
}
