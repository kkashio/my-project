package com.jevinci.fpm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by seongmin Park on 2017. 6. 29..
 */

@Entity
@Table(name = "USER_ROLE")
public class UserRole {

    @Embeddable // jpa 내에서 엔티티 내에 참조 클래스의 필드를 자신의 컬럼으로 만들고 싶다면
    public static class Id implements Serializable {
        private static final long serialVersionUID = 1322120000551624359L;

        @Column(name = "USER_ID")
        protected Long userId;

        @Enumerated(EnumType.STRING)
        @Column(name = "ROLE")
        protected Role role;

        public Id() {}

        public Id(Long userId, Role role) {
            this.userId = userId;
            this.role = role;
        }
    }

    @EmbeddedId
    Id id = new Id();

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", insertable=false, updatable=false)
    protected Role role;

    public Role getRole() {
        return role;
    }
}
