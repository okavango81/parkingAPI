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

public class SwaggerUserById {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(summary = "Return by id", description = "Return parking user by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Return of parking users defined by id",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingUserMinDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Resource not found / The URI does not contain a valid identifier",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    ),

                    @ApiResponse(responseCode = "400", description = "Check the full URL as there may be typos",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    )
            }
    )
    public @interface UserById {}
}
