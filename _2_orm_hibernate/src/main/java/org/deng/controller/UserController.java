package org.deng.controller;

import org.deng.model.User;
import org.deng.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUser userService;

    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.getAll();
    }
}
