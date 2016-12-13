package by.mnk.htp.glotovs.msr.services.impl;

import by.mnk.htp.glotovs.msr.dao.impl.ChatDao;
import by.mnk.htp.glotovs.msr.dao.interfaces.IBaseDao;
import by.mnk.htp.glotovs.msr.dao.interfaces.IChatDao;
import by.mnk.htp.glotovs.msr.dao.interfaces.IUserDao;
import by.mnk.htp.glotovs.msr.entities.ChatEntity;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.interfaces.IBaseService;
import by.mnk.htp.glotovs.msr.services.interfaces.IChatService;
import by.mnk.htp.glotovs.msr.services.interfaces.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class ChatServiceTest {

    @Autowired
    private IChatService chatService;

    @Autowired
    private IUserService userService;


    @Autowired
    private IChatDao chatDao;


    private UserEntity userEntity1;
    private UserEntity userEntity2;

    private ChatEntity chatEntity1;
    private ChatEntity chatEntity2;
    Set<ChatEntity> chatEntities;

    @Before
    public void initParam() {

        userEntity1 = new UserEntity(1, "+1", "Sergey", "Glotov", 25, "Belarus", "Minsk", "1", "user", "true", "false");
        userEntity2 = new UserEntity(2, "+2", "Sergey2", "Glotov2", 25, "Belarus", "Minsk", "1", "user", "true", "false");
        Set<UserEntity> userEntitySet = new HashSet<UserEntity>();
        userEntitySet.add(userEntity1);
        userEntitySet.add(userEntity2);

        chatEntity1 = new ChatEntity(1, new Date(1451665447567L));
        chatEntity1.setUserEntities(userEntitySet);
        chatEntity2 = new ChatEntity(2, new Date(2000001111111L));
        chatEntity2.setUserEntities(userEntitySet);

        chatEntities = new HashSet<ChatEntity>();
        chatEntities.add(chatEntity1);
        chatEntities.add(chatEntity2);
        userEntity1.setChatEntities(chatEntities);
        userEntity2.setChatEntities(chatEntities);
    }

    @Test
    public void get() {
        try {
            chatService.saveOrUpdate(chatEntity1);
            assertEquals(chatService.get(chatEntity1.getId()), chatEntity1);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void load() {
        try {
            chatService.saveOrUpdate(chatEntity1);
            assertEquals(chatService.load(chatEntity1.getId()).getId(), chatEntity1.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserChatsByUserId() {
        try {

            chatService.saveOrUpdate(chatEntity1);
            chatService.saveOrUpdate(chatEntity2);
            assertNotNull(chatService.getUserChatsByUserId(1));
            assertTrue(2 == chatService.getUserChatsByUserId(1).size());
            assertNull(chatService.getUserChatsByUserId(3));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}