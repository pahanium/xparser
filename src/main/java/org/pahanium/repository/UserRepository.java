package org.pahanium.repository;

import org.pahanium.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u where u.login = :login")
    User findByLogin(@Param("login") String login);
}
