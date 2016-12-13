package by.mnk.htp.glotovs.msr.services.interfaces;

import by.mnk.htp.glotovs.msr.entities.IEntity;
import by.mnk.htp.glotovs.msr.entities.MessageEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergey Glotov on 06.12.2016.
 */

public interface IMessageService<T extends IEntity, PK extends Serializable> extends IBaseService<T, PK> {
    public List<MessageEntity> getAllMessagesByChatId(Integer idChat) throws ServiceException;
}
