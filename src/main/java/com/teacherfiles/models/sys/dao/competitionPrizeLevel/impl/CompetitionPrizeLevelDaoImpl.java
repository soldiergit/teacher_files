package com.teacherfiles.models.sys.dao.competitionPrizeLevel.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.competitionPrizeLevel.CompetitionPrizeLevelDao;
import com.teacherfiles.models.sys.model.CompetitionPrizeLevelEntity;
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
 * @title: AcademicPaperAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class CompetitionPrizeLevelDaoImpl implements CompetitionPrizeLevelDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(CompetitionPrizeLevelEntity competitionPrizeLevelEntity) {
        hibernateTemplate.save(competitionPrizeLevelEntity);
    }

    @Override
    public void delete(CompetitionPrizeLevelEntity competitionPrizeLevelEntity) {
        sessionFactory.getCurrentSession().delete(competitionPrizeLevelEntity);
    }

    @Override
    public void update(CompetitionPrizeLevelEntity competitionPrizeLevelEntity) {
        sessionFactory.getCurrentSession().update(competitionPrizeLevelEntity);
    }

    @Override
    public CompetitionPrizeLevelEntity findById(CompetitionPrizeLevelEntity competitionPrizeLevelEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionPrizeLevelEntity.class);

        List list = criteria.add(Restrictions.eq("id", competitionPrizeLevelEntity.getId())).list();

        session.close();

        return list!=null&&list.size()>0? (CompetitionPrizeLevelEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<CompetitionPrizeLevelEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionPrizeLevelEntity.class);

        if (key != null && !key.equals("")) {

            //搜索
            List list = criteria.add(
                    Restrictions.or(
//                            Restrictions.or(Restrictions.like("path", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("title", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(CompetitionPrizeLevelEntity.class).setProjection(Projections.rowCount()).uniqueResult()));


        session.close();

        return pageBean;
    }

    @Override
    public List<CompetitionPrizeLevelEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionPrizeLevelEntity.class);

        List list = criteria.list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<CompetitionPrizeLevelEntity> list = new ArrayList<>();

        for (String id : Ids) {
            CompetitionPrizeLevelEntity competitionPrizeLevelEntity = new CompetitionPrizeLevelEntity();
            competitionPrizeLevelEntity.setId(Integer.parseInt(id));
            list.add(competitionPrizeLevelEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
