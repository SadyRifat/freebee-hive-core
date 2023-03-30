package com.freebee.hive.dto.institute;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class InstituteBase {
    @JsonProperty("instituteInitials")
    @NotBlank(message = "Institute Initials Name is required.")
    @Size(max = 10, message = "Field length should not greater than 10")
    private String instituteInitials;

    @JsonProperty("instituteName")
    @NotBlank(message = "Institute Name is required.")
    @Size(max = 200, message = "Field length should not greater than 200")
    private String instituteName;

    @JsonProperty("instituteReg")
    @NotBlank(message = "Institute Reg No. is required.")
    @Size(max = 20, message = "Field length should not greater than 20")
    private String instituteReg;

    @JsonProperty("zipCode")
    @NotBlank(message = "Zip Code is required.")
    @Size(max = 10, message = "Field length should not greater than 10")
    private String zipCode;

    @JsonProperty("assignedKAM")
    @NotBlank(message = "Assigned KAM is required.")
    @Size(max = 10, message = "Field length should not greater than 10")
    private String assignedKam;

    @JsonProperty("locationID")
    @NotBlank(message = "Location ID is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    private String locationID;
}
