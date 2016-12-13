package by.mnk.htp.glotovs.msr.services.impl;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.impl.BaseDao;
import by.mnk.htp.glotovs.msr.dao.interfaces.IBaseDao;
import by.mnk.htp.glotovs.msr.entities.IEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.interfaces.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by Sefire on 20.11.2016.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public abstract class BaseService<T extends IEntity, PK extends Serializable> implements IBaseService<T, PK> {

    @Autowired
    private IBaseDao<T,PK> baseDao;

    public void setBaseDao(IBaseDao<T, PK> baseDao) {
        this.baseDao = baseDao;
    }

    public IBaseDao<T, PK> getBaseDao() {
        return baseDao;
    }

    public BaseService(){
    }

/*    @Autowired
    public BaseService(IBaseDao<T,PK> baseDao) {
        this.baseDao = baseDao;
    }*/

    public abstract T get(PK id) throws ServiceException;

    public abstract T load(PK id) throws ServiceException;

    public boolean saveOrUpdate(T t) throws ServiceException {
        try {
            baseDao.saveOrUpdate(t);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean delete(T t) throws ServiceException {
        try {
            baseDao.delete(t);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
