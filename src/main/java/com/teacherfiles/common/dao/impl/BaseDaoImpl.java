package com.teacherfiles.common.dao.impl;//package com.pedmoni.common.dao.impl;
//
//import com.pedmoni.common.dao.BaseDao;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.hibernate.*;
//import org.hibernate.engine.spi.SessionFactoryImplementor;
//import org.hibernate.hql.internal.ast.QueryTranslatorImpl;
//import org.hibernate.type.Type;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.orm.hibernate3.HibernateCallback;
//import org.springframework.orm.hibernate3.HibernateTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.io.Serializable;
//import java.sql.SQLException;
//import java.util.Collections;
//import java.util.List;
//
///**
// * @Program: teacher_files
// * @Author: soldier
// * @Email： 583403411@qq.com
// * @Create: 2019-04-29 07:40
// * @Describe：
// **/
//@Repository
//public class BaseDaoImpl<T> implements BaseDao<T>
//{
//    HibernateTemplate hibernateTemplate;
//
//    public HibernateTemplate getHibernateTemplate() {
//        return hibernateTemplate;
//    }
//
//    protected Log log = LogFactory.getLog(getClass());
//
//    private SessionFactory sessionFactory;
//
//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    @Autowired
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    private Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }
//
//    @Autowired
//    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//        this.hibernateTemplate = hibernateTemplate;
//    }
//
//    protected final Log logger = LogFactory.getLog(getClass());
//
//    @Override
//    public T save(T entity) {
//        try {
//            getHibernateTemplate().save(entity);
//        } catch (RuntimeException e) {
//            logger.error("save failed", e);
//            throw e;
//        }
//        return entity;
//    }
//
//    @Override
//    public T merge(T entity) {
//        getHibernateTemplate().merge(entity);
//        return entity;
//    }
//
//    @Override
//    public void update(T entity) throws DataAccessException {
//        getHibernateTemplate().update(entity);
//    }
//    @Override
//    public T saveOrUpdate(T entity) {
//        getHibernateTemplate().saveOrUpdate(entity);
//        return entity;
//    }
//
//    @Override
//    public T get(Class<T> clazz, Serializable id) {
//        return getHibernateTemplate().get(clazz, id);
//    }
//
//    @Override
//    public void remove(Class<T> clazz, Serializable id) {
//        getHibernateTemplate().delete(get(clazz, id));
//    }
//
//    @Override
//    public void remove(T entity) throws DataAccessException {
//        getHibernateTemplate().delete(entity);
//    }
//
//    @Override
//    public void evict(T entity) {
//        getHibernateTemplate().evict(entity);
//
//    }
//
//    @Override
//    public T getById(T entity, Integer id) {
//
//        return (T) getHibernateTemplate().get((Class<T>) entity, id);
//    }
//
//    @Override
//    public List<T> getAll(final Class<T> clazz) {
//        Object result = getHibernateTemplate().execute(
//                new HibernateCallback<Object>() {
//                    @Override
//                    public Object doInHibernate(Session session)
//                            throws HibernateException, SQLException {
//                        String hql = "from " + clazz.getName();
//                        Query query = session.createQuery(hql);
//                        return query.list();
//                    }
//                });
//        return (List) result;
//    }
//
//    @Override
//    public List<T> findByHql(String queryString, Object[] params) {
//
//        return (List<T>) getHibernateTemplate().find(queryString, params);
//    }
//
//    @Override
//    public List<T> findByHql(final String queryString, final Object[] values,
//                             final int start, final int limit) {
//        return (List) getHibernateTemplate().execute(
//                new HibernateCallback<List>() {
//                    @Override
//                    public List doInHibernate(Session session)
//                            throws HibernateException, SQLException {
//                        Query query = session.createQuery(queryString);
//                        query.setFirstResult(start).setMaxResults(limit);
//                        if (values != null) {
//                            for (int i = 0; i < values.length; i++) {
//                                query.setParameter(i, values[i]);
//                            }
//                        }
//                        return query.list();
//                    }
//                });
//    }
//
//    @Override
//    public void flush() {
//        getHibernateTemplate().flush();
//    }
//
//    @Override
//    public Long update(final String queryString, final Object[] values) {
//
//        int c = getHibernateTemplate().executeWithNativeSession(
//                new HibernateCallback<Integer>() {
//                    @Override
//                    public Integer doInHibernate(Session session)
//                            throws HibernateException {
//                        Query queryObject = session.createQuery(queryString);
//
//                        if (values != null) {
//                            for (int i = 0; i < values.length; i++) {
//                                queryObject.setParameter(i, values[i]);
//                            }
//                        }
//                        return queryObject.executeUpdate();
//                    }
//                });
//        return new Long(c);
//    }
//
//    @Override
//    public Long updateBySql(final String queryString, final Object[] values) {
//        int c = getHibernateTemplate().executeWithNativeSession(
//                new HibernateCallback<Integer>() {
//                    @Override
//                    public Integer doInHibernate(Session session)
//                            throws HibernateException {
//                        Query queryObject = session.createSQLQuery(queryString);
//
//                        if (values != null) {
//                            for (int i = 0; i < values.length; i++) {
//                                queryObject.setParameter(i, values[i]);
//                            }
//                        }
//                        return queryObject.executeUpdate();
//                    }
//                });
//        return new Long(c);
//    }
//
//    @Override
//    public List<T> find(String hql) {
//        Query q = this.getCurrentSession().createQuery(hql);
//        return q.list();
//    }
////
////    public Result find(
////
////            final String queryString, final Object[] values, final Type[] types,
////            final int start, final int limit) {
////        try {
////            Result result = new Result(start, limit);
////
////            if (start != -1 && limit != -1) {
////                result.setTotal(getTotalItems(queryString, values).intValue());
////            }
////
////            HibernateTemplate ht = getHibernateTemplate();
////
////            List data = ht.executeFind(new HibernateCallback<List>() {
////                public List doInHibernate(Session session)
////                        throws HibernateException {
////                    Query queryObject = session.createQuery(queryString);
////
////                    setParameters(queryObject, values, types);
////
////                    if (start >= 0) {
////                        queryObject.setFirstResult(start);
////                    }
////
////                    if (limit >= 0) {
////                        queryObject.setMaxResults(limit);
////                    }
////
////                    return queryObject.list();
////                }
////            });
////
////            result.setData(data);
////
////            if (start == -1 && limit == -1) {
////                result.setTotal(data.size());
////            }
////
////            return result;
////        } catch (RuntimeException e) {
////            e.printStackTrace();
////
////            throw e;
////        }
////    }
//
//    @Override
//    public List<T> findByExample(String queryString, Object[] valueArray) {
//        return (List<T>) getHibernateTemplate().find(queryString, valueArray);
//    }
//
//    /**
//     *
//     * @param queryString
//     * @param values
//     * @param types
//     * @return
//     * @throws DataAccessException
//     */
//    protected int bulkUpdate(final String queryString, final Object[] values,
//                             final Type[] types) throws DataAccessException {
//
//        Integer updateCount = (Integer) getHibernateTemplate().execute(
//                new HibernateCallback<Integer>() {
//                    @Override
//                    public Integer doInHibernate(Session session)
//                            throws HibernateException {
//                        Query queryObject = session.createQuery(queryString);
//                        setParameters(queryObject, values, types);
//                        return new Integer(queryObject.executeUpdate());
//                    }
//                });
//        return updateCount.intValue();
//    }
//
//    /**
//     * SQL
//     *
//     * @param queryObject
//     * @param values
//     * @param types
//     */
//    static void setParameters(Query queryObject, Object[] values, Type[] types) {
//        if (values != null) {
//            if (types != null) {
//                for (int i = 0; i < values.length; i++) {
//                    queryObject.setParameter(i, values[i], types[i]);
//                }
//            } else {
//                for (int i = 0; i < values.length; i++) {
//                    queryObject.setParameter(i, values[i]);
//                }
//            }
//        }
//    }
//
////    @SuppressWarnings("rawtypes")
////    public Result findBySQL(final String sql, final Object[] values,
////                            final int start, final int limit) {
////
////
////        HibernateTemplate ht = getHibernateTemplate();
////
////        Result result = new Result(start, limit);
////        if (start != -1 && limit != -1) {
////
////            Number count = getTotalItems2(sql, values);
////            result.setTotal(count.intValue());
////        }
////
////        List data = ht.executeFind(new HibernateCallback<List>() {
////            public List doInHibernate(Session session)
////                    throws HibernateException {
////                SQLQuery query = session.createSQLQuery(sql);
////                if (values != null) {
////                    for (int i = 0; i < values.length; i++) {
////                        query.setParameter(i, values[i]);
////                    }
////                }
////                if (start >= 0) {
////                    query.setFirstResult(start);
////                }
////
////                if (limit >= 0) {
////                    query.setMaxResults(limit + 1);
////                }
////
////                return query.list();
////            }
////        });
////
////        result.setData(data);
////
////        if (start == -1 && limit == -1) {
////            result.setTotal(data.size());
////        }
////
////        return result;
////    }
//
//    /**
//     *
//     * @param queryString
//     * @param values
//     * @return
//     */
//    public Long getTotalItems(String queryString, final Object[] values) {
//        int orderByIndex = queryString.toUpperCase().indexOf(" ORDER BY ");
//
//        if (orderByIndex != -1) {
//            queryString = queryString.substring(0, orderByIndex);
//        }
//
//        QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(
//                queryString, queryString, Collections.EMPTY_MAP,
//                (SessionFactoryImplementor) getHibernateTemplate()
//                        .getSessionFactory());
//        queryTranslator.compile(Collections.EMPTY_MAP, false);
//        final String sql = "select count(*) from ("
//                + queryTranslator.getSQLString() + ") tmp_count_t";
//
//        Object reVal = getHibernateTemplate().execute(
//                new HibernateCallback<Object>() {
//                    public Object doInHibernate(Session session)
//                            throws HibernateException, SQLException {
//                        SQLQuery query = session.createSQLQuery(sql);
//                        if (values != null) {
//                            for (int i = 0; i < values.length; i++) {
//                                query.setParameter(i, values[i]);
//                            }
//                        }
//                        return query.uniqueResult();
//                    }
//                });
//        return new Long(reVal.toString());
//    }
//
//    /**
//     *
//     * @param queryString
//     * @param values
//     * @return
//     */
//    public Long getTotalItems2(String queryString, final Object[] values) {
//        int orderByIndex = queryString.toUpperCase().indexOf(" ORDER BY ");
//
//        if (orderByIndex != -1) {
//            queryString = queryString.substring(0, orderByIndex);
//        }
//
//        final String sql = "select count(*) from (" + queryString
//                + ") tmp_count_t";
//
//        Object reVal = getHibernateTemplate().execute(
//                new HibernateCallback<Object>() {
//                    public Object doInHibernate(Session session)
//                            throws HibernateException, SQLException {
//                        SQLQuery query = session.createSQLQuery(sql);
//                        if (values != null) {
//                            for (int i = 0; i < values.length; i++) {
//                                query.setParameter(i, values[i]);
//                            }
//                        }
//                        return query.uniqueResult();
//                    }
//                });
//        return new Long(reVal.toString());
//    }
//
//    @Override
//    public List queryByHql(final String queryString) {
//
//        return (List) getHibernateTemplate().execute(
//                new HibernateCallback<List>() {
//                    @Override
//                    public List doInHibernate(Session session)
//                            throws HibernateException, SQLException {
//                        Query query = session.createSQLQuery(queryString);
//
//                        return query.list();
//                    }
//                });
//    }
//
//    @Override
//    public List query(final String queryString) {
//
//        return (List) getHibernateTemplate().execute(
//                new HibernateCallback<List>() {
//                    @Override
//                    public List doInHibernate(Session session)
//                            throws HibernateException, SQLException {
//                        Query query = session.createSQLQuery(queryString);
//                        return query.list();
//                    }
//                });
//    }
//    @Override
//    public List query(final String queryString,final Object[] values) {
//
//        return (List) getHibernateTemplate().execute(
//                new HibernateCallback<List>() {
//                    @Override
//                    public List doInHibernate(Session session)
//                            throws HibernateException, SQLException {
//                        Query query = session.createSQLQuery(queryString);
//                        if (values != null) {
//                            for (int i = 0; i < values.length; i++) {
//                                query.setParameter(i, values[i]);
//                            }
//                        }
//                        return query.list();
//                    }
//                });
//    }
//
//    /**
//     * "select count(*) from User as sysuser";计算记录条数
//     *
//     * @param hql
//     * @return
//     */
//    @Override
//    public int findCount(String hql) {
//        Integer count = 0;
//        try {
//            count = getHibernateTemplate().find(hql).listIterator().nextIndex();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//
//
//
//
//
//}
