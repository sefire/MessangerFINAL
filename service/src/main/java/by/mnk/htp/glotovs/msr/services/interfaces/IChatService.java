package by.mnk.htp.glotovs.msr.services.interfaces;

import by.mnk.htp.glotovs.msr.entities.ChatEntity;
import by.mnk.htp.glotovs.msr.entities.IEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergey Glotov on 06.12.2016.
 */

public interface IChatService<T extends IEntity, PK extends Serializable> extends IBaseService<T, PK> {
    public List<ChatEntity> getUserChatsByUserId(Integer id) throws ServiceException;
}
