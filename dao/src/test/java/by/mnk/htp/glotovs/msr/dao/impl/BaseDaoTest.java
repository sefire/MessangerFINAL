package by.mnk.htp.glotovs.msr.dao.impl;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.interfaces.IUserDao;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.junit.Assert.*;


@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager"/*, defaultRollback = true*/)
@Transactional
public class BaseDaoTest {

    @Autowired
    private IUserDao userDao;

    private UserEntity userEntity1;
    private UserEntity userEntity2;

    @Before
    public void initParam() {

        //String phone, String firstName, String lastName, Integer age, String country, String city, String password, String type, String access, String deletionStatus) {
        userEntity1 = new UserEntity(1, "+1", "Sergey", "Glotov", 25, "Belarus", "Minsk", "1", "user", "true", "false");
        userEntity2 = new UserEntity(2, "+2", "Sergey2", "Glotov2", 25, "Belarus", "Minsk", "1", "user", "true", "false");

    }

    @Test
    public void saveOrUpdate() {
        try {
            assertTrue(userDao.saveOrUpdate(userEntity1));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveOrUpdate2() {
        try {
            assertTrue(userDao.saveOrUpdate(userEntity2));
            assertNotNull(userDao.get(userEntity2.getId()));
            assertNull(userDao.get(10000000));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Update() {
        userEntity1.setCity("Moscow");
        UserEntity userEntityResult = null;
        try {
            userDao.saveOrUpdate(userEntity1);
            userEntityResult = (UserEntity) userDao.get(userEntity1.getId());
            assertNotNull(userEntityResult);
            assertEquals(userEntity1, userEntityResult);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        try {
            userDao.saveOrUpdate(userEntity2);
            assertEquals(userDao.get(userEntity2.getId()), userEntity2);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void load() {
        try {
            userDao.saveOrUpdate(userEntity2);
            assertEquals(userDao.load(userEntity2.getId()).getId(), userEntity2.getId());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @After
    public void delete() {
        List<UserEntity> list = null;
        try {
            list = userDao.getAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int size = list.size();
        if (list.size() > 0) {
            UserEntity persistent = list.get(0);
            try {
                userDao.delete(persistent);
            } catch (DaoException e) {
                e.printStackTrace();
            }
            try {
                assertNotSame(userDao.getAll().size(), size);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
    }
}
