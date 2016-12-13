package by.mnk.htp.glotovs.msr.dao.impl;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.interfaces.IMessageDao;
import by.mnk.htp.glotovs.msr.entities.ChatEntity;
import by.mnk.htp.glotovs.msr.entities.MessageEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Sefire on 25.10.2016.*/

@Repository
public class MessageDao extends BaseDao<MessageEntity, Integer> implements IMessageDao<MessageEntity, Integer> {

    private static Logger log = Logger.getLogger(MessageDao.class);
    public List<MessageEntity> getAllMessagesByChatId(Integer idChat) throws DaoException {
        List<MessageEntity> messageEntityList = null;

        log.info("Get messages by idChat:" + idChat);
        try {
            Query query = getSession().createQuery("SELECT M FROM MessageEntity as M where M.chatEntity.idChat=:idChat");
            query.setParameter("idChat", idChat);
            query.setCacheable(true);
            messageEntityList = ( List<MessageEntity> ) query.list();
            log.info("Get messages by idChat:" + idChat);
        } catch (HibernateException e) {
            log.error("Error get " + getPersistentClass() + " in Dao" + e);
            throw new DaoException(e);
        }
        return messageEntityList;
    }

    private Class getPersistentClass() {
        return (Class<UserDao>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
 }
