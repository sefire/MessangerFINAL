package by.mnk.htp.glotovs.msr.dao.impl;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.interfaces.IBaseDao;
import by.mnk.htp.glotovs.msr.entities.IEntity;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Repository
public class BaseDao<T extends IEntity, PK extends Serializable> implements IBaseDao<T, PK> {

    private static Logger log = Logger.getLogger(BaseDao.class);

    public BaseDao() {
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public boolean saveOrUpdate(T t) throws DaoException {
        log.info("Start saveOrUpdate:" + t);
        try {
            getSession().saveOrUpdate(t);
            log.info("End saveOrUpdate:" + t);
            return true;
        } catch (HibernateException e) {
            log.error("Error save or update in Dao" + e);
            throw new DaoException(e);
        }
    }

    public T get(PK id) throws DaoException {
        log.info("Start get class by id:" + id);
        T t = null;
        try {
            t = (T) getSession().get(getPersistentClass(), id);
            log.info("End get class by id: " + t);
        } catch (HibernateException e) {
            log.error("Error get " + getPersistentClass() + " in Dao" + e);
            throw new DaoException(e);
        }
        return t;
    }

    public T load(PK id) throws DaoException {
        log.info("Start loading class by id:" + id);
        T t = null;
        try {
            t = (T) getSession().load(getPersistentClass(), id);
            log.info("End loading class");
        } catch (HibernateException e) {
            log.error("Error load() " + getPersistentClass() + " in Dao" + e);
            throw new DaoException(e);
        }
        return t;
    }

    public boolean delete(T t) throws DaoException {
        try {
            getSession().delete(t);
            log.info("Delete:" + t);
            return true;
        } catch (HibernateException e) {
            log.error("Error delete in Dao" + e);
            throw new DaoException(e);
        }
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
