package by.mnk.htp.glotovs.msr.services.interfaces;

import by.mnk.htp.glotovs.msr.entities.IEntity;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.vo.UserPaginationVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergey Glotov on 06.12.2016.
 */

public interface IUserService<T extends IEntity, PK extends Serializable> extends IBaseService<T, PK>{

    public UserEntity getUserEntityByPhone(String phone) throws ServiceException;

    public UserPaginationVO paginationUsers(String page, Integer countPerPage) throws ServiceException;

    public List getAll() throws ServiceException;
}
