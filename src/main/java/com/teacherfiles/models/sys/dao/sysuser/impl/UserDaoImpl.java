package com.teacherfiles.models.sys.dao.sysuser.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.sysuser.UserDao;
import com.teacherfiles.models.sys.model.SysUserEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soldier
 * @title: UserDaoImpl
 * @projectName teacher_files
 * @date 19-7-4下午11:27
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 添加用户
     * @param sysUserEntity
     */
    @Override
    public void addUser(SysUserEntity sysUserEntity) {
        hibernateTemplate.save(sysUserEntity);
    }

    /**
     * 删除用户
     * @param sysUserEntity
     */
    @Override
    public void deleteUser(SysUserEntity sysUserEntity) {
        hibernateTemplate.delete(sysUserEntity);
    }

    /**
     * 更新用户
     * @param sysUserEntity
     */
    @Override
    public void updateUser(SysUserEntity sysUserEntity) {
        hibernateTemplate.update(sysUserEntity);
    }

    /**
     * 选择一个
     * @param sysUserEntity
     * @return
     */
    @Override
    public SysUserEntity findOneUser(SysUserEntity sysUserEntity) {

        Session currentSession = sessionFactory.openSession();

        Criteria criteria = currentSession.createCriteria(SysUserEntity.class);

        List<SysUserEntity> list = criteria.add(Restrictions.eq("userId", sysUserEntity.getUserId())).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        currentSession.close();

        return list.size()>0&&list!=null?list.get(0):null;
    }

    /**
     * 分页查询
     * @param key
     * @param pageBean
     * @return
     */
    @Override
    public PageBean findAllUser(String key, PageBean<SysUserEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysUserEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            List list = criteria.add(
                    Restrictions.or(Restrictions.like("userId", key, MatchMode.ANYWHERE),
                            Restrictions.or(Restrictions.like("loginAccount", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("userName", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

            System.out.println(pageBean+"\n"+list);
            pageBean.setRows(list);


        } else {
            List list = criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            System.out.println(pageBean+"\n"+list);
            pageBean.setRows(list);

        }
        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(SysUserEntity.class).setProjection(Projections.rowCount()).uniqueResult()));

        session.close();
        return pageBean;

    }

    /**
     * 批量删除
     * @param userIds
     */
    @Override
    public void deleteAllUser(String[] userIds) {
        List<SysUserEntity> list = new ArrayList<>();

        System.out.println(userIds);

        for (String id : userIds) {
            SysUserEntity sysUserEntity = new SysUserEntity();
            sysUserEntity.setUserId(Integer.parseInt(id));
            list.add(sysUserEntity);
        }
        hibernateTemplate.deleteAll(list);
    }
}
