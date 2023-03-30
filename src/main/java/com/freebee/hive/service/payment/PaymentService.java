package com.freebee.hive.service.payment;

import com.freebee.hive.entity.payment.PaymentMethod;
import com.freebee.hive.exception.ResourceNotFoundException;
import com.freebee.hive.repository.payment.PaymentMethodRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod findPaymentByID(String methodID) {
        return paymentMethodRepository.findById(methodID).orElseThrow(() -> new ResourceNotFoundException("Payment Method", "methodID", methodID));
    }
}
