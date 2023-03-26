package com.freebee.hive.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileResponse {
    @JsonProperty("userID")
    private String userID;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("officeIDNo")
    private String officeIDNo;

    @JsonProperty("email")
    private String email;
}
