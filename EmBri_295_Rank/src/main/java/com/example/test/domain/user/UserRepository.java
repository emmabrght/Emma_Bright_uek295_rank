package com.example.test.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.DoubleStream;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String user);
}
