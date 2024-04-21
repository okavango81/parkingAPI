package com.okavango.parkingapi.controllers;


import com.okavango.parkingapi.docs.parking_user.SwaggerParkingClient;
import com.okavango.parkingapi.domains.dtos.ParkingClientDTO;
import com.okavango.parkingapi.domains.dtos.ParkingClientMinDTO;
import com.okavango.parkingapi.domains.projection.PaginatedResponse;
import com.okavango.parkingapi.services.ParkingClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ParkingClients", description = "Operations related to the ParkingClient entity")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ParkingClientController {

    private final ParkingClientService parkingClientService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    @SwaggerParkingClient.NewClient
    public ResponseEntity<ParkingClientMinDTO> register(@Valid @RequestBody ParkingClientDTO client){
        return parkingClientService.registration(client);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SwaggerParkingClient.ClientById
    public ResponseEntity<ParkingClientMinDTO> findById(@PathVariable Long id){
        return parkingClientService.findId(id);
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SwaggerParkingClient.ReturnAllClients
    public ResponseEntity<PaginatedResponse<ParkingClientMinDTO>> returnAll(@PageableDefault(size = 12) Pageable pageable){
        return parkingClientService.allClients(pageable);
    }
}
