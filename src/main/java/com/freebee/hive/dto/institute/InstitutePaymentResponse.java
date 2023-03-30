package com.freebee.hive.dto.institute;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class InstitutePaymentResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("instituteInitials")
    private String instituteInitials;

    @JsonProperty("accountNO")
    private String accountNO;

    @JsonProperty("payment_method_id")
    private String paymentMethodID;

    @JsonProperty("institution_id")
    private String institutionID;
}
