package com.teacherfiles.models.sys.dao.competitionPrizeGrade.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.competitionPrizeGrade.CompetitionPrizeGradeDao;
import com.teacherfiles.models.sys.model.CompetitionPrizeGradeEntity;
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
public class CompetitionPrizeGradeDaoImpl implements CompetitionPrizeGradeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(CompetitionPrizeGradeEntity competitionPrizeGradeEntity) {
        hibernateTemplate.save(competitionPrizeGradeEntity);
    }

    @Override
    public void delete(CompetitionPrizeGradeEntity competitionPrizeGradeEntity) {
        sessionFactory.getCurrentSession().delete(competitionPrizeGradeEntity);
    }

    @Override
    public void update(CompetitionPrizeGradeEntity competitionPrizeGradeEntity) {
        sessionFactory.getCurrentSession().update(competitionPrizeGradeEntity);
    }

    @Override
    public CompetitionPrizeGradeEntity findById(CompetitionPrizeGradeEntity competitionPrizeGradeEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionPrizeGradeEntity.class);

        List list = criteria.add(Restrictions.eq("id", competitionPrizeGradeEntity.getId())).list();

        session.close();

        return list!=null&&list.size()>0? (CompetitionPrizeGradeEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<CompetitionPrizeGradeEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionPrizeGradeEntity.class);

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

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(CompetitionPrizeGradeEntity.class).setProjection(Projections.rowCount()).uniqueResult()));


        session.close();

        return pageBean;
    }

    @Override
    public List<CompetitionPrizeGradeEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionPrizeGradeEntity.class);

        List list = criteria.list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<CompetitionPrizeGradeEntity> list = new ArrayList<>();

        for (String id : Ids) {
            CompetitionPrizeGradeEntity competitionPrizeGradeEntity = new CompetitionPrizeGradeEntity();
            competitionPrizeGradeEntity.setId(Integer.parseInt(id));
            list.add(competitionPrizeGradeEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
