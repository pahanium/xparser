package org.pahanium.service;

import org.pahanium.entity.User;

public interface UserService {
    User getUserByLogin(String login);

    void addUser(User User);
}
