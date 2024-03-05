package com.okavango.parkingapi.controllers;

import com.okavango.parkingapi.domains.dtos.ParkingUserDTO;
import com.okavango.parkingapi.domains.dtos.ParkingUserMinDTO;
import com.okavango.parkingapi.domains.dtos.ParkingUserUpdatePasswordDTO;
import com.okavango.parkingapi.exceptions.ExceptionObject;
import com.okavango.parkingapi.exceptions.GlobalExceptionHandler;
import com.okavango.parkingapi.services.ParkingUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ParkingUsers", description = "Operations related to the ParkingUser entity")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class ParkingUserController {

    private final ParkingUserService parkingUserService;

    //cadastrar novo
    @Operation(summary = "Register", description = "Registration of parking users",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resource created",
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
    @PostMapping
    public ResponseEntity<ParkingUserMinDTO> newUser(@Valid @RequestBody ParkingUserDTO user) {
        return parkingUserService.registration(user);
    }

    //retornar por id
    @Operation(summary = "Return by id", description = "Return parking user by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Return parking user defined by id",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingUserMinDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ParkingUserMinDTO> userById(@PathVariable Long id) {
        return parkingUserService.findId(id);
    }

    //retornar todos
    @Operation(summary = "Return All", description = "Return all parking users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Return All",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParkingUserMinDTO.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ParkingUserMinDTO>> returnAll() {
        return parkingUserService.all();
    }

    //atualizar a senha
    @Operation(summary = "Update password", description = "Update parking user password defined by id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Update parking user password",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Inconsistent data",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    ),
                    @ApiResponse(responseCode = "422", description = "Validation of the supplied argument(s) failed",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    )
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody ParkingUserUpdatePasswordDTO user, @PathVariable Long id) {
        return parkingUserService.update(user, id);
    }

    //deletar usuario
    @Operation(summary = "Remove by id", description = "Return parking user by id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Remove parking user defined by id",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionObject.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable Long id) {
        return parkingUserService.delete(id);
    }

}
