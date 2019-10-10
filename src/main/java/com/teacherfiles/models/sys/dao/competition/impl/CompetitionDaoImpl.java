package com.teacherfiles.models.sys.dao.competition.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.competition.CompetitionDao;
import com.teacherfiles.models.sys.model.CompetitionEntity;
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
 * @title: CompetitionDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:48
 */
@Component
public class CompetitionDaoImpl implements CompetitionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(CompetitionEntity competitionEntity) {
        sessionFactory.getCurrentSession().save(competitionEntity);
    }

    @Override
    public void delete(CompetitionEntity competitionEntity) {
        sessionFactory.getCurrentSession().delete(competitionEntity);
    }

    @Override
    public void update(CompetitionEntity competitionEntity) {
        sessionFactory.getCurrentSession().update(competitionEntity);
    }

    @Override
    public CompetitionEntity findById(CompetitionEntity competitionEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionEntity.class);

        List list = criteria.add(Restrictions.eq("itemId", competitionEntity.getItemId())).list();

        session.close();

        return list!=null&&list.size()>0? (CompetitionEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<CompetitionEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionEntity.class);

        if (key != null && !key.equals("")) {

            //搜索
            List list = criteria.add(
                    Restrictions.or(
//                            Restrictions.or(Restrictions.like("itemName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("itemName", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(CompetitionEntity.class).setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public PageBean findByTeacher(Integer teacherId, String key, PageBean<CompetitionEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionEntity.class);

        //TODO 外键查询
        criteria.createAlias("teacher", "teacher").
                add(Restrictions.eq("teacher.teacherId", teacherId));

        if (key != null && !key.equals("")) {

            //搜索--实现：where teacherId=? and (xxx like ? or xxx like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接
            dis.add(Restrictions.like("itemName", key, MatchMode.ANYWHERE));
            List list = criteria.add(dis)
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {

            pageBean.setRows(criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(CompetitionEntity.class)
                .createAlias("teacher", "teacher").add(Restrictions.eq("teacher.teacherId", teacherId))
                .setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public PageBean findByAwardee(String teacherName, String key, PageBean<CompetitionEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionEntity.class);
        criteria.add(Restrictions.like("awardee", teacherName, MatchMode.ANYWHERE));

        if (key != null && !key.equals("")) {

            //搜索--实现：where awardee like ? and (xxx like ? or xxx like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接
            dis.add(Restrictions.like("itemName", teacherName, MatchMode.ANYWHERE));
            List list = criteria.add(dis)
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(CompetitionEntity.class)
                .add(Restrictions.like("awardee", teacherName, MatchMode.ANYWHERE))
                .setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    /**
     * 0:根据项目名称等String类型查询
     * 1：根据所属赛事id查询
     * 2：根据指导老师所属教研室--teacher.unitIds
     * 3：根据获奖等次查询--prizeGrade
     * 4：根据获奖级别查询--prizeLevel
     */
    @Override
    public PageBean findByDept(Integer deptId, String key, PageBean<CompetitionEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionEntity.class);

        //伪外键查询--首要条件
        criteria.add(Restrictions.eq("deptId", deptId));

        if (key != null && !key.equals("")) {

            //搜索--实现：where deptId=? and (xxx like ? or xxx like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接

            String[] thisKey = key.split(",");
            if ("0".equals(thisKey[0])) {
                if (thisKey.length == 2) {
                    dis.add(Restrictions.like("itemName", thisKey[1], MatchMode.ANYWHERE));
                    criteria.add(dis);
                }
            } else if ("1".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.add(Restrictions.eq("match.matchId", newKey));
                } catch (Exception e) {
                    System.out.println("1，key:"+key+",不能转换为数字！");
                }
            } else if ("2".equals(thisKey[0])) {
                try {
                    criteria.createAlias("teacher", "teacher").
                            add(Restrictions.like("teacher.unitIds", thisKey[1], MatchMode.ANYWHERE));
                } catch (Exception e) {
                    System.out.println("2，key:"+key+",不能转换为数字！");
                }
            } else if ("3".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.add(Restrictions.eq("prizeGrade.id", newKey));
                } catch (Exception e) {
                    System.out.println("2，key:"+key+",不能转换为数字！");
                }
            } else if ("4".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.add(Restrictions.eq("prizeLevel.id", newKey));
                } catch (Exception e) {
                    System.out.println("3，key:"+key+",不能转换为数字！");
                }
            }

            List list = criteria.add(dis)
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {

            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                            .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

//        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(CompetitionEntity.class).add(Restrictions.eq("deptId", deptId))
//                .setProjection(Projections.rowCount()).uniqueResult()));
        pageBean.setTotal(Math.toIntExact((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public List<CompetitionEntity> findByExport(Integer deptId, String[] Ids, Integer teacherId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionEntity.class);

        //是否添加部门约束。系统管理员可以导出全部
        if (deptId != null) {
            criteria.add(Restrictions.eq("deptId", deptId));
        }

        //是否根据id导出
        if (Ids!=null && Ids.length!=0) {
            //转Object数组
            Object[] ids = new Object[Ids.length];
            for(int i=0;i<Ids.length;i++){
                ids[i] = Integer.parseInt(Ids[i]);
            }
            criteria.add(Restrictions.in("itemId", ids));
        }

        //是否添加教师外键约束
        if (teacherId != null) {
            //TODO 外键查询
            criteria.createAlias("teacher", "teacher").
                    add(Restrictions.eq("teacher.teacherId", teacherId));
        }

        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<CompetitionEntity> list = new ArrayList<>();

        for (String id : Ids) {
            CompetitionEntity competitionEntity = new CompetitionEntity();
            competitionEntity.setItemId(Integer.parseInt(id));
            list.add(competitionEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
