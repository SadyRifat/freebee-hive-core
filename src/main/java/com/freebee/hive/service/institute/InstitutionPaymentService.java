package com.freebee.hive.service.institute;

import com.freebee.hive.dto.institute.InstituteCreateReq;
import com.freebee.hive.dto.institute.InstitutePaymentReq;
import com.freebee.hive.dto.institute.InstitutePaymentResponse;
import com.freebee.hive.dto.institute.InstitutePaymentUpdateReq;
import com.freebee.hive.entity.institute.Institute;
import com.freebee.hive.entity.institute.InstitutionPayment;
import com.freebee.hive.entity.payment.PaymentMethod;
import com.freebee.hive.exception.ResourceNotFoundException;
import com.freebee.hive.mapper.InstituteMapper;
import com.freebee.hive.model.BaseUser;
import com.freebee.hive.repository.institute.InstituteRepository;
import com.freebee.hive.repository.institute.InstitutionPaymentRepository;
import com.freebee.hive.service.payment.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class InstitutionPaymentService {
    private final InstituteMapper instituteMapper;
    private final PaymentService paymentService;
    private final InstituteService instituteService;
    private final InstitutionPaymentRepository institutionPaymentRepository;

    public InstitutePaymentResponse createInstitutionPayment(InstitutePaymentReq institutePaymentReq, BaseUser baseUser) {
        PaymentMethod paymentMethod = paymentService.findPaymentByID(institutePaymentReq.getPaymentMethodID());
        Institute institute = instituteService.findInstituteByID(institutePaymentReq.getInstitutionID());
        InstitutionPayment institutionPayment = instituteMapper.createReqToInstitutePayment(institutePaymentReq);
        institutionPayment.setId(UUID.randomUUID().toString());
        institutionPayment.setCreatedOn(ZonedDateTime.now());
        institutionPayment.setUpdatedOn(ZonedDateTime.now());
        institutionPayment.setUpdatedBy(baseUser.getUsername());

        institutionPayment.setPaymentMethod(paymentMethod);
        institutionPayment.setInstitute(institute);

        institutionPaymentRepository.save(institutionPayment);
        return instituteMapper.insPaymentRequestToResponse(institutionPayment);
    }

    public InstitutePaymentResponse updateInsPaymentInfo(InstitutePaymentUpdateReq institutePaymentUpdateReq, BaseUser baseUser) {
        InstitutionPayment institutionPayment = findInstitutionPaymentByID(institutePaymentUpdateReq.getInstitutionID());
        institutionPayment.setInstituteInitials(institutePaymentUpdateReq.getInstituteInitials());
        institutionPayment.setAccountNO(institutePaymentUpdateReq.getAccountNO());
        institutionPayment.setUpdatedOn(ZonedDateTime.now());
        institutionPayment.setUpdatedBy(baseUser.getUsername());
        institutionPaymentRepository.save(institutionPayment);
        return instituteMapper.insPaymentRequestToResponse(institutionPayment);
    }

    public InstitutionPayment findInstitutionPaymentByID(String institutionPaymentID) {
        return institutionPaymentRepository.findById(institutionPaymentID).orElseThrow(() -> new ResourceNotFoundException("Institute Payment", "institutionPaymentID", institutionPaymentID));
    }
}
