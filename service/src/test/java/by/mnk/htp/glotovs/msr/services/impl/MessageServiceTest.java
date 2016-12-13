package by.mnk.htp.glotovs.msr.services.impl;

import by.mnk.htp.glotovs.msr.dao.interfaces.IChatDao;
import by.mnk.htp.glotovs.msr.dao.interfaces.IMessageDao;
import by.mnk.htp.glotovs.msr.dao.interfaces.IUserDao;
import by.mnk.htp.glotovs.msr.entities.ChatEntity;
import by.mnk.htp.glotovs.msr.entities.MessageEntity;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.interfaces.IChatService;
import by.mnk.htp.glotovs.msr.services.interfaces.IMessageService;
import by.mnk.htp.glotovs.msr.services.interfaces.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.*;

@ContextConfiguration("/testContextServices.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class MessageServiceTest {

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IChatService chatService;

    private MessageEntity messageEntity;
    private ChatEntity chatEntity1;
    private UserEntity userEntity1;

    @Autowired
    private IChatDao chatDao;

    @Before
    public void initParam() {

        userEntity1 = new UserEntity(1, "+1", "Sergey", "Glotov", 25, "Belarus", "Minsk", "1", "user", "true", "false");
        chatEntity1 = new ChatEntity(1, new Date(1451665447567L));
        messageEntity = new MessageEntity();
        messageEntity.setId(1);
        messageEntity.setUserEntity(userEntity1);
        messageEntity.setChatEntity(chatEntity1);
        messageEntity.setDateTime(new Date(1451665447567L));

    }

    @Test
    public void getAllMessagesByChatId() {
        try {
            userService.saveOrUpdate(userEntity1);
            chatService.saveOrUpdate(chatEntity1);
            messageService.saveOrUpdate(messageEntity);
            assertNotNull(messageService.getAllMessagesByChatId(1));
            assertTrue(1 == messageService.getAllMessagesByChatId(1).size());
            assertNull(messageService.getAllMessagesByChatId(2));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        try {
            userService.saveOrUpdate(userEntity1);
            chatService.saveOrUpdate(chatEntity1);
            messageService.saveOrUpdate(messageEntity);
            assertEquals(messageService.get(messageEntity.getId()), messageEntity);
            assertNotNull(messageService.get(messageEntity.getId()));
            System.out.println("OK");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void load() {
        try {
            userService.saveOrUpdate(userEntity1);
            chatService.saveOrUpdate(chatEntity1);
            messageService.saveOrUpdate(messageEntity);
            assertEquals(messageService.load(messageEntity.getId()), messageEntity);
            assertNotNull(messageService.load(messageEntity.getId()));
            System.out.println("OK");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
