package com.okavango.parkingapi.controllers;

import com.okavango.parkingapi.docs.parking_user.*;
import com.okavango.parkingapi.domains.dtos.ParkingUserDTO;
import com.okavango.parkingapi.domains.dtos.ParkingUserMinDTO;
import com.okavango.parkingapi.domains.dtos.ParkingUserUpdatePasswordDTO;
import com.okavango.parkingapi.services.ParkingUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ParkingUsers", description = "Operations related to the ParkingUser entity")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class ParkingUserController {

    private final ParkingUserService parkingUserService;

    @SwaggerParkingUser.NewUser
    @PostMapping
    public ResponseEntity<ParkingUserMinDTO> newUser(@Valid @RequestBody ParkingUserDTO user) {
        return parkingUserService.registration(user);
    }


    @SwaggerParkingUser.UserById
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('CLIENT') AND #id == authentication.principal.id)")
    public ResponseEntity<ParkingUserMinDTO> userById(@PathVariable Long id) {
        return parkingUserService.findId(id);
    }


    @SwaggerParkingUser.ReturnAll
    @GetMapping
    public ResponseEntity<List<ParkingUserMinDTO>> returnAll() {
        return parkingUserService.all();
    }


    @SwaggerParkingUser.UpdatePassword
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CLIENT') AND (#id == authentication.principal.id)")
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody ParkingUserUpdatePasswordDTO user, @PathVariable Long id) {
        return parkingUserService.update(user, id);
    }


    @SwaggerParkingUser.RemoveUser
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable Long id) {
        return parkingUserService.delete(id);
    }

}
