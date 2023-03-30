package com.freebee.hive.dto.institute;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class InstitutePaymentUpdateReq {
    @JsonProperty("id")
    @NotBlank(message = "Id is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    private String id;

    @JsonProperty("instituteInitials")
    @NotBlank(message = "Institute Reg No. is required.")
    @Size(max = 10, message = "Field length should not greater than 10")
    private String instituteInitials;

    @JsonProperty("accountNO")
    @NotBlank(message = "Institute Reg No. is required.")
    @Size(max = 15, message = "Field length should not greater than 15")
    private String accountNO;

    @JsonProperty("payment_method_id")
    @NotBlank(message = "Payment Method ID is required.")
    @Size(max = 15, message = "Field length should not greater than 15")
    private String paymentMethodID;

    @JsonProperty("institution_id")
    @NotBlank(message = "Institution ID is required.")
    @Size(max = 50, message = "Field length should not greater than 50")
    private String institutionID;
}
