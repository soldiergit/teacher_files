package com.teacherfiles.models.sys.action.login;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.teacherfiles.common.vo.R;
import com.teacherfiles.models.sys.model.SysUserEntity;
import com.teacherfiles.models.sys.service.login.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author soldier
 * @title: LoginAction
 * @projectName teacher_files
 * @date 19-7-5下午5:55
 * @Email： 583403411@qq.com
 * @description:
 */
public class LoginAction extends ActionSupport implements ModelDriven<SysUserEntity> {

    @Autowired
    private LoginService loginService;

    //日志
    private static Logger logger = Logger.getLogger(LoginAction.class);
    //模型驱动
    private SysUserEntity sysUserEntity = new SysUserEntity();
    //新密码
    private String newPassword;
    //返回集
    private R result = new R();

    @Override
    public SysUserEntity getModel() {
        return sysUserEntity;
    }

    /////////////////////////////////////////

    /**
     * 用户登入
     * @return
     */
    public String userLogin(){

        logger.info("message:用户登入:"+sysUserEntity);

        result = loginService.userLogin(sysUserEntity);

        logger.info(result);

        return SUCCESS;
    }

    /**
     * 修改密码
     * @return
     */
    public String updatePassword(){

        logger.info("修改密码："+sysUserEntity+"\t"+newPassword);

        result = loginService.updatePassword(sysUserEntity,newPassword);

        logger.info(result);

        return SUCCESS;
    }

    /**
     * 退出登入
     * @return
     */
    public String logout(){

        System.out.println("用户退出");

        return SUCCESS;
    }

    /////////////////////////////////////////

    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }


}
