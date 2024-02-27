package com.okavango.parkingapi.domains.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ParkingUserDTO {

    @NotBlank
    @Email(message = "Invalid email format!")
    private String username;

    @NotBlank
    @Size(min = 6, max = 6, message = "Password length must be exactly 6 characters")
    private String password;

}
