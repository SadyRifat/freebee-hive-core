package com.freebee.hive.controller.institute;

import com.freebee.hive.dto.institute.*;
import com.freebee.hive.dto.response.BaseResponse;
import com.freebee.hive.model.BaseUser;
import com.freebee.hive.security.CurrentUser;
import com.freebee.hive.service.institute.InstitutionPaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/institute-payment")
public class InstitutionPaymentController {
    private final InstitutionPaymentService institutionPaymentService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> crateInstitute(@Valid @RequestBody InstitutePaymentReq institutePaymentReq, @CurrentUser BaseUser baseUser) {
        InstitutePaymentResponse institutionPayment = institutionPaymentService.createInstitutionPayment(institutePaymentReq, baseUser);
        return new ResponseEntity<>(new BaseResponse(institutionPayment, HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateInstitute(@Valid @RequestBody InstitutePaymentUpdateReq institutePaymentUpdateReq, @CurrentUser BaseUser baseUser) {
        InstitutePaymentResponse institutionPayment = institutionPaymentService.updateInsPaymentInfo(institutePaymentUpdateReq, baseUser);
        return new ResponseEntity<>(new BaseResponse(institutionPayment, HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }
}
