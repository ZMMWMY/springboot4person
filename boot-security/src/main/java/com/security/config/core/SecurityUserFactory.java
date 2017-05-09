package com.security.config.core;

import com.security.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ZMM on 2017/5/9.
 */
public class SecurityUserFactory {

    private SecurityUserFactory() {

    }

    public static SecurityUser createUser(User user) {
        return new SecurityUser(user.getId(), user.getUsername(), user.getPassword(),
                listToCollection(user.getRole()), null, user.isLocked(), user.isEnable()
        );
    }

    private static Collection listToCollection(List<String> role) {
        return role.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
