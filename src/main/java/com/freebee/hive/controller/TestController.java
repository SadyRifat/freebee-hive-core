package com.freebee.hive.controller;

import com.freebee.hive.dto.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {

    @PreAuthorize("hasAnyRole('ROLE_ANONYMOUS')")
    @GetMapping("/auth")
    public ResponseEntity<?> userRegistration() {
        return new ResponseEntity<>(new BaseResponse("Hello", HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<?> testApi() {
        return new ResponseEntity<>(new BaseResponse("Hello", HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }
}
