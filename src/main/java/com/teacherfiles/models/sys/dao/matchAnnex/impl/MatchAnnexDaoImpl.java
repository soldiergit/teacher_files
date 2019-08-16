package com.teacherfiles.models.sys.dao.matchAnnex.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.matchAnnex.MatchAnnexDao;
import com.teacherfiles.models.sys.model.MatchAnnexEntity;
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
 * @title: MatchAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class MatchAnnexDaoImpl implements MatchAnnexDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(MatchAnnexEntity matchAnnexEntity) {
        hibernateTemplate.save(matchAnnexEntity);
    }

    @Override
    public void delete(MatchAnnexEntity matchAnnexEntity) {
        sessionFactory.getCurrentSession().delete(matchAnnexEntity);
    }

    @Override
    public void update(MatchAnnexEntity matchAnnexEntity) {
        sessionFactory.getCurrentSession().update(matchAnnexEntity);
    }

    @Override
    public List<MatchAnnexEntity> findByMatchId(Integer matchId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(MatchAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("matchId", matchId)).list();

        session.close();

        return list;
    }

    @Override
    public MatchAnnexEntity findById(MatchAnnexEntity matchAnnexEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(MatchAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("matchAnnexId", matchAnnexEntity.getMatchAnnexId())).list();

        session.close();

        return list!=null&&list.size()>0? (MatchAnnexEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<MatchAnnexEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(MatchAnnexEntity.class);

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

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(MatchAnnexEntity.class).setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public List<MatchAnnexEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(MatchAnnexEntity.class);

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<MatchAnnexEntity> list = new ArrayList<>();

        for (String id : Ids) {
            MatchAnnexEntity matchAnnexEntity = new MatchAnnexEntity();
            matchAnnexEntity.setMatchAnnexId(Integer.parseInt(id));
            list.add(matchAnnexEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
