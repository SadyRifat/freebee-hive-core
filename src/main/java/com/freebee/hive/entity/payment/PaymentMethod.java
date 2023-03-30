package com.freebee.hive.entity.payment;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

import static com.freebee.hive.util.StaticTextConfig.DB_SCHEMA;

@Getter
@Setter
@Entity
@Table(name = "payment_methods", schema = DB_SCHEMA)
public class PaymentMethod {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "payment_methods")
    private String paymentMethod;

    @Column(name = "gateway_charge_percentage")
    private String gatewayChargePercentage;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;
}
