package com.okavango.parkingapi.services;

import com.okavango.parkingapi.repositories.ParkingUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingUserService {

    private final ParkingUserRepository parkingUserRepository;
}
