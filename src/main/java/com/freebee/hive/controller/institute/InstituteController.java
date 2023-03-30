package com.freebee.hive.controller.institute;

import com.freebee.hive.dto.institute.InstituteCreateReq;
import com.freebee.hive.dto.institute.InstituteResponse;
import com.freebee.hive.dto.institute.InstituteUpdateReq;
import com.freebee.hive.dto.response.BaseResponse;
import com.freebee.hive.model.BaseUser;
import com.freebee.hive.security.CurrentUser;
import com.freebee.hive.service.institute.InstituteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/institute")
public class InstituteController {
    private final InstituteService instituteService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> crateInstitute(@Valid @RequestBody InstituteCreateReq instituteCreateReq, @CurrentUser BaseUser baseUser) {
        InstituteResponse instituteResponse = instituteService.addNewInstitute(instituteCreateReq, baseUser);
        return new ResponseEntity<>(new BaseResponse(instituteResponse, HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateInstitute(@Valid @RequestBody InstituteUpdateReq instituteUpdateReq) {
        InstituteResponse instituteResponse = instituteService.updateInstitute(instituteUpdateReq);
        return new ResponseEntity<>(new BaseResponse(instituteResponse, HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> getInstituteList(@PathVariable(value = "id") String instituteID) {
        InstituteResponse institute = instituteService.getInstitute(instituteID);
        return new ResponseEntity<>(new BaseResponse(institute, HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> getInstituteList() {
        List<InstituteResponse> instituteList = instituteService.getInstituteList();
        return new ResponseEntity<>(new BaseResponse(instituteList, HttpServletResponse.SC_OK, null), HttpStatus.OK);
    }
}
