package io.github.jasmin.jtpark.common.util;

import io.github.jasmin.jtpark.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Component
@RequiredArgsConstructor
public class JdbcUtils {

    private final DataSource dataSource;
    private final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    /**
     * 조건절 생성
     */
    public String createConditionalClause(final Object entity, final String... conditionColumnNames) {
        if (conditionColumnNames == null || conditionColumnNames.length == 0) {
            return "";
        }

        final StringBuilder sqlBuilder = new StringBuilder().append(" where 1=1 ");

        for (String column : conditionColumnNames) {
            try {
                Field field = entity.getClass().getDeclaredField(column);
                field.setAccessible(true);

                String value = field.get(entity).toString();
                sqlBuilder.append(String.format("and %s = '%s' ", column, value));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error(e.getMessage());
            }
        }

        return sqlBuilder.toString();
    }

    /**
     * resultSet의 데이터를 객체에 매핑
     */
    public <T> T mapObjectOrNull(final ResultSet resultSet, final Class<T> clazz) throws SQLException {
        final Field[] fields = Member.class.getDeclaredFields();
        final T object;
        try {
            object = clazz.getConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            log.error(e.getMessage());
            return null;
        }


        for (Field field : fields) {
            try {
                String type = field.getType().getName();
                Method getter = ResultSet.class.getMethod("get" + type);
                //noinspection JavaReflectionInvocation
                Object value = getter.invoke(resultSet, field.getName());

                if (value != null) {
                    field.setAccessible(true);
                    field.set(object, value);
                }
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException ignored) {
            }
        }

        return object;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = connectionThreadLocal.get();
        if(connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
            connectionThreadLocal.set(connection);
        }

        return connection;
    }

    public void closeResources(Statement statement, ResultSet resultSet) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }

    public void closeConnection() {
        Connection connection = connectionThreadLocal.get();

        try {
            if( connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
