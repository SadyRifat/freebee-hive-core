package com.freebee.hive.repository.institute;

import com.freebee.hive.entity.institute.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, String> {
}
