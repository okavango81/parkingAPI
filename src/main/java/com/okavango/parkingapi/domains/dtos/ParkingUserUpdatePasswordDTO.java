package com.okavango.parkingapi.domains.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingUserUpdatePasswordDTO {

    @NotBlank
    @Size(min = 6, max = 6, message = "Password length must be exactly 6 characters")
    private String currentPassword;

    @NotBlank
    @Size(min = 6, max = 6, message = "Password length must be exactly 6 characters")
    private String newPassword;

    @NotBlank
    @Size(min = 6, max = 6, message = "Password length must be exactly 6 characters")
    private String passwordConfirmation;
}
