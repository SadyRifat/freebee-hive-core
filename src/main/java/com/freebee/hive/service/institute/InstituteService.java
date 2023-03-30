package com.freebee.hive.service.institute;

import com.freebee.hive.dto.institute.InstituteCreateReq;
import com.freebee.hive.dto.institute.InstituteResponse;
import com.freebee.hive.dto.institute.InstituteUpdateReq;
import com.freebee.hive.entity.institute.Institute;
import com.freebee.hive.entity.institute.Location;
import com.freebee.hive.exception.ResourceNotFoundException;
import com.freebee.hive.mapper.InstituteMapper;
import com.freebee.hive.model.BaseUser;
import com.freebee.hive.repository.institute.InstituteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class InstituteService {
    private final LocationService locationService;
    private final InstituteMapper instituteMapper;
    private final InstituteRepository instituteRepository;

    public InstituteResponse addNewInstitute(InstituteCreateReq instituteCreateReq, BaseUser baseUser) {
        Location location = locationService.findLocationByID(instituteCreateReq.getLocationID());
        Institute institute = instituteMapper.createReqToInstitute(instituteCreateReq);
        institute.setInstitutionID(UUID.randomUUID().toString());
        institute.setAddedBy(baseUser.getUsername());
        institute.setLocation(location);
        institute.setCurrentlyActive(true);

        ZonedDateTime timeNow = ZonedDateTime.now();
        institute.setJoinedOn(timeNow);
        institute.setCreatedOn(timeNow);
        institute.setUpdatedOn(timeNow);

        instituteRepository.save(institute);
        return instituteMapper.instituteToResponse(institute);
    }

    public InstituteResponse updateInstitute(InstituteUpdateReq instituteUpdateReq) {
        Institute institute = findInstituteByID(instituteUpdateReq.getInstitutionID());
        Location location = locationService.findLocationByID(instituteUpdateReq.getLocationID());
        institute.setLocation(location);
        institute.setInstituteInitials(instituteUpdateReq.getInstituteInitials());
        institute.setInstituteName(instituteUpdateReq.getInstituteName());
        institute.setInstituteReg(instituteUpdateReq.getInstituteReg());
        institute.setZipCode(instituteUpdateReq.getZipCode());
        institute.setUpdatedOn(ZonedDateTime.now());

        instituteRepository.save(institute);
        return instituteMapper.instituteToResponse(institute);
    }

    public InstituteResponse getInstitute(String instituteID) {
        Institute institute = findInstituteByID(instituteID);
        return instituteMapper.instituteToResponse(institute);
    }

    public List<InstituteResponse> getInstituteList() {
        List<Institute> institutes = instituteRepository.findAll();
        return instituteMapper.instituteToResponse(institutes);
    }

    public Institute findInstituteByID(String instituteID) {
        return instituteRepository.findById(instituteID).orElseThrow(() -> new ResourceNotFoundException("Institute", "instituteID", instituteID));
    }

}
