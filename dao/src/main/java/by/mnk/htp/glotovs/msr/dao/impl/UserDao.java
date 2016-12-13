package by.mnk.htp.glotovs.msr.dao.impl;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.interfaces.IUserDao;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Sefire on 24.10.2016.
 */
@Repository
public class UserDao extends BaseDao<UserEntity, Integer> implements IUserDao<UserEntity, Integer> {
    private static Logger log = Logger.getLogger(UserDao.class);

    public UserEntity getUserEntityByPhone(String phone) throws DaoException {
        UserEntity userEntity = null;

        log.info("Get class by phone:" + phone);
        try {

            Query query = getSession().createQuery("SELECT U FROM UserEntity as U where U.phone = :phone");
            query.setParameter("phone",phone);
            query.setCacheable(true);
            userEntity = (UserEntity) query.uniqueResult();
            log.info("get clazz:" + userEntity);
        } catch (HibernateException e) {
            log.error("Error get " + getPersistentClass() + " in Dao" + e);
            throw new DaoException(e);
        }
        return userEntity;
    }

    public List getAll() throws DaoException {
        List<UserEntity> userEntityList = null;

        log.info("Get all users");

        try {
            Query query = getSession().createQuery("FROM UserEntity ");
            query.setCacheable(true);
            userEntityList = query.list();
            log.info("got all users!");
        } catch (HibernateException e) {
            log.error("Error get " + getPersistentClass() + " in Dao" + e);
            throw new DaoException(e);
        }
        return userEntityList;
    }

    public Integer totalUsersCount() throws DaoException {
        Integer totalUsersCount = 0;

        log.info("Get totalUsersCount");

        try {

            Query query = getSession().createQuery("select count(user) from UserEntity user");
            query.setCacheable(true);
            Long totalUsers = (Long) query.uniqueResult();
             totalUsersCount = totalUsers != null ? totalUsers.intValue() : null;
            log.info("got totalUsersCount!");
        } catch (HibernateException e) {
            log.error("Error get " + getPersistentClass() + " in Dao" + e);
            throw new DaoException(e);
        }
        return totalUsersCount;
    }

    public List<UserEntity> getPartUsersPagination (Integer count, Integer startPosition) throws DaoException{
        List<UserEntity>  userEntityList = null;
        try {
            String hql = "from UserEntity ";
            Query query = getSession().createQuery(hql);
            query.setFirstResult(startPosition);
            query.setMaxResults(count);
            query.setCacheable(true);
            userEntityList = query.list();
        } catch (HibernateException e) {
            log.error("Error get " + " in Dao" + e);
            throw new DaoException(e);
        }
        return userEntityList;
    }

    private Class getPersistentClass() {
        return (Class<UserDao>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
