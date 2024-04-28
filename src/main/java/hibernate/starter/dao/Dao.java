package hibernate.starter.dao;

import org.hibernate.Session;

import java.util.List;

public interface Dao<T> {

    List<T> findAll(Session session);
    List<T> findAllByFirstName(Session session, String firstName);
}
