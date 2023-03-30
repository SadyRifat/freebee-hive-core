package com.freebee.hive.entity.institute;

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
@Table(name = "location", schema = DB_SCHEMA)
public class Location {
    @Id
    @Column(name = "id")
    private String locationID;

    @Column(name = "divisions")
    private String divisions;

    @Column(name = "districts")
    private String districts;

    @Column(name = "upazillas")
    private String upazillas;

    @Column(name = "post_office")
    private String postOffice;

    @Column(name = "police_station")
    private String policeStation;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;
}
