package by.mnk.htp.glotovs.msr.services.impl;

import by.mnk.htp.glotovs.msr.dao.interfaces.IUserDao;
import by.mnk.htp.glotovs.msr.entities.FriendEntity;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.interfaces.IFriendService;
import by.mnk.htp.glotovs.msr.services.interfaces.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration("/testContextServices.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class FriendServiceTest {

    @Autowired
    private IFriendService friendService;


    @Autowired
    private IUserService userService;

    private FriendEntity friendEntity;

    private UserEntity userEntity1;
    private UserEntity userEntity2;
    private List<UserEntity> userEntityList;

    @Before
    public void initParam() {

        userEntity1 = new UserEntity(1, "+1", "Sergey", "Glotov", 25, "Belarus", "Minsk", "1", "user", "true", "false");
        userEntity2 = new UserEntity(2, "+2", "Sergey2", "Glotov2", 25, "Belarus", "Minsk", "1", "user", "true", "false");
        userEntityList = Arrays.asList(userEntity1, userEntity2);
    }


    //public boolean deleteFriendEntity(Integer idUser, Integer userFriendId) throws ServiceException {
    @Test
    public void deleteFriendEntity() {
        try {
            friendEntity = new FriendEntity();
            friendEntity.setId(1);
            userService.saveOrUpdate(userEntity1);
            friendEntity.setUserEntity(userEntity1);
            friendService.saveOrUpdate(friendEntity);
            assertTrue(friendService.deleteFriendEntity(1, 0));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getAllFriendsByUserId() {
        try {

            friendEntity = new FriendEntity();
            friendEntity.setId(1);
            userService.saveOrUpdate(userEntity1);
            friendEntity.setUserEntity(userEntity1);
            friendService.saveOrUpdate(friendEntity);
            assertNotNull(friendService.getAllFriendsByUserId(1));
            assertTrue(1 == friendService.getAllFriendsByUserId(1).size());
            assertNull(friendService.getAllFriendsByUserId(2));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        try {
            friendEntity = new FriendEntity();
            friendEntity.setId(1);
            friendEntity.setUserEntity(userEntity1);
            friendService.saveOrUpdate(friendEntity);
            assertEquals(friendService.get(friendEntity.getId()), friendEntity);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void load() {
        try {
            friendEntity = new FriendEntity();
            friendEntity.setId(1);
            friendEntity.setUserEntity(userEntity1);
            friendService.saveOrUpdate(friendEntity);
            assertEquals(friendService.load(friendEntity.getId()), friendEntity);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
