package com.security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Author : MrZ
 *
 * @Description Created in 17:05 on 2017/5/9.
 * Modified By :
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {
    @Autowired
    UserRepository userRepository;


    @PostMapping(value = "/delete/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable String id) throws JsonProcessingException {
        userRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
