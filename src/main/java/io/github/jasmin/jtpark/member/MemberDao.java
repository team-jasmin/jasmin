package io.github.jasmin.jtpark.member;

import io.github.jasmin.jtpark.common.util.JdbcUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
///////////////////////////////////// DAO 전체 트랜잭션, 커넥션 리소스반납 관련코드 서비스로 빼야함
@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberDao {

    private final JdbcUtils jdbcUtils;

    public Long insert(final Member member) {
        final String sql = "insert into member(name, password) values(?, ?)";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            Connection connection = jdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, member.getName());
            preparedStatement.setString(2, member.getPassword());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (!resultSet.next()) {
                throw new SQLException("no search generated key");
            }
            return resultSet.getLong(1);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            jdbcUtils.closeResources(preparedStatement, resultSet);
            jdbcUtils.closeConnection();
        }
    }

    public List<Member> findAllOrNull() {
        List<Member> members = new ArrayList<>();

        try (ResultSet resultSet = selectBy(null)) {
            while (resultSet.next()) {
                Member member = jdbcUtils.mapObjectOrNull(resultSet, Member.class);
                if (member != null) {
                    members.add(member);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }

        return (members.size() == 0) ? null : members;
    }

    private Member selectByOneOrNull(final String key, final Object value) {
        final Member param = new Member();

        final Field[] fields = param.getClass().getDeclaredFields();
        for (final Field field : fields) {
            if(field.getName().equals(key)) {
                field.setAccessible(true);
                try {
                    field.set(key, value);
                } catch (IllegalAccessException e) {
                    log.error(e.getMessage());
                    return null;
                }
            }
        }

        try (ResultSet resultSet = selectBy(param,key)) {
            return jdbcUtils.mapObjectOrNull(resultSet, Member.class);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        }
    }


    private ResultSet selectBy(final Member memberOrNull, final String... columnNames) {
        String sql = "select id, name, password from member";

        // 조건 검색
        if (memberOrNull != null
                && columnNames.length > 0) {
            sql += jdbcUtils.createConditionalClause(memberOrNull, columnNames);
        }

        try (
                PreparedStatement prepareStatement = jdbcUtils.getConnection().prepareStatement(sql)
        ) {
            return prepareStatement.executeQuery();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
