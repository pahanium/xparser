package org.pahanium.entity;

import org.pahanium.entity.enums.UserRole;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User() {
    }

    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
