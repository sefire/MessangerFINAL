package by.mnk.htp.glotovs.msr.services.impl;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.impl.BaseDao;
import by.mnk.htp.glotovs.msr.dao.impl.FriendDao;
import by.mnk.htp.glotovs.msr.dao.interfaces.IBaseDao;
import by.mnk.htp.glotovs.msr.dao.interfaces.IFriendDao;
import by.mnk.htp.glotovs.msr.entities.FriendEntity;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.interfaces.IFriendService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sefire on 22.11.2016.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FriendService extends BaseService<FriendEntity, Integer> implements IFriendService<FriendEntity, Integer> {

    @Autowired
    IFriendDao friendDao;

    public List<UserEntity> getAllFriendsByUserId(Integer id) throws ServiceException {
        List<UserEntity> userFriendsList = null;
        try {
            userFriendsList = friendDao.getAllFriendsByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userFriendsList;
    }

    public boolean deleteFriendEntity(Integer idUser, Integer userFriendId) throws ServiceException {
        boolean isDeleted = false;
        try {
            isDeleted = friendDao.deleteFriendEntity(idUser, userFriendId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    public FriendEntity get(Integer id) throws ServiceException {
        FriendEntity friendEntity = new FriendEntity();

        try {
            friendEntity = (FriendEntity) friendDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return friendEntity;
    }

    public FriendEntity load(Integer id) throws ServiceException {
        FriendEntity friendEntity = new FriendEntity();
        try {
            friendEntity = (FriendEntity) friendDao.load(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return friendEntity;
    }
}