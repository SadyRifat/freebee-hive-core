package com.freebee.hive.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationRequest {
    @JsonProperty("firstName")
    @NotBlank(message = "First Name is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    private String firstName;

    @JsonProperty("lastName")
    @NotBlank(message = "Last Name is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    private String lastName;

    @JsonProperty("officeIDNo")
    @NotBlank(message = "Office ID is required.")
    @Size(max = 5, message = "Field length should not greater than 5")
    private String officeIDNo;

    @JsonProperty("department")
    @NotBlank(message = "Department is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    private String department;

    @JsonProperty("email")
    @NotBlank(message = "Email is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$", message = "Email format is not valid")
    private String email;

    @JsonProperty("password")
    @NotBlank(message = "Password is required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=?!])(?=\\S+$).{7,}$",
            message = "Choose a password of at least seven characters, one special character, one uppercase letter and one number")
    @Size(max = 20, message = "Field length should not greater than 20")
    private String password;

    @JsonProperty("confirmPassword")
    @NotBlank(message = "Confirm Password is required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=?!])(?=\\S+$).{7,}$",
            message = "Choose a password of at least seven characters, one special character, one uppercase letter and one number")
    @Size(max = 20, message = "Field length should not greater than 20")
    private String confirmPassword;
}
