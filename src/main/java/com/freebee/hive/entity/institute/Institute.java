package com.freebee.hive.entity.institute;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

import static com.freebee.hive.util.StaticTextConfig.DB_SCHEMA;

@Getter
@Setter
@Entity
@Table(name = "institutes", schema = DB_SCHEMA)
public class Institute {
    @Id
    @Column(name = "id")
    private String institutionID;

    @Column(name = "institute_initials")
    private String instituteInitials;

    @Column(name = "institute_name")
    private String instituteName;

    @Column(name = "institute_reg_no")
    private String instituteReg;

    @Column(name = "joined_on")
    private ZonedDateTime joinedOn;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "added_by")
    private String addedBy;

    @Column(name = "assigned_kam")
    private String assignedKam;

    @Column(name = "is_currently_active")
    private boolean isCurrentlyActive;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
}
