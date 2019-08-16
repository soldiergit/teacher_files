package com.teacherfiles.models.sys.dao.academicPaperAnnex.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.academicPaperAnnex.AcademicPaperAnnexDao;
import com.teacherfiles.models.sys.model.AcademicPaperAnnexEntity;
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
public class AcademicPaperAnnexDaoImpl implements AcademicPaperAnnexDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(AcademicPaperAnnexEntity academicPaperAnnexEntity) {
        hibernateTemplate.save(academicPaperAnnexEntity);
    }

    @Override
    public void delete(AcademicPaperAnnexEntity academicPaperAnnexEntity) {
        sessionFactory.getCurrentSession().delete(academicPaperAnnexEntity);
    }

    @Override
    public void update(AcademicPaperAnnexEntity academicPaperAnnexEntity) {
        sessionFactory.getCurrentSession().update(academicPaperAnnexEntity);
    }

    @Override
    public List<AcademicPaperAnnexEntity> findByPaperId(Integer paperId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(AcademicPaperAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("paperId", paperId)).list();

        session.close();

        return list;
    }

    @Override
    public AcademicPaperAnnexEntity findById(AcademicPaperAnnexEntity academicPaperAnnexEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(AcademicPaperAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("paperAnnexId", academicPaperAnnexEntity.getPaperAnnexId())).list();

        session.close();

        return list!=null&&list.size()>0? (AcademicPaperAnnexEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<AcademicPaperAnnexEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(AcademicPaperAnnexEntity.class);

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

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(AcademicPaperAnnexEntity.class).setProjection(Projections.rowCount()).uniqueResult()));


        session.close();

        return pageBean;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<AcademicPaperAnnexEntity> list = new ArrayList<>();

        for (String id : Ids) {
            AcademicPaperAnnexEntity academicPaperAnnexEntity = new AcademicPaperAnnexEntity();
            academicPaperAnnexEntity.setPaperAnnexId(Integer.parseInt(id));
            list.add(academicPaperAnnexEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
