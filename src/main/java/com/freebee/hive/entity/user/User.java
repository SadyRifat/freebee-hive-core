package com.freebee.hive.entity.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.freebee.hive.util.StaticTextConfig.DB_SCHEMA;

@Getter
@Setter
@Entity
@Table(name = "users", schema = DB_SCHEMA)
public class User {
    @Id
    @Column(name = "id")
    private String userID;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "otp")
    private String otp;

    @Column(name = "is_enabled")
    private boolean enabled = false;

    @Column(name = "is_not_expired")
    private boolean accountNonExpired = true;

    @Column(name = "is_not_locked")
    private boolean accountNonLocked = true;

    @Column(name = "is_cred_not_expired")
    private boolean credentialsNonExpired = true;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles = new HashSet<>();
}
