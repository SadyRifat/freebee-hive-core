package com.freebee.hive.service.user;

import com.freebee.hive.entity.user.Role;
import com.freebee.hive.enums.ERole;
import com.freebee.hive.repository.user.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRoleByName(ERole role) {
        return roleRepository.getRoleByName(role);
    }
}
