/*
package hibernate.starter;

import hibernate.starter.entity.User;
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
    public void testHibernateApi(){
        var user = User.builder()
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
        }

    }
}*/
