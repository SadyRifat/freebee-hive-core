package com.freebee.hive.mapper;

import com.freebee.hive.dto.institute.InstituteCreateReq;
import com.freebee.hive.dto.institute.InstituteResponse;
import com.freebee.hive.entity.institute.Institute;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstituteMapper {
    Institute createReqToInstitute(InstituteCreateReq instituteCreateReq);

    InstituteResponse instituteToResponse(Institute institute);

    List<InstituteResponse> instituteToResponse(List<Institute> institute);
}
