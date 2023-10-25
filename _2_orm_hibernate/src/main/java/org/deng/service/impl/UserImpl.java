package org.deng.service.impl;

import org.deng.model.User;
import org.deng.repository.UserRepository;
import org.deng.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements IUser {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getAll() {
        List<User> userList = repository.findAll();
        return userList;
    }
}
