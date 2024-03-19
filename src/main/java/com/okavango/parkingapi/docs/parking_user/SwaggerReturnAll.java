package com.okavango.parkingapi.docs.parking_user;

import com.okavango.parkingapi.domains.dtos.ParkingUserMinDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SwaggerReturnAll {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(summary = "Return All", description = "Return all parking users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Return of all registered parking users",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingUserMinDTO.class))
                    )
            }
    )
    public @interface ReturnAll{}
}
