package com.okavango.parkingapi.controllers;


import com.okavango.parkingapi.domains.dtos.ParkingClientDTO;
import com.okavango.parkingapi.domains.dtos.ParkingClientMinDTO;
import com.okavango.parkingapi.jwt.JWTUserDetail;
import com.okavango.parkingapi.services.ParkingClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ParkingClientController {

    private final ParkingClientService parkingClientService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ParkingClientMinDTO> register(@Valid @RequestBody ParkingClientDTO client){
        return parkingClientService.registration(client);
    }
}
