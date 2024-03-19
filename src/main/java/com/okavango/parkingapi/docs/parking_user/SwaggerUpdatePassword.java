package com.okavango.parkingapi.docs.parking_user;

import com.okavango.parkingapi.exceptions.ExceptionObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class SwaggerUpdatePassword {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Operation(summary = "Update password", description = "Update parking user password defined by id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Parking user password updated successfully"),
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
    public @interface UpdatePassword{}
}
