package com.okavango.parkingapi.controllers;

import com.okavango.parkingapi.docs.parking_user.SwaggerParkingSpace;
import com.okavango.parkingapi.domains.dtos.ParkingSpaceDTO;
import com.okavango.parkingapi.domains.dtos.ParkingSpaceMinDTO;
import com.okavango.parkingapi.services.ParkingSpaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/vacancies")
@RequiredArgsConstructor
@Tag(name = "ParkingSpaces", description = "Operations related to the ParkingSpace entity")
public class ParkingSpaceController {

    private final ParkingSpaceService parkingSpaceService;

//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<ParkingSpaceMinDTO> vacancy(@Valid @RequestBody ParkingSpaceDTO parkingSpaceDTO){
//        return parkingSpaceService.save(parkingSpaceDTO);
//    }

    @SwaggerParkingSpace.NewParkingSpace
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> vacancy(@Valid @RequestBody ParkingSpaceDTO parkingSpaceDTO){
        parkingSpaceService.save(parkingSpaceDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}".toLowerCase()).buildAndExpand(parkingSpaceDTO.getCode()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParkingSpaceMinDTO> vacancyPerCode(@PathVariable String code){
            return parkingSpaceService.findByCode(code);
    }

}
