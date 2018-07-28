package org.young.base.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.young.base.entity.AbstractEntity;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * Created by Young on 2017/9/2.
 */
@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW )
public abstract class AbstractDao<T> extends HibernateDaoSupport {

    private Class<T> clazz;

    public AbstractDao() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    public void setDaoSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public T get(Long id) {
        return getHibernateTemplate().get(clazz, id);
    }

    protected T get(String queryString, Object... values) {
        List<T> list = find(queryString, values);
        return (list.size() > 0 ? list.get(0) : null);
    }


    protected List<T> find(String queryString, Object... values) {
        return (List<T>) getHibernateTemplate().find(fromQuery(queryString), values);
    }

    protected boolean exists(String queryString, Object... values) {
        return (get(queryString, values) != null);
    }

    public Long save(Object entity) {
        if (entity instanceof AbstractEntity) {
            AbstractEntity abstractEntity = ((AbstractEntity) entity);
            abstractEntity.setCreatedDate(new Date());
            abstractEntity.setLastModifiedDate(new Date());
        }
        return (Long) getHibernateTemplate().save(entity);
    }

    public void update(Object entity) {
        if (entity instanceof AbstractEntity) {
            AbstractEntity abstractEntity = ((AbstractEntity) entity);
            abstractEntity.setLastModifiedDate(new Date());
        }
        getHibernateTemplate().update(entity);
    }


    public void delete(Object entity) {
        getHibernateTemplate().delete(entity);
    }


    public void clear() {
        getHibernateTemplate().clear();
    }

    private String fromQuery(String queryString) {
        return "from " + clazz.getSimpleName() + " as " + clazz.getSimpleName().toLowerCase() + " " + queryString;
    }


}