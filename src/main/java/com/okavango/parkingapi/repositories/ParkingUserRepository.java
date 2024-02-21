package com.okavango.parkingapi.repositories;

import com.okavango.parkingapi.domains.ParkingUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingUserRepository extends JpaRepository<ParkingUser, Long> {
}