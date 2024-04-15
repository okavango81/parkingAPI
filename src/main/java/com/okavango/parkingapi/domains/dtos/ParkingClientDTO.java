package com.okavango.parkingapi.domains.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingClientDTO {

    @NotBlank(message = "Must not be null")
    @Size(min = 6, max = 50, message = "Must contain a minimum of {min} and a maximum of {max} characters")
    private String name;

    @NotBlank(message = "Must not be null")
    @CPF(message = "Invalid format")
    @Size(min = 11, max = 14, message = "Must contain a minimum of {min} and a maximum of {max} characters")
    private String cpf;
}
