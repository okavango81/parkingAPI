package com.okavango.parkingapi.services;

import com.okavango.parkingapi.domains.ParkingUser;
import com.okavango.parkingapi.domains.dtos.ParkingUserDTO;
import com.okavango.parkingapi.domains.dtos.ParkingUserMinDTO;
import com.okavango.parkingapi.domains.dtos.ParkingUserUpdatePasswordDTO;
import com.okavango.parkingapi.repositories.ParkingUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingUserService {

    private final ParkingUserRepository parkingUserRepository;

    @Transactional
    public ResponseEntity<ParkingUserMinDTO> registration(ParkingUserDTO user){
        ParkingUser u = new ParkingUser(user.getUsername(), user.getPassword());
        parkingUserRepository.save(u);
        ParkingUserMinDTO newUser = new ParkingUserMinDTO(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ParkingUserMinDTO> findId(Long id) {
        ParkingUser u = parkingUserRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(new ParkingUserMinDTO(u));
    }

    @Transactional
    public ResponseEntity<Void> update(ParkingUserUpdatePasswordDTO user, Long id){

        ParkingUser u = parkingUserRepository.findById(id).map(ResponseEntity::ok).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).getBody();
        if (!user.getNewPassword().equals(user.getPasswordConfirmation())){
            throw new RuntimeException("The new password must be the same as the confirmation password! ");
        }if (!user.getCurrentPassword().equals(u.getPassword())){
            throw new RuntimeException("Incorrect current password!");
        }else {
            u.setPassword(user.getNewPassword());
            return ResponseEntity.noContent().build();
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<ParkingUserMinDTO>> all() {
        List<ParkingUserMinDTO> userDTOs = parkingUserRepository.findAll().stream()
                .map(ParkingUserMinDTO::new) // Mapeia os objetos ParkingUser para DTOs
                .collect(Collectors.toList()); // Coleta os DTOs em uma lista
        return ResponseEntity.ok(userDTOs); // Retorna a lista encapsulada em um ResponseEntity
    }

    @Transactional
    public ResponseEntity<Void> delete(Long id){
        findId(id);
        parkingUserRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
