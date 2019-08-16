package com.teacherfiles.models.sys.dao.academicPaper.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.academicPaper.AcademicPaperDao;
import com.teacherfiles.models.sys.model.AcademicPaperEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soldier
 * @title: AcademicPaperDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class AcademicPaperDaoImpl implements AcademicPaperDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(AcademicPaperEntity academicPaperEntity) {
        hibernateTemplate.save(academicPaperEntity);
    }

    @Override
    public void delete(AcademicPaperEntity academicPaperEntity) {
        sessionFactory.getCurrentSession().delete(academicPaperEntity);
    }

    @Override
    public void update(AcademicPaperEntity academicPaperEntity) {
        sessionFactory.getCurrentSession().update(academicPaperEntity);
    }

    @Override
    public AcademicPaperEntity findById(AcademicPaperEntity academicPaperEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(AcademicPaperEntity.class);

        List list = criteria.add(Restrictions.eq("paperId", academicPaperEntity.getPaperId())).list();

        session.close();

        return list!=null&&list.size()>0? (AcademicPaperEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<AcademicPaperEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(AcademicPaperEntity.class);

        if (key != null && !key.equals("")) {

            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("paperName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("paperTitle", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("periodicalName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("periodicalNumber", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                            .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(AcademicPaperEntity.class).setProjection(Projections.rowCount()).uniqueResult()));


        session.close();

        return pageBean;
    }

    @Override
    public PageBean findByDept(Integer deptId, String key, PageBean<AcademicPaperEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(AcademicPaperEntity.class);
        //TODO 多重外键查询
        criteria.createAlias("teacher", "teacher").
                add(Restrictions.eq("teacher.dept.deptId", deptId));

        if (key != null && !key.equals("")) {

            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("paperName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("paperTitle", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("periodicalName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("periodicalNumber", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                            .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(AcademicPaperEntity.class)
                .createAlias("teacher", "teacher")
                    .add(Restrictions.eq("teacher.dept.deptId", deptId))
                .setProjection(Projections.rowCount()).uniqueResult()));


        session.close();

        return pageBean;
    }

    @Override
    public PageBean findByAuthor(Integer teacherId, String key, PageBean<AcademicPaperEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(AcademicPaperEntity.class);

        //TODO 外键查询
        criteria.createAlias("teacher", "teacher").
                add(Restrictions.eq("teacher.teacherId", teacherId));

        if (key != null && !key.equals("")) {

            //搜索--实现：where teacherId=? and (xxx like ? or xxx like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接
            dis.add(Restrictions.like("teacherName", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("paperName", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("paperTitle", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("periodicalName", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("periodicalNumber", key, MatchMode.ANYWHERE));
            List list = criteria.add(dis)
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {

            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                            .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(AcademicPaperEntity.class)
                .createAlias("teacher", "teacher").add(Restrictions.eq("teacher.teacherId", teacherId))
                .setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<AcademicPaperEntity> list = new ArrayList<>();

        for (String id : Ids) {
            AcademicPaperEntity academicPaperEntity = new AcademicPaperEntity();
            academicPaperEntity.setPaperId(Integer.parseInt(id));
            list.add(academicPaperEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
