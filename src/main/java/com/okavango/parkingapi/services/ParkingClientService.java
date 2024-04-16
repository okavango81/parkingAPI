package com.okavango.parkingapi.services;

import com.okavango.parkingapi.domains.ParkingClient;
import com.okavango.parkingapi.domains.dtos.ParkingClientDTO;
import com.okavango.parkingapi.domains.dtos.ParkingClientMinDTO;
import com.okavango.parkingapi.jwt.JWTUserDetail;
import com.okavango.parkingapi.repositories.ParkingClientRepository;
import com.okavango.parkingapi.repositories.ParkingUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingClientService {

    private final ParkingClientRepository parkingClientRepository;
    private final ParkingUserService parkingUserService;

    @Transactional
    public ResponseEntity<ParkingClientMinDTO> registration(ParkingClientDTO clientDTO){
        ParkingClient c = parkingClientRepository.save(new ParkingClient(clientDTO.getName(), clientDTO.getCpf())) ;
        ParkingClientMinDTO client = new ParkingClientMinDTO(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ParkingClientMinDTO> findId(Long id){
        Optional<ParkingClient> c = parkingClientRepository.findById(id);
        return ResponseEntity.ok().body(new ParkingClientMinDTO(c.get()));
    }

}
