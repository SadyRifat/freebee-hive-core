package com.freebee.hive.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;
}
