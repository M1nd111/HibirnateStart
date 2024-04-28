package hibernate.starter.dao;

import hibernate.starter.entity.PersonalInfo;
import hibernate.starter.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<User>{
    private static final UserDao INSTANCE = new UserDao();
    public static UserDao getInstance(){
        return INSTANCE;
    }

    @Override
    public List<User> findAll(Session session) {

        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user);

        return session.createQuery(criteria).list();
    }

    @Override
    public List<User> findAllByFirstName(Session session, String firstName) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

//        criteria.select(user).where(cb.equal(user.get(User_.personalInfo).get(PersonalInfo_.FIRSTNAME), firstName));

        return session.createQuery(criteria).list();
    }


}
