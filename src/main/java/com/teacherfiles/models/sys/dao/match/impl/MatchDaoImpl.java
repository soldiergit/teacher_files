package com.teacherfiles.models.sys.dao.match.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.match.MatchDao;
import com.teacherfiles.models.sys.model.MatchEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
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
 * @title: MatchDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class MatchDaoImpl implements MatchDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(MatchEntity matchEntity) {
        hibernateTemplate.save(matchEntity);
    }

    @Override
    public void delete(MatchEntity matchEntity) {
        sessionFactory.getCurrentSession().delete(matchEntity);
    }

    @Override
    public void update(MatchEntity matchEntity) {
        sessionFactory.getCurrentSession().update(matchEntity);
    }

    @Override
    public MatchEntity findById(MatchEntity matchEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(MatchEntity.class);

        List list = criteria.add(Restrictions.eq("matchId", matchEntity.getMatchId())).list();

        session.close();

        return list!=null&&list.size()>0? (MatchEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<MatchEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(MatchEntity.class);

        if (key != null && !key.equals("")) {

            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("matchName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("organizer", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("contractor", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(MatchEntity.class).setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public PageBean findByTeacher(Integer teacherId, String key, PageBean<MatchEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(MatchEntity.class);

        //TODO 外键查询
        criteria.createAlias("teacher", "teacher")
                .add(Restrictions.eq("teacher.teacherId", teacherId));

        if (key != null && !key.equals("")) {

            //搜索--实现：where teacherId=? and (xxx like ? or xxx like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接
            dis.add(Restrictions.like("matchName", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("organizer", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("contractor", key, MatchMode.ANYWHERE));
            List list = criteria.add(dis)
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {

            pageBean.setRows(criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(MatchEntity.class)
                .createAlias("teacher", "teacher").add(Restrictions.eq("teacher.teacherId", teacherId))
                .setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public List<MatchEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(MatchEntity.class);

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<MatchEntity> list = new ArrayList<>();

        for (String id : Ids) {
            MatchEntity matchEntity = new MatchEntity();
            matchEntity.setMatchId(Integer.parseInt(id));
            list.add(matchEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
