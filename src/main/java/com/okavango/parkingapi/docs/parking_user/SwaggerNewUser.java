package com.okavango.parkingapi.docs.parking_user;

import com.okavango.parkingapi.domains.dtos.ParkingUserMinDTO;
import com.okavango.parkingapi.exceptions.ExceptionObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SwaggerNewUser {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(
            summary = "Register",
            description = "Registration parking user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resource created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingUserMinDTO.class))
                    ),
                    @ApiResponse(responseCode = "409", description = "This username is already registered in the database",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    ),
                    @ApiResponse(responseCode = "422", description = "Validation of the supplied argument(s) failed",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    )
            }
    )
    public @interface NewUser{}
}
