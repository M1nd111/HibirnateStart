package hibernate.starter;

import hibernate.starter.entity.*;
import hibernate.starter.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {

    @Test
    public void checkOneToOneAutoId(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();
        Company company = session.get(Company.class, 1);

        User user = User.builder()
                .username("Ban@1mail.ru")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Dan8888")
                        .lastname("Web")
                        .birthDate(new Birthday(LocalDate.of(1999, 10, 11))).build())
                .company(company)
                .role(Role.ADMIN)
                .build();
        ProfileAutoId profile = ProfileAutoId.builder()
                .language("RU")
                .street("ofmsc")
                .build();
        session.save(user);
        profile.setUser(user);
        session.save(profile);

        session.getTransaction().commit();
    }

    @Test
    public void checkOneToOne(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();
        Company company = session.get(Company.class, 1);

        User user = User.builder()
                .username("Ban@1mail.ru")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Dan8888")
                        .lastname("Web")
                        .birthDate(new Birthday(LocalDate.of(1999, 10, 11))).build())
                .company(company)
                .role(Role.ADMIN)
                .build();
        Profile profile = Profile.builder()
                .language("RU")
                .street("ofmsc")
                .build();
        session.save(user);
        profile.setUser(user);
        session.save(profile);

        session.getTransaction().commit();
    }

    @Test
    public void checkOrphalRemoval(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        Company company = session.get(Company.class, 3);
        company.getUsers().clear();

        session.getTransaction().commit();
    }

    @Test
    public void addNewUserAndCompany(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        Company company = Company.builder()
                .name("Google2")
                .build();
        User user = User.builder()
                .username("Ban@999mail.ru")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Dan")
                        .lastname("Web")
                        .birthDate(new Birthday(LocalDate.of(1999, 10, 11))).build())
                .role(Role.ADMIN)
                .build();

        company.addUser(user);

        session.saveOrUpdate(company);

        session.getTransaction().commit();
    }

    @Test
    public void checkOneToMany(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        var company = session.get(Company.class, 4);
        System.out.println(company.getUsers());

        session.getTransaction().commit();
    }

    @Test
    public void testHibernateApi(){
        /*var user = User.builder()
                .username("Vlad@111mail.ru")
                .firstname("Vlad")
                .lastname("Admin")
                .birthDate(LocalDate.of(2000, 12, 16))
                .age(19).build();
        var sql = """
                insert into
                %s
                (%s)
                values
                (%s)
                """;
        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> STR."\{table.schema()}.\{table.name()}")
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();

        var columnNames = Arrays.stream(fields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName())).collect(Collectors.joining(", "));

        var columnValues = Arrays.stream(fields)
                .map(field -> "?").collect(Collectors.joining(", "));

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/public",
                                                                "root", "Root89");
            var statement = connection
                    .prepareStatement(sql.formatted(tableName, columnNames, columnValues))) {

                for(int i = 0; i < fields.length; i++){
                    fields[i].setAccessible(true);
                    statement.setObject(i+1,  fields[i].get(user));
                }
                System.out.println(statement.toString());
                statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }*/
    }
}
