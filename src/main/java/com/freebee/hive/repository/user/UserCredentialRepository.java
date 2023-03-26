package com.freebee.hive.repository.user;

import com.freebee.hive.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);

    @Query("SELECT user FROM User user WHERE user.email=:email")
    Optional<User> findByUserEmail(@Param("email") String email);

    @Query(value = "SELECT name FROM hive_core.users WHERE email = :email", nativeQuery = true)
    String getNameFromEmail(@Param("email") String email);
}
