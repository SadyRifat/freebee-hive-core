package com.freebee.hive.repository.institute;

import com.freebee.hive.entity.institute.InstitutionPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionPaymentRepository extends JpaRepository<InstitutionPayment, String> {
}
