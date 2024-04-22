package com.okavango.parkingapi.services;

import com.okavango.parkingapi.domains.ParkingSpace;
import com.okavango.parkingapi.domains.dtos.ParkingSpaceDTO;
import com.okavango.parkingapi.domains.dtos.ParkingSpaceMinDTO;
import com.okavango.parkingapi.repositories.ParkingSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingSpaceService {

    private final ParkingSpaceRepository parkingSpaceRepository;

    @Transactional
    public ResponseEntity<ParkingSpaceMinDTO> save(ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpace parkingSpace = parkingSpaceRepository.save(new ParkingSpace(parkingSpaceDTO.getCode().toUpperCase(), parkingSpaceDTO.getStatus()));
        ParkingSpaceMinDTO parkingSpaceMinDTO = new ParkingSpaceMinDTO(parkingSpace);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpaceMinDTO);
    }

    @Transactional
    public ResponseEntity<ParkingSpaceMinDTO> findByCode(String code) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findByCode(code);
        return ResponseEntity.ok().body(new ParkingSpaceMinDTO(parkingSpace));
    }
}
