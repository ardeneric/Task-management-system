package com.banque.banquemisr.service;


import com.banque.banquemisr.entity.User;

import java.util.List;

public interface UserService {

    User getUserByUsername(String username);
    List<User> findAll();
}
