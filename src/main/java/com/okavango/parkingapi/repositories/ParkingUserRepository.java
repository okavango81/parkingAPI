package com.okavango.parkingapi.repositories;

import com.okavango.parkingapi.domains.ParkingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParkingUserRepository extends JpaRepository<ParkingUser, Long> {
    Optional<ParkingUser> findByUsername(String username);

    @Query("SELECT u.role FROM ParkingUser u WHERE u.username LIKE :username")
    ParkingUser.Role findRoleByUsername(String username);
}