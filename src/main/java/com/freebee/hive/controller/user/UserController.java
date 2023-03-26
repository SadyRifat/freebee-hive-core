package com.freebee.hive.controller.user;

import com.freebee.hive.dto.response.BaseResponse;
import com.freebee.hive.dto.user.*;
import com.freebee.hive.exception.BadRequestException;
import com.freebee.hive.model.BaseUser;
import com.freebee.hive.security.CurrentUser;
import com.freebee.hive.service.user.ApplicationTokenService;
import com.freebee.hive.service.user.MemberService;
import com.freebee.hive.service.user.UserCredentialService;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final MemberService memberService;
    private final UserCredentialService userCredentialService;
    private final ApplicationTokenService applicationTokenService;

    @PostMapping("/registration")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody RegistrationRequest registrationRequest, @CurrentUser BaseUser baseUser) throws MessagingException, IOException, TemplateException {
        if (!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())) {
            throw new BadRequestException("User Password Mismatched", 4006);
        }
        UserProfileResponse userProfileResponse = memberService.addNewUser(registrationRequest, baseUser);
        log.info("New Registration Success");
        return new ResponseEntity<>(new BaseResponse(userProfileResponse, HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AccessRequest accessRequest) {
        AccessToken accessToken = applicationTokenService.requestForAccessToken(accessRequest);
        return new ResponseEntity<>(new BaseResponse(accessToken, HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) throws MessagingException, TemplateException, IOException {
        userCredentialService.userForgotPassword(forgotPasswordRequest);
        return new ResponseEntity<>(new BaseResponse("Success", HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }

    @GetMapping("/confirm-identity/{userEmail}/{otp}")
    public ResponseEntity<?> confirmIdentity(@PathVariable(value = "userEmail") String userEmail, @PathVariable(value = "otp") String validationOtp) {
        String token = userCredentialService.confirmIdentity(userEmail, validationOtp);
        return new ResponseEntity<>(new BaseResponse(token, HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }

    @GetMapping("/resend-opt/{userEmail}")
    public ResponseEntity<?> optResend(@PathVariable(value = "userEmail") String userEmail) throws MessagingException, TemplateException, IOException {
        userCredentialService.resendOTP(userEmail);
        return new ResponseEntity<>(new BaseResponse("Success", HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }

    @PostMapping("/password-setup")
    public ResponseEntity<?> passwordSetup(@Valid @RequestBody PasswordSetupRequest passwordSetupRequest, @CurrentUser BaseUser baseUser) {
        if (!passwordSetupRequest.getPassword().equals(passwordSetupRequest.getConfirmPassword())) {
            throw new BadRequestException("User Password Mismatched", 4006);
        }
        userCredentialService.verityForgotPasswordUserIdentity(passwordSetupRequest, baseUser);
        return new ResponseEntity<>(new BaseResponse("Success", HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUserProfile(@CurrentUser BaseUser baseUser) {
        UserProfileResponse userProfileResponse = memberService.makeUserProfileResponse(baseUser);
        return new ResponseEntity<>(new BaseResponse(userProfileResponse, HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }
}
