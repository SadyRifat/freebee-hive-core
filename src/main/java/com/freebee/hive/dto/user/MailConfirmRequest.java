package com.freebee.hive.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MailConfirmRequest {
    @JsonProperty("verificationCode")
    @NotBlank(message = "Verification code is required.")
    @Size(max = 10, message = "Verification code length should not greater than 10")
    private String verificationCode;

    @JsonProperty("userEmail")
    @NotBlank(message = "User email is required.")
    @Size(max = 50, message = "Verification code length should not greater than 50")
    private String userEmail;
}
