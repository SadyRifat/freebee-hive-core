package com.freebee.hive.service.user;

import com.freebee.hive.dto.user.AccessRequest;
import com.freebee.hive.entity.user.User;

public interface AccessProvider {
    User processRequest(AccessRequest accessRequest);
}
