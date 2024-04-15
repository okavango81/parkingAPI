package com.okavango.parkingapi.services;

import com.okavango.parkingapi.domains.ParkingUser;
import com.okavango.parkingapi.domains.dtos.ParkingUserDTO;
import com.okavango.parkingapi.domains.dtos.ParkingUserMinDTO;
import com.okavango.parkingapi.domains.dtos.ParkingUserUpdatePasswordDTO;
import com.okavango.parkingapi.exceptions.NewPasswordDifferentFromPasswordConfirmationException;
import com.okavango.parkingapi.exceptions.PasswordProvidedDifferentFromRegisteredPasswordException;
import com.okavango.parkingapi.repositories.ParkingUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingUserService {

    private final ParkingUserRepository parkingUserRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public ResponseEntity<ParkingUserMinDTO> registration(ParkingUserDTO user) {
        ParkingUser u = new ParkingUser(user.getUsername(), passwordEncoder.encode(user.getPassword()));
        parkingUserRepository.save(u);
        ParkingUserMinDTO newUser =  new ParkingUserMinDTO(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ParkingUserMinDTO> findId(Long id) {
        Optional<ParkingUser> u = parkingUserRepository.findById(id);
        return ResponseEntity.ok(new ParkingUserMinDTO(u.get()));
    }

    @Transactional(readOnly = true)
    public ParkingUser findUsername(String username) {
        return  parkingUserRepository.findByUsername(username);
    }

    @Transactional
    public ResponseEntity<Void> update(ParkingUserUpdatePasswordDTO user, Long id) {
        Optional<ParkingUser> u = parkingUserRepository.findById(id);

        // se a nova senha for diferente da confirmacao de senha
        if (!user.getNewPassword().equals(user.getPasswordConfirmation())) {
            throw new NewPasswordDifferentFromPasswordConfirmationException("");
        }
        // se a senha atual fornecida for diferente da senha cadastrada no BD
        //if (!user.getCurrentPassword().equals(u.get().getPassword())) {
        if (!passwordEncoder.matches(user.getCurrentPassword(), u.get().getPassword())) {
            throw new PasswordProvidedDifferentFromRegisteredPasswordException("");

        } else {
            u.get().setPassword(passwordEncoder.encode(user.getNewPassword()));
            return ResponseEntity.noContent().build();
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<ParkingUserMinDTO>> all() {
        return ResponseEntity.ok(parkingUserRepository.findAll().stream().map(ParkingUserMinDTO::new).collect(Collectors.toList()));
    }

    @Transactional
    public ResponseEntity<Void> delete(Long id) {
        findId(id);
        parkingUserRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @Transactional(readOnly = true)
    public ParkingUser.Role roleByUsername(String username) {
        return parkingUserRepository.findRoleByUsername(username);
    }
}
