package com.freebee.hive.service.user;

import com.freebee.hive.dto.user.RegistrationRequest;
import com.freebee.hive.dto.user.UserProfileResponse;
import com.freebee.hive.entity.user.Member;
import com.freebee.hive.entity.user.User;
import com.freebee.hive.exception.BadRequestException;
import com.freebee.hive.exception.ResourceNotFoundException;
import com.freebee.hive.mapper.UserMapper;
import com.freebee.hive.model.BaseUser;
import com.freebee.hive.repository.user.MemberRepository;
import com.freebee.hive.service.mail.MailService;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.ZonedDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService {
    private final UserMapper userMapper;
    private final MailService mailService;
    private final MemberRepository memberRepository;
    private final UserCredentialService userCredentialService;

    public UserProfileResponse addNewUser(RegistrationRequest registrationRequest, BaseUser baseUser) throws MessagingException, TemplateException, IOException {
        if (userCredentialService.findByUserEmail(registrationRequest.getEmail()) == null) {
            return saveNewUser(registrationRequest, baseUser.getUsername());
        } else {
            log.error("User Already exists: {}", registrationRequest.getEmail());
            throw new BadRequestException("User Already exists.", 4005);
        }
    }

    public UserProfileResponse saveNewUser(RegistrationRequest registrationRequest, String addedBy) {
        User user = userCredentialService.addNewUserCredential(registrationRequest);

        Member member = new Member();
        member.setUserID(user.getUserID());
        member.setFirstName(registrationRequest.getFirstName());
        member.setLastName(registrationRequest.getLastName());
        member.setOfficeIDNo(registrationRequest.getOfficeIDNo());
        member.setDepartment(registrationRequest.getDepartment());
        member.setEmail(registrationRequest.getEmail());
        member.setAddedBy(addedBy);
        member.setCreatedOn(ZonedDateTime.now());
        member.setUpdatedOn(ZonedDateTime.now());
        memberRepository.save(member);
        log.info("New registration UserProfile saved with member ID: {}", member.getUserID());

        return userMapper.subscriberToUserProfileResponse(member);
    }

    public UserProfileResponse makeUserProfileResponse(BaseUser baseUser) {
        Member userProfileByID = findMemberByID(baseUser.getUsername());
        return userMapper.subscriberToUserProfileResponse(userProfileByID);
    }

    public Member findMemberByID(String userID) {
        return memberRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("UserProfile", "userID", userID));
    }

    public Member getMemberByID(String userID) {
        return memberRepository.findById(userID).orElse(null);
    }


}
