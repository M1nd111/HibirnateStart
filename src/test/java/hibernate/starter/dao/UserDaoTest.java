package hibernate.starter.dao;

import hibernate.starter.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    public void CriteriaTest(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();


        UserDao userDao = UserDao.getInstance();
        System.out.println(STR."\{userDao.findAll(session).toString()}\n");
        System.out.println(userDao.findAllByFirstName(session,"first"));

        session.getTransaction().commit();
    }

}