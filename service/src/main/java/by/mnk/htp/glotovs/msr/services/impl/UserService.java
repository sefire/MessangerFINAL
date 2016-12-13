package by.mnk.htp.glotovs.msr.services.impl;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.interfaces.IUserDao;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.interfaces.IUserService;
import by.mnk.htp.glotovs.msr.vo.UserPaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sefire on 25.10.2016.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService extends BaseService<UserEntity, Integer> implements IUserService<UserEntity, Integer>  {

    @Autowired
    IUserDao userDao;

    public UserEntity get(Integer id) throws ServiceException {
        UserEntity userEntity = new UserEntity();
        try {
            userEntity = (UserEntity) userDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userEntity;
    }

    public UserEntity load(Integer id) throws ServiceException {
        UserEntity userEntity = new UserEntity();
        try {
            userEntity = (UserEntity) userDao.load(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userEntity;
    }

    public UserEntity getUserEntityByPhone(String phone) throws ServiceException {
        UserEntity userEntity = null;
        try {
            userEntity = userDao.getUserEntityByPhone(phone);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userEntity;
    }

    public UserPaginationVO paginationUsers(String page, Integer countPerPage) throws ServiceException {
        UserPaginationVO userPaginationVO = new UserPaginationVO();

        Integer newPage = page != null ? Integer.valueOf(page) : 1;
        List<UserEntity> userEntityList = null;
        Integer totalUsersCount = 0;
        try {
            totalUsersCount = userDao.totalUsersCount();
            Integer first = countPerPage * (newPage - 1);
            userEntityList = userDao.getPartUsersPagination(countPerPage, first);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        userPaginationVO.setPage(String.valueOf(newPage));
        userPaginationVO.setTotalUsersCount(totalUsersCount);
        userPaginationVO.setUserEntityList(userEntityList);

        return userPaginationVO;
    }

    public List getAll() throws ServiceException {
        List userEntities = null;
        try {
            userEntities = userDao.getAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return userEntities;
    }
}
