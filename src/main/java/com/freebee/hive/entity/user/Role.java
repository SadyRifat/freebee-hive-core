package com.freebee.hive.entity.user;

import com.freebee.hive.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

import static com.freebee.hive.util.StaticTextConfig.DB_SCHEMA;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles", schema = DB_SCHEMA)
public class Role {
    @Id
    @Column(name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    private ERole name;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;
}
