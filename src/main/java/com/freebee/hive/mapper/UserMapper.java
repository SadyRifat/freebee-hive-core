package com.freebee.hive.mapper;

import com.freebee.hive.dto.user.UserProfileResponse;
import com.freebee.hive.entity.user.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserProfileResponse subscriberToUserProfileResponse(Member member);
}
