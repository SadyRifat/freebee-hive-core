package com.freebee.hive.dto.institute;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class InstituteUpdateReq extends InstituteCreateReq {
    @JsonProperty("instituteID")
    @NotBlank(message = "Institute ID is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    private String institutionID;
}
