package com.teacherfiles.models.sys.dao.teacherItemAnnex.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.teacherItemAnnex.TeacherItemAnnexDao;
import com.teacherfiles.models.sys.model.TeacherItemAnnexEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soldier
 * @title: TeacherItemAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class TeacherItemAnnexDaoImpl implements TeacherItemAnnexDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(TeacherItemAnnexEntity teacherItemAnnexEntity) {
        hibernateTemplate.save(teacherItemAnnexEntity);
    }

    @Override
    public void delete(TeacherItemAnnexEntity teacherItemAnnexEntity) {
        sessionFactory.getCurrentSession().delete(teacherItemAnnexEntity);
    }

    @Override
    public void update(TeacherItemAnnexEntity teacherItemAnnexEntity) {
        sessionFactory.getCurrentSession().update(teacherItemAnnexEntity);
    }

    @Override
    public List<TeacherItemAnnexEntity> findByTeacherItemId(Integer itemId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("itemId", itemId)).list();

        session.close();

        return list;
    }

    @Override
    public TeacherItemAnnexEntity findById(TeacherItemAnnexEntity teacherItemAnnexEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("itemAnnexId", teacherItemAnnexEntity.getItemAnnexId())).list();

        session.close();

        return list!=null&&list.size()>0? (TeacherItemAnnexEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherItemAnnexEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemAnnexEntity.class);

        if (key != null && !key.equals("")) {

            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("path", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("fileName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("fileType", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(TeacherItemAnnexEntity.class).setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public List<TeacherItemAnnexEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemAnnexEntity.class);

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TeacherItemAnnexEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TeacherItemAnnexEntity teacherItemAnnexEntity = new TeacherItemAnnexEntity();
            teacherItemAnnexEntity.setItemAnnexId(Integer.parseInt(id));
            list.add(teacherItemAnnexEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
