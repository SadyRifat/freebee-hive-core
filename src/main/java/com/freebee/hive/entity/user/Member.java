package com.freebee.hive.entity.user;

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
@Table(name = "freebee_members", schema = DB_SCHEMA)
public class Member {
    @Id
    @Column(name = "users_id")
    private String userID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "office_id_no")
    private String officeIDNo;

    @Column(name = "department")
    private String department;

    @Column(name = "email")
    private String email;

    @Column(name = "added_by")
    private String addedBy;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;

}
