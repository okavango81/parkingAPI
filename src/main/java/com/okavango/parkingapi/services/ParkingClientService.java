package com.okavango.parkingapi.services;

import com.okavango.parkingapi.domains.ParkingClient;
import com.okavango.parkingapi.domains.dtos.ParkingClientDTO;
import com.okavango.parkingapi.domains.dtos.ParkingClientMinDTO;
import com.okavango.parkingapi.domains.projection.PaginatedResponse;
import com.okavango.parkingapi.repositories.ParkingClientRepository;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingClientService {

    private final ParkingClientRepository parkingClientRepository;
    private final ParkingUserService parkingUserService;

    @Transactional
    public ResponseEntity<ParkingClientMinDTO> registration(ParkingClientDTO clientDTO) {
        ParkingClient c = parkingClientRepository.save(new ParkingClient(clientDTO.getName(), clientDTO.getCpf()));
        ParkingClientMinDTO client = new ParkingClientMinDTO(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ParkingClientMinDTO> findId(Long id) {
        Optional<ParkingClient> c = parkingClientRepository.findById(id);
        return ResponseEntity.ok().body(new ParkingClientMinDTO(c.get()));
    }

    @Transactional
    public ResponseEntity<PaginatedResponse<ParkingClientMinDTO>> allClients(Pageable page) {

        Page<ParkingClient> clientsPage = parkingClientRepository.findAll(page);
        List<ParkingClientMinDTO> clientDTOs = clientsPage.getContent().stream().map(ParkingClientMinDTO::new).collect(Collectors.toList());
        PaginatedResponse<ParkingClientMinDTO> response = new PaginatedResponse<>();

        response.setContent(clientDTOs);
        response.setFirst(clientsPage.isFirst());
        response.setLast(clientsPage.isLast());
        response.setNumber(clientsPage.getNumber());
        response.setSize(clientsPage.getSize());
        response.setNumberOfElements(clientsPage.getNumberOfElements());
        response.setTotalPages(clientsPage.getTotalPages());
        response.setTotalElements(clientsPage.getTotalElements());

        return ResponseEntity.ok().body(response);
    }


}
