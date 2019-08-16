package com.teacherfiles.models.sys.dao.academicPaperGrade.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.academicPaperGrade.AcademicPaperGradeDao;
import com.teacherfiles.models.sys.model.AcademicPaperGradeEntity;
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
public class AcademicPaperGradeDaoImpl implements AcademicPaperGradeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(AcademicPaperGradeEntity academicPaperGradeEntity) {
        hibernateTemplate.save(academicPaperGradeEntity);
    }

    @Override
    public void delete(AcademicPaperGradeEntity academicPaperGradeEntity) {
        sessionFactory.getCurrentSession().delete(academicPaperGradeEntity);
    }

    @Override
    public void update(AcademicPaperGradeEntity academicPaperGradeEntity) {
        sessionFactory.getCurrentSession().update(academicPaperGradeEntity);
    }

    @Override
    public AcademicPaperGradeEntity findById(AcademicPaperGradeEntity academicPaperGradeEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(AcademicPaperGradeEntity.class);

        List list = criteria.add(Restrictions.eq("id", academicPaperGradeEntity.getId())).list();

        session.close();

        return list!=null&&list.size()>0? (AcademicPaperGradeEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<AcademicPaperGradeEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(AcademicPaperGradeEntity.class);

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

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(AcademicPaperGradeEntity.class).setProjection(Projections.rowCount()).uniqueResult()));


        session.close();

        return pageBean;
    }

    @Override
    public List<AcademicPaperGradeEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(AcademicPaperGradeEntity.class);

        List list = criteria.list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<AcademicPaperGradeEntity> list = new ArrayList<>();

        for (String id : Ids) {
            AcademicPaperGradeEntity academicPaperGradeEntity = new AcademicPaperGradeEntity();
            academicPaperGradeEntity.setId(Integer.parseInt(id));
            list.add(academicPaperGradeEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
