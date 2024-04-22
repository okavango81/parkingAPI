package com.okavango.parkingapi.repositories;

import com.okavango.parkingapi.domains.ParkingSpace;
import com.okavango.parkingapi.domains.dtos.ParkingSpaceMinDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    ParkingSpace findByCode(String code);
}
