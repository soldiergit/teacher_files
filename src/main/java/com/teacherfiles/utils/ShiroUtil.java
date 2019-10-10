//package com.teacherfiles.utils;
//
//import com.teacherfiles.models.sys.model.SysUserEntity;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.Subject;
//
///**
// * @ProjectName: teacher_files
// * @author: soldier
// * @Email: 583403411@qq.com
// * @create 19-7-31 下午8:52
// * @Describe:
// **/
//public class ShiroUtil {
//
//    public static Session getSession() {
//        return SecurityUtils.getSubject().getSession();
//    }
//
//    public static Subject getSubject() {
//        return SecurityUtils.getSubject();
//    }
//
//    public static SysUserEntity getUserEntity() {
//        return (SysUserEntity)SecurityUtils.getSubject().getPrincipal();
//    }
//
//    public static Integer getUserId() {
//        return getUserEntity().getUserId();
//    }
//    public static String getUserName() {
//        return getUserEntity().getUserName();
//    }
//
//    public static void setSessionAttribute(Object key, Object value) {
//        getSession().setAttribute(key, value);
//    }
//
//    public static Object getSessionAttribute(Object key) {
//        return getSession().getAttribute(key);
//    }
//
//    public static boolean isLogin() {
//        return SecurityUtils.getSubject().getPrincipal() != null;
//    }
//
//    public static void logout() {
//        SecurityUtils.getSubject().logout();
//    }
//
//    public static String getKaptcha(String key) {
//        String kaptcha = getSessionAttribute(key).toString();
//        getSession().removeAttribute(key);
//        return kaptcha;
//    }
//
//}
