package com.okavango.parkingapi.domains.dtos;

import com.okavango.parkingapi.domains.ParkingUser;
import lombok.Getter;

@Getter
public class ParkingUserMinDTO {
    private Long id;
    private String username;
    private String role;

    public ParkingUserMinDTO(ParkingUser user){
        id = user.getId();
        username = user.getUsername();
        role = user.getRole().name().substring("ROLE_".length());
    }
}
