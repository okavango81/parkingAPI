package com.okavango.parkingapi.domains.dtos;

import com.okavango.parkingapi.domains.ParkingSpace;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSpaceMinDTO {

    private Long id;
    private String code;
    private String status;

    public ParkingSpaceMinDTO(ParkingSpace vacancy){
        id = vacancy.getId();
        code = vacancy.getCode();
        status = String.valueOf(vacancy.getVacancyStatus());
    }
}
