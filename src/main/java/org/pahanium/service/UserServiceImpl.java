package org.pahanium.service;

import org.pahanium.entity.User;
import org.pahanium.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }
}
