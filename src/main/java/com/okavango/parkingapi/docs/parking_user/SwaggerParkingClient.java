package com.okavango.parkingapi.docs.parking_user;

import com.okavango.parkingapi.domains.dtos.ParkingClientMinDTO;
import com.okavango.parkingapi.domains.projection.PaginatedResponse;
import com.okavango.parkingapi.exceptions.ExceptionObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SwaggerParkingClient {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(summary = "Register", description = "Register a new parking client (Operation permited only to CLIENT profile authenticated)",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resource created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingClientMinDTO.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "User unauthorized"),
                    @ApiResponse(responseCode = "403", description = "User have be not permission to access this resource",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    )
            }
    )
    public @interface NewClient {
    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(summary = "Return All", description = "Return all parking clients (Operation permited only to ADMIN profile authenticated)",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "401", description = "User unauthorized",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema())
                    ),
                    @ApiResponse(responseCode = "200", description = "Return of all registered parking clients",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PaginatedResponse.class))
                    ),
                    @ApiResponse(responseCode = "403", description = "User have be not permission to access this resource",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    )
            }
    )
    public @interface ReturnAllClients {
    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(summary = "Return by id", description = "Return parking client by id (Operation permited only to ADMIN profile authenticated)",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "401", description = "User unauthorized",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema())
                    ),
                    @ApiResponse(responseCode = "200", description = "Return of parking client defined by id",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingClientMinDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Resource not found / The URI does not contain a valid identifier",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    ),

                    @ApiResponse(responseCode = "400", description = "Check the full URL as there may be typos",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    ),
                    @ApiResponse(responseCode = "403", description = "User have be not permission to access this resource",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    )
            }
    )
    public @interface ClientById {
    }
}
