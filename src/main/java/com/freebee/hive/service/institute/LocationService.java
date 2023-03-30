package com.freebee.hive.service.institute;

import com.freebee.hive.entity.institute.Location;
import com.freebee.hive.exception.ResourceNotFoundException;
import com.freebee.hive.repository.institute.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public Location findLocationByID(String locationID) {
        return locationRepository.findById(locationID).orElseThrow(() -> new ResourceNotFoundException("Location", "locationID", locationID));
    }
}
