package by.mnk.htp.glotovs.msr.dao.interfaces;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.entities.IEntity;
import by.mnk.htp.glotovs.msr.entities.UserEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergey Glotov on 06.12.2016.
 */

public interface IFriendDao<T extends IEntity, PK extends Serializable> extends IBaseDao<T, PK>  {

    public List<UserEntity> getAllFriendsByUserId(Integer idUser) throws DaoException ;

    public boolean deleteFriendEntity(Integer idUser, Integer userFriendId) throws DaoException;
}
