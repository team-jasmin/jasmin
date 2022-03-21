package io.github.jasmin.member;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(final String name, final String email, final String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static Member of(final String name, final String email, final String password) {
        return Member.builder()
            .name(name)
            .email(email)
            .password(password)
            .build();
    }

    public void updateMember(final MemberUpdateRequest request) {
        this.name = request.getName();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }
}
