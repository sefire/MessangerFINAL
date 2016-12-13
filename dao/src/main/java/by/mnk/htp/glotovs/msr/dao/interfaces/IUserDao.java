package by.mnk.htp.glotovs.msr.dao.interfaces;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.entities.IEntity;
import by.mnk.htp.glotovs.msr.entities.UserEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergey Glotov on 06.12.2016.
 */

public interface IUserDao<T extends IEntity, PK extends Serializable> extends IBaseDao<T, PK> {
    public UserEntity getUserEntityByPhone(String phone) throws DaoException;

    public List getAll() throws DaoException;

    public Integer totalUsersCount() throws DaoException;

    public List<UserEntity> getPartUsersPagination (Integer count, Integer startPosition) throws DaoException;
}
