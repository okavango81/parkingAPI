package com.okavango.parkingapi.controllers;

import com.okavango.parkingapi.domains.dtos.ParkingUserLoginDTO;
import com.okavango.parkingapi.jwt.JWTUserDetailService;
import com.okavango.parkingapi.jwt.JWToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JWTUserDetailService detailService;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody @Valid ParkingUserLoginDTO parkingUserLoginDTO, HttpServletRequest request) {
        log.info("Authentication process {}", parkingUserLoginDTO.getUsername());

        try {

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(parkingUserLoginDTO.getUsername(), parkingUserLoginDTO.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            JWToken token = detailService.getTokenAuthenticated(parkingUserLoginDTO.getUsername());

            return ResponseEntity.ok().body(token);

        } catch (AuthenticationException ex) {
            log.warn("Bad credentials from username{}", parkingUserLoginDTO.getUsername());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
