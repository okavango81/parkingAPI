package com.okavango.parkingapi.domains.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSpaceDTO {

    @NotBlank(message = "must have be not null")
    @Size(min = 4, max = 4, message = "must contain at least {min} and at most {max} characters")
    private String code;

    @NotBlank(message = "must have be not null")
    @Pattern(regexp = "F|O|f|o", message = "The allowed values are: F for FREE or O for OCCUPIED")
    @Size(min = 1, max = 1, message = "must contain at least {min} and at most {max} characters")
    private String status;
}
