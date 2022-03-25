package io.github.jasmin.jtpark.common.util;

import io.github.jasmin.jtpark.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JdbcUtilsTest {
    
    @BeforeEach
    //여기해야댐 DataSource 추가됨

    @Test
    void createConditionalClause() {
        final Long id = 1L;
        final String name = "Test";
        final String password = "123";

        final String base = " where 1=1 ";
        final String idConditionSql = String.format("%sand id = '%s' ", base, id);
        final String nameConditionSql = String.format("%sand name = '%s' ", base, name);
        final String passwordConditionSql = String.format("%sand password = '%s' ", base, password);
        final String allConditionSql = String.format("%sand id = '%s' and name = '%s' and password = '%s' ", base, id, name, password);

        Member member = new Member(id, name, password);

        Assertions.assertThat(JdbcUtils.createConditionalClause(member, "id")).isEqualTo(idConditionSql);
        Assertions.assertThat(JdbcUtils.createConditionalClause(member, "name")).isEqualTo(nameConditionSql);
        Assertions.assertThat(JdbcUtils.createConditionalClause(member, "password")).isEqualTo(passwordConditionSql);
        Assertions.assertThat(JdbcUtils.createConditionalClause(member, "id", "name", "password")).isEqualTo(allConditionSql);
    }
}