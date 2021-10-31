package com.spring.user.dao;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/com/spring/applicationContext.xml")
public class UserDaoTest {
    @Autowired
    private ApplicationContext context;

    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
//        ApplicationContext context = new GenericXmlApplicationContext("com/spring/applicationContext.xml");
        this.dao = context.getBean("userDao", UserDao.class);

        user1 = new User("somi", "다솜", "dasom");
        user2 = new User("click", "민우", "minwoo");
        user3 = new User("pine", "파인애플", "pineapple");
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userGet1 = dao.get(user1.getId());
        assertThat(userGet1.getId(), is(user1.getId()));
        assertThat(userGet1.getPassword(), is(user1.getPassword()));

        User userGet2 = dao.get(user2.getId());
        assertThat(userGet2.getId(), is(user2.getId()));
        assertThat(userGet2.getPassword(), is(user2.getPassword()));
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown id");
    }

    @Test
    public void getAll() {
        dao.deleteAll();

        dao.add(user1);
        List<User> users1 = dao.getAll();
        assertThat(users1.size(), is(1));

        dao.add(user2);
        List<User> users2 = dao.getAll();
        assertThat(users2.size(), is(2));

        dao.add(user3);
        List<User> users3 = dao.getAll();
        assertThat(users3.size(), is(3));

        checkSameUser(user2, users3.get(0));
        checkSameUser(user3, users3.get(1));
        checkSameUser(user1, users3.get(2));
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId(),is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
////        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//
//        ApplicationContext context = new GenericXmlApplicationContext("com/spring/applicationContext.xml");
//        UserDao dao = context.getBean("userDao", UserDao.class);
//
//        User user = new User();
//        user.setId("whiteship");
//        user.setName("백기선");
//        user.setPassword("married");
//        dao.add(user);
//        System.out.println("등록성공");
//
//        User user2 = dao.get(user.getId());
//        System.out.println(user2.getName());
//        System.out.println(user2.getPassword());
//        System.out.println(user2.getId() + " 조회 성공");
//
//    }
}
