package org.pahanium;

import org.pahanium.entity.User;
import org.pahanium.entity.enums.UserRole;
import org.pahanium.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final UserService userService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                userService.addUser(new User("admin", "d033e22ae348aeb5660fc2140aec35850c4da997", UserRole.ADMIN));
                userService.addUser(new User("demo", "89e495e7941cf9e40e6980d14a16bf023ccd4c91", UserRole.USER));
            }
        };
    }
}
