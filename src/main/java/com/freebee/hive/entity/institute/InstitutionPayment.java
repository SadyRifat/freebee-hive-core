package com.freebee.hive.entity.institute;

import com.freebee.hive.entity.payment.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.ZonedDateTime;

import static com.freebee.hive.util.StaticTextConfig.DB_SCHEMA;

@Getter
@Setter
@Entity
@Table(name = "institute_payment_methods", schema = DB_SCHEMA)
public class InstitutionPayment {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "institute_initials")
    private String instituteInitials;

    @Column(name = "account_no")
    private String accountNO;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "institute_id", referencedColumnName = "id")
    private Institute institute;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_methods_id", referencedColumnName = "id")
    private PaymentMethod paymentMethod;

}
