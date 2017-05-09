package com.security.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by Z先生 on 2016/11/19.
 */
@Data
@NoArgsConstructor
@ToString
public class User {

    public enum ROLE {
        admin, user, guest;

        private Integer role;

        ROLE(Integer role) {
            this.role = role;
        }

        ROLE() {
        }
    }

    @Id
    private String id;

    private List<String> role;

    private String username;

    private String password;

    private boolean locked;

    private boolean enable;

}
