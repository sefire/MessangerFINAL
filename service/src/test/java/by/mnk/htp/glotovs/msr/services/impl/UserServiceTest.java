package by.mnk.htp.glotovs.msr.services.impl;

import by.mnk.htp.glotovs.msr.dao.interfaces.IUserDao;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.interfaces.IBaseService;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration("/testContextServices.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class UserServiceTest {

    @Autowired
    private IUserDao userDao;


    @Autowired
    private IUserService userService;

    private UserEntity userEntity1;
    private UserEntity userEntity2;
    private List<UserEntity> userEntityList;

    @Before
    public void initParam() {

        userEntity1 = new UserEntity(1, "+1", "Sergey", "Glotov", 25, "Belarus", "Minsk", "1", "user", "true", "false");
        userEntity2 = new UserEntity(2, "+2", "Sergey2", "Glotov2", 25, "Belarus", "Minsk", "1", "user", "true", "false");
        userEntityList = Arrays.asList(userEntity1, userEntity2);
    }

    @Test
    public void getAll() throws Exception {
        try {
            assertTrue(userService.saveOrUpdate(userEntity1));
            assertTrue(userService.saveOrUpdate(userEntity2));
            List users = userService.getAll();
            assertNotNull(users);
            assertEquals(userEntityList, userService.getAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getUserEntityByPhoneTest() {
        try {
            assertTrue(userService.saveOrUpdate(userEntity1));
            assertEquals(userService.getUserEntityByPhone("+1"), userEntity1);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void paginationUsers() {
        try {
            assertTrue(userService.saveOrUpdate(userEntity1));
            assertTrue(userService.saveOrUpdate(userEntity2));
            assertEquals(1, userService.paginationUsers("1", 1).getUserEntityList().size());
            assertEquals("1", userService.paginationUsers("1", 2).getPage());
            assertTrue(2 == userService.paginationUsers("1", 2).getTotalUsersCount());

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveOrUpdate() {
        try {
            assertTrue(userService.saveOrUpdate(userEntity1));
            assertTrue(userService.saveOrUpdate(userEntity2));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void Update() {
        userEntity1.setCity("Moscow");
        UserEntity userEntityResult = null;
        try {
            userService.saveOrUpdate(userEntity1);
            userEntityResult = (UserEntity) userService.get(userEntity1.getId());
            assertNotNull(userEntityResult);
            assertEquals(userEntity1, userEntityResult);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            assertTrue(userService.saveOrUpdate(userEntity1));
            assertTrue(userService.saveOrUpdate(userEntity2));
            List users = userService.getAll();
            int size = users.size();
            assertTrue(userService.delete(userEntity1));
            assertNotSame(size, userService.getAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        try {
            userService.saveOrUpdate(userEntity2);
            assertEquals(userService.get(userEntity2.getId()), userEntity2);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void load() {
        try {
            userService.saveOrUpdate(userEntity2);
            assertEquals(userService.load(userEntity2.getId()).getId(), userEntity2.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
