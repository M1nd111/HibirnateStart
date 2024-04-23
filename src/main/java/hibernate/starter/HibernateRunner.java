package hibernate.starter;


import hibernate.starter.converter.BirthdayConverter;
import hibernate.starter.entity.Birthday;
import hibernate.starter.entity.Role;
import hibernate.starter.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession();) {
            session.beginTransaction();
            User user = User.builder()
                    .username("Vlad@999mail.ru")
                    .firstname("Vlad")
                    .lastname("Admin")
                    .birthDate(new Birthday(LocalDate.of(2000, 12, 16)))
                    .role(Role.ADMIN)
                    .build();

//            session.save(user);
//            session.update(user);
//            session.saveOrUpdate(user);
//            session.delete(user);

            User user1 = session.get(User.class, "Vlad@999mail.ru");
            System.out.println(user1);
            session.getTransaction().commit();
        }
    }
}
