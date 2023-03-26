package com.freebee.hive.repository.user;

import com.freebee.hive.entity.user.Role;
import com.freebee.hive.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Query("select role from Role role where role.name=:name")
    Role getRoleByName(@Param("name") ERole name);
}
