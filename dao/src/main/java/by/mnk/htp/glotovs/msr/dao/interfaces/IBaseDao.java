package by.mnk.htp.glotovs.msr.dao.interfaces;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.entities.IEntity;

import java.io.Serializable;

/**
 * Created by Sefire on 24.10.2016.
 */
public interface IBaseDao<T extends IEntity, PK extends Serializable> {

    boolean saveOrUpdate(T t) throws DaoException;

    T get(PK pk) throws DaoException;

    T load(PK pk) throws DaoException;

    boolean delete(T t) throws DaoException;
}
