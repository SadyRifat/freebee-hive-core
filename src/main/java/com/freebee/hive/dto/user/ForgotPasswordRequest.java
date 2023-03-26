package com.freebee.hive.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ForgotPasswordRequest {
    @JsonProperty("email")
    @NotBlank(message = "Email is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$", message = "Email format is not valid")
    private String email;
}
