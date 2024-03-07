package com.user.users.component;

import com.user.users.entity.user;
import com.user.users.repository.UserRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserResolver implements GraphQLQueryResolver {
    @Autowired
    private UserRepository userRepository;
    public List<user> usersByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public List<user> usersByUsername(String userName) {
        return userRepository.usersByUsername(userName);
    }
    public List<user> usersByEmailAndUsername(String email, String userName) {
        return userRepository.usersByEmailAndUsername(email,userName);
    }

}
