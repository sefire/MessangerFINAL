package by.mnk.htp.glotovs.msr.services.impl;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.impl.BaseDao;
import by.mnk.htp.glotovs.msr.dao.impl.ChatDao;
import by.mnk.htp.glotovs.msr.dao.interfaces.IBaseDao;
import by.mnk.htp.glotovs.msr.dao.interfaces.IChatDao;
import by.mnk.htp.glotovs.msr.entities.ChatEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.interfaces.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sefire on 22.11.2016.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ChatService extends BaseService<ChatEntity, Integer> implements IChatService<ChatEntity, Integer>  {

    @Autowired
    IChatDao chatDao;

    public List<ChatEntity> getUserChatsByUserId(Integer id) throws ServiceException {
        List<ChatEntity> chatEntityList = null;
        try {
            chatEntityList = chatDao.getUserChatsByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return chatEntityList;
    }

    public ChatEntity get(Integer id) throws ServiceException {
        ChatEntity chatEntity = new ChatEntity();

        try {
            chatEntity = (ChatEntity) chatDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return chatEntity;
    }

    public ChatEntity load(Integer id) throws ServiceException {
        ChatEntity chatEntity = new ChatEntity();
        try {
            chatEntity = (ChatEntity) chatDao.load(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return chatEntity;
    }
}