package com.okavango.parkingapi.controllers;

import com.okavango.parkingapi.domains.ParkingUser;
import com.okavango.parkingapi.domains.dtos.ParkingUserDTO;
import com.okavango.parkingapi.domains.dtos.ParkingUserMinDTO;
import com.okavango.parkingapi.domains.dtos.ParkingUserUpdatePasswordDTO;
import com.okavango.parkingapi.services.ParkingUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class ParkingUserController {

    private final ParkingUserService parkingUserService;

    @PostMapping
    public ResponseEntity<ParkingUserMinDTO> newUser(@Valid @RequestBody ParkingUserDTO user){
        return parkingUserService.registration(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingUserMinDTO> userById(@PathVariable Long id){
        return parkingUserService.findId(id);
    }

    @GetMapping
    public ResponseEntity<List<ParkingUserMinDTO>> returnAll(){
        return parkingUserService.all();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody ParkingUserUpdatePasswordDTO user, @PathVariable Long id){
        return parkingUserService.update(user,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable  Long id){
        return parkingUserService.delete(id);
    }

}
