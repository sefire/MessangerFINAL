package by.mnk.htp.glotovs.msr.dao.impl;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.interfaces.IFriendDao;
import by.mnk.htp.glotovs.msr.entities.FriendEntity;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Sefire on 25.10.2016.*/

@Repository
public class FriendDao extends BaseDao<FriendEntity, Integer> implements IFriendDao<FriendEntity, Integer> {
    private static Logger log = Logger.getLogger(FriendDao.class);

    public List<UserEntity> getAllFriendsByUserId(Integer idUser) throws DaoException {
        List<UserEntity> userEntityList = null;

        log.info("Get friends by id:" + idUser);
        try {
            Query query = getSession().createQuery("SELECT U FROM UserEntity as U where U.idUser in " +
                    "(select F.userFriendId from FriendEntity AS F where F.userEntity.idUser =:idUser)");
            query.setParameter("idUser", idUser);
            query.setCacheable(true);
            userEntityList = ( List<UserEntity> ) query.list();
            log.info("Get friends by id:" + idUser);
        } catch (HibernateException e) {
            log.error("Error get " + getPersistentClass() + " in Dao" + e);
            throw new DaoException(e);
        }
        return userEntityList;
    }

    public boolean deleteFriendEntity(Integer idUser, Integer userFriendId) throws DaoException {
        boolean isDeleted = false;

        log.info("Delete friend with idUser, userFriendId: " + idUser + ", " + userFriendId);
        try {
            Query query = getSession().createQuery("delete from FriendEntity as F  where F.userEntity.idUser =:idUser  and  " +
                    "F.userFriendId =:userFriendId" );
            query.setParameter("idUser", idUser);
            query.setParameter("userFriendId", userFriendId);
            query.setCacheable(true);
            query.executeUpdate();
            log.info("Deleted friend with idUser, userFriendId: " + idUser + ", " + userFriendId);
            isDeleted = true;
        } catch (HibernateException e) {
            log.error("Error delete " + getPersistentClass() + " in Dao" + e);
            throw new DaoException(e);
        }
        return isDeleted;
    }

    private Class getPersistentClass() {
        return (Class<UserDao>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
