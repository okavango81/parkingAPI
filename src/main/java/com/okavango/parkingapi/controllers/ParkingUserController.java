package com.okavango.parkingapi.controllers;

import com.okavango.parkingapi.services.ParkingUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class ParkingUserController {

    private final ParkingUserService parkingUserService;
}
