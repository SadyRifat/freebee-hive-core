package com.freebee.hive.service.user.impl;

import com.freebee.hive.dto.user.AccessRequest;
import com.freebee.hive.entity.user.User;
import com.freebee.hive.exception.BadRequestException;
import com.freebee.hive.repository.user.UserCredentialRepository;
import com.freebee.hive.service.JWTService;
import com.freebee.hive.service.user.AccessProvider;
import com.freebee.hive.validate.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RefreshAccessImpl implements AccessProvider {
    private final JWTService jwtService;
    private final UserCredentialRepository userCredentialRepository;

    @Override
    public User processRequest(AccessRequest accessRequest) {
        boolean valid = jwtService.validateJwtToken(accessRequest.getRefreshToken());
        if (valid) {
            String subject = jwtService.getSubjectDataFromJwtToken(accessRequest.getRefreshToken());
            User user = userCredentialRepository.getById(subject);
            UserValidator userValidator = new UserValidator();
            if (userValidator.activityValidate(user)) {
                return user;
            } else {
                throw new BadRequestException("Unable to validate user", 4100);
            }
        } else {
            throw new BadRequestException("Invalid refresh token", 4114);
        }
    }
}
