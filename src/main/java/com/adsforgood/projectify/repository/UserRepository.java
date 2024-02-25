package com.adsforgood.projectify.repository;

import com.adsforgood.projectify.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Since email is unique, we'll find users by email
    Optional<User> findByEmail(String email);
}
