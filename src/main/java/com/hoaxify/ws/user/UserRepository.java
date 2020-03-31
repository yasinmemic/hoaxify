package com.hoaxify.ws.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Yasin Memic on Mar, 2020
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
