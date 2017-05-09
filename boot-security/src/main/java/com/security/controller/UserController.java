package com.security.controller;

import com.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Author : MrZ
 *
 * @Description Created in 17:05 on 2017/5/9.
 * Modified By :
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserRepository userRepository;


    @PostMapping(value = "/delete/{id}")
    @PreAuthorize(value = "hasRole('admin')")
    public Object delete(@PathVariable String id) {
        Optional.of(id);
        userRepository.delete(id);
        return ResponseEntity.ok();
    }
}
