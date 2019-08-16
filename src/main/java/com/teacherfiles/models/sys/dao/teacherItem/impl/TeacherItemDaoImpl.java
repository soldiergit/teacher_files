package com.teacherfiles.models.sys.dao.teacherItem.impl;

import com.teacherfiles.common.vo.PageBean;
import com.teacherfiles.models.sys.dao.teacherItem.TeacherItemDao;
import com.teacherfiles.models.sys.model.TeacherItemEntity;
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
 * @title: TeacherItemDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:53
 */
@Component
public class TeacherItemDaoImpl implements TeacherItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(TeacherItemEntity teacherItemEntity) {
        sessionFactory.getCurrentSession().save(teacherItemEntity);
    }

    @Override
    public void delete(TeacherItemEntity teacherItemEntity) {
        sessionFactory.getCurrentSession().delete(teacherItemEntity);
    }

    @Override
    public void update(TeacherItemEntity teacherItemEntity) {
        sessionFactory.getCurrentSession().update(teacherItemEntity);
    }

    @Override
    public TeacherItemEntity findById(TeacherItemEntity teacherItemEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemEntity.class);

        List list = criteria.add(Restrictions.eq("itemId", teacherItemEntity.getItemId())).list();

        session.close();

        return list!=null&&list.size()>0? (TeacherItemEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherItemEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemEntity.class);

        if (key != null && !key.equals("")) {

            //搜索--实现：where deptId=? and (xxx like ? or xxx like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接
            dis.add(Restrictions.like("itemName", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("contractNumber", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("itemMember", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("memberName", key, MatchMode.ANYWHERE));

            //搜索
            List list = criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                            .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(TeacherItemEntity.class).setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public PageBean findByPerson(Integer itemPersonId, String key, PageBean<TeacherItemEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemEntity.class);

        //TODO 外键查询
        criteria.createAlias("itemPerson", "itemPerson").
                add(Restrictions.eq("itemPerson.teacherId", itemPersonId));

        if (key != null && !key.equals("")) {

            //搜索--实现：where itemPerson=? and (xxx like ? or xxx like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接
            dis.add(Restrictions.like("itemName", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("contractNumber", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("itemMember", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("memberName", key, MatchMode.ANYWHERE));
            List list = criteria.add(dis)
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {

            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                            .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(TeacherItemEntity.class)
                .createAlias("itemPerson", "itemPerson").add(Restrictions.eq("itemPerson.teacherId", itemPersonId))
                .setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public PageBean findByMember(String memberId, String key, PageBean<TeacherItemEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemEntity.class);

        //外键查询
//            criteria.createAlias("memberSet", "member").
//                    add(Restrictions.eq("member.teacherId", Integer.valueOf(memberId)));
        //伪外键查询--首要条件
        criteria.add(Restrictions.like("itemMember", memberId,  MatchMode.ANYWHERE));

        if (key != null && !key.equals("")) {

            //搜索--实现：where deptId=? and (xxx like ? or xxx like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接
            dis.add(Restrictions.like("itemName", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("contractNumber", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("itemMember", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("memberName", key, MatchMode.ANYWHERE));
            List list = criteria.add(dis)
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {

            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                            .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(TeacherItemEntity.class).add(Restrictions.like("itemMember", memberId))
                .setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    /**
     * 0:根据项目名称等String类型查询
     * 1：根据主持人id查询
     * 2：根据主持人所属教研室查询
     * 3：根据项目类型查询--itemType
     * 4：根据项目级别--itemCategory
     * 5：根据项目类别--itemLevel
     */
    @Override
    public PageBean findByDept(Integer deptId, String key, PageBean<TeacherItemEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemEntity.class);

        //TODO 多重外键查询
        criteria.createAlias("itemPerson", "itemPerson").
                add(Restrictions.eq("itemPerson.dept.deptId", deptId));

        if (key != null && !key.equals("")) {

            //搜索--实现：where deptId=? and (xxx like ? or xxx like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接

            String[] thisKey = key.split(",");
            if ("0".equals(thisKey[0])) {
                dis.add(Restrictions.like("itemName", thisKey[1], MatchMode.ANYWHERE));
                dis.add(Restrictions.like("contractNumber", thisKey[1], MatchMode.ANYWHERE));
                dis.add(Restrictions.like("itemMember", thisKey[1], MatchMode.ANYWHERE));
                dis.add(Restrictions.like("memberName", thisKey[1], MatchMode.ANYWHERE));
                criteria.add(dis);
            } else if ("1".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.add(Restrictions.eq("itemPerson.teacherId", newKey));
                } catch (Exception e) {
                    System.out.println("1，key:"+key+",不能转换为数字！");
                }
            } else if ("2".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.add(Restrictions.eq("itemPerson.unitIds", newKey+""));
                } catch (Exception e) {
                    System.out.println("2，key:"+key+",不能转换为数字！");
                }
            } else if ("3".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.add(Restrictions.eq("itemType", newKey));
                } catch (Exception e) {
                    System.out.println("3，key:"+key+",不能转换为数字！");
                }
            } else if ("4".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.createAlias("itemCategory", "itemCategory").
                            add(Restrictions.eq("itemCategory.id", newKey));
                } catch (Exception e) {
                    System.out.println("4，key:"+key+",不能转换为数字！");
                }
            } else if ("5".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.createAlias("itemLevel", "itemLevel").
                            add(Restrictions.eq("itemLevel.id", newKey));
                } catch (Exception e) {
                    System.out.println("5，key:"+key+",不能转换为数字！");
                }
            }

            List list = criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {

            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                            .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(TeacherItemEntity.class)
                .createAlias("itemPerson", "itemPerson").
                        add(Restrictions.eq("itemPerson.dept.deptId", deptId))
                .setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public List<TeacherItemEntity> findByExport(Integer deptId, String[] Ids, Integer itemPersonId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemEntity.class);

        //是否添加部门约束。系统管理员可以导出全部
        if (deptId != null) {
            //TODO 外键查询
            criteria.createAlias("itemPerson", "itemPerson").
                    add(Restrictions.eq("itemPerson.dept.deptId", deptId));
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

        //是否添加主持人外键约束
        if (itemPersonId != null) {
            //TODO 外键查询
            criteria.createAlias("itemPerson", "itemPerson").
                    add(Restrictions.eq("itemPerson.teacherId", itemPersonId));
        }

        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TeacherItemEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TeacherItemEntity teacherItemEntity = new TeacherItemEntity();
            teacherItemEntity.setItemId(Integer.parseInt(id));
            list.add(teacherItemEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
