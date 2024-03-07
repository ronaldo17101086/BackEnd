package com.user.users.services;

import com.user.users.entity.user;
import com.user.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServices {
    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void insertUser(String username, String email, String password) {
        userRepository.insertUser(username, email, password);
    }

    public List<user> findSelectedColumns() {
        return userRepository.findSelectedColumns();
    }

    public List<Object[]> threecolumns() {
        return userRepository.threecolumns();
    }

    public List<user> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
