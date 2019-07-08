package com.crud.library.controller;

import com.crud.library.domain.UserDto;
import com.crud.library.mapper.UserMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/library")
public class UserController {
    @Autowired
    private DbService service;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST, value = "addUser", consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
        service.addUser(userMapper.mapToUser(userDto));

    }
}
