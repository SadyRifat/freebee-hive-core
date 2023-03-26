package com.freebee.hive.model;

import com.freebee.hive.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {
    private String consumerID;
    private Set<ERole> eRoles;
}
