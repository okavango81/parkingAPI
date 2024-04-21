package com.okavango.parkingapi.repositories;

import com.okavango.parkingapi.domains.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
}
