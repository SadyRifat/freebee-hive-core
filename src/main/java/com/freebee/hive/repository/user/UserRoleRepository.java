package com.freebee.hive.repository.user;

import com.freebee.hive.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<User, String> {
}
