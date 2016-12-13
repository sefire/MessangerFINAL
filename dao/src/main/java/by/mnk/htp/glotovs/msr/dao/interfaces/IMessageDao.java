package by.mnk.htp.glotovs.msr.dao.interfaces;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.entities.IEntity;
import by.mnk.htp.glotovs.msr.entities.MessageEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergey Glotov on 06.12.2016.
 */

public interface IMessageDao<T extends IEntity, PK extends Serializable> extends IBaseDao<T, PK> {
    public List<MessageEntity> getAllMessagesByChatId(Integer idChat) throws DaoException;
}
