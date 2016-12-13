package by.mnk.htp.glotovs.msr.services.impl;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.interfaces.IMessageDao;
import by.mnk.htp.glotovs.msr.entities.MessageEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.interfaces.IMessageService;
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
public class MessageService extends BaseService<MessageEntity, Integer> implements IMessageService<MessageEntity, Integer> {

    @Autowired
    IMessageDao messageDao;

    public MessageEntity get(Integer id) throws ServiceException {
        MessageEntity messageEntity = new MessageEntity();

        try {
            messageEntity = (MessageEntity) messageDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return messageEntity;
    }

    public MessageEntity load(Integer id) throws ServiceException {
        MessageEntity messageEntity = new MessageEntity();
        try {
            messageEntity = (MessageEntity) messageDao.load(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return messageEntity;
    }

    public List<MessageEntity> getAllMessagesByChatId(Integer idChat) throws ServiceException  {
        List<MessageEntity> messageEntityList = null;
        try {
            messageEntityList = messageDao.getAllMessagesByChatId(idChat);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return messageEntityList;
    }
}
