package com.okavango.parkingapi.repositories;

import com.okavango.parkingapi.domains.ParkingClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingClientRepository extends JpaRepository<ParkingClient, Long> {
}
