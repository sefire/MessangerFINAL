package by.mnk.htp.glotovs.msr.dao.interfaces;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.impl.BaseDao;
import by.mnk.htp.glotovs.msr.entities.ChatEntity;
import by.mnk.htp.glotovs.msr.entities.IEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergey Glotov on 06.12.2016.
 */

public interface IChatDao<T extends IEntity, PK extends Serializable> extends IBaseDao<T, PK> {

    public List<ChatEntity> getUserChatsByUserId(Integer idUser) throws DaoException;

}
