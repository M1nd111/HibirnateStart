package hibernate.starter;


import hibernate.starter.converter.BirthdayConverter;
import hibernate.starter.entity.*;
import hibernate.starter.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;


@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        /*Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        Company company = Company.builder()
                .name("Mail.ru")
                .build();
        // transient
        User user = User.builder()
                .username("Dan@999mail.ru")
                .personalInfo(PersonalInfo.builder()
                    .firstname("Dan")
                    .lastname("Web")
                    .birthDate(new Birthday(LocalDate.of(1999, 10, 11))).build())
                .role(Role.ADMIN)
                .company(company)
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            System.out.println("OK");

            session.getTransaction().commit();
        }*/
    }
}



/*log.info("User object in transient state: {} ", user); // {} это как %s
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            // persistent к session
            session.beginTransaction();

            user.getPersonalInfo().setFirstname("gg");
            log.warn("User firstName was changed: {} ", user);
            session.saveOrUpdate(user);
//            session.isDirty(); // проверяет есть ли у нас в кэшэ данные которые были зменены но не отправились в бд
            session.refresh(user); // перезаписывает кэш user-ом
            log.debug("User: {}, session: {}", user, session);

            session.getTransaction().commit();
        }
        catch (Exception e){
            log.error("Exception occurred: ", e);
            throw e;
        }*/


/*try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
var session2 = sessionFactory.openSession()) {
        session2.beginTransaction();

            session2.saveOrUpdate(user);

            session2.getTransaction().commit();
        }*/


//            session.save(user);
//            session.update(user);
//            session.saveOrUpdate(user);
//            session.delete(user);
//            User user1 = session.get(User.class, "Vlad@999mail.ru");
// после гет(select) мы записали в persistenceContext user1
//            user1.setFirstname("gg"); // меняем имя
//            session.flush(); // запускает update для кэша без явного вызово - изменения отобразятся в бд.
//            session.clear(); // очищает persistenceContext также close, evict
//            session.evict(user1); // конкретно удалит user1 из кэша