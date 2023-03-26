package com.freebee.hive.factory;

import com.freebee.hive.enums.EAuthProvider;
import com.freebee.hive.exception.BadRequestException;
import com.freebee.hive.service.user.AccessProvider;
import com.freebee.hive.service.user.impl.NativeAccessImpl;
import com.freebee.hive.service.user.impl.RefreshAccessImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class AccessProviderFactory {
    private final NativeAccessImpl nativeAccess;
    private final RefreshAccessImpl refreshAccess;

    public AccessProvider getAccessProvider(String grandType) {
        if (grandType.equalsIgnoreCase(EAuthProvider.password.name())) {
            return nativeAccess;
        } else if (grandType.equalsIgnoreCase(EAuthProvider.refresh.name())) {
            return refreshAccess;
        } else {
            throw new BadRequestException("Unknown Grant Type", 4113);
        }
    }
}
