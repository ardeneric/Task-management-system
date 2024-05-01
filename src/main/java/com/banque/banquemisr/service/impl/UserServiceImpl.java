package com.banque.banquemisr.service.impl;

import com.banque.banquemisr.entity.User;
import com.banque.banquemisr.repository.UserRepository;
import com.banque.banquemisr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}