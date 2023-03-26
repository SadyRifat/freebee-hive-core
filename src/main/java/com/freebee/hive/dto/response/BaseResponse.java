package com.freebee.hive.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    @JsonProperty("data")
    Object object;

    @JsonProperty("code")
    int code;

    @JsonProperty("error")
    ErrorResponse errorResponse;
}
