package com.teacherfiles.models.sys.action.download;

import com.opensymphony.xwork2.ActionSupport;

import java.io.*;

/**
 * @author soldier
 * @title: DownLoadAction
 * @projectName teacher_files
 * @date 19-7-19下午5:37
 * @Email： 583403411@qq.com
 * @description:
 */
public class DownLoadAction extends ActionSupport {

    private String downloadPath;// 下载的路径
    private String contentType;// 下载文件的类型
    private String filename;// 文件名称

    public String downloadFile() throws Exception {
        //处理文件名称中文问题
        try {
            filename = new String(filename.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
        contentType = "application/octet-stream";// 指定下载问文件的类型
        return SUCCESS;
    }

    /**
     * 返回InputStream
     *
     * @return
     */
    public InputStream getInputStream() {
//        InputStream inputStream = ServletActionContext.getServletContext().getResourceAsStream(fileName);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(downloadPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
    }


    ///////////////////////////////////
    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
