package by.mnk.htp.glotovs.msr.services.interfaces;

import by.mnk.htp.glotovs.msr.entities.IEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;

import java.io.Serializable;

/**
 * Created by Sefire on 25.10.2016.
 */
public interface IBaseService<T extends IEntity, PK extends Serializable> {

    boolean saveOrUpdate(T t) throws ServiceException;

    T get(PK pk) throws ServiceException;

    T load(PK pk) throws ServiceException;

    boolean delete(T t) throws ServiceException;
}
