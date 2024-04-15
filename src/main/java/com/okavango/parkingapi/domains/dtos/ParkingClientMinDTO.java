package com.okavango.parkingapi.domains.dtos;

import com.okavango.parkingapi.domains.ParkingClient;
import lombok.Getter;

@Getter
public class ParkingClientMinDTO {

    private Long id;
    private String name;
    private String cpf;

    public ParkingClientMinDTO(ParkingClient client){
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
    }
}
