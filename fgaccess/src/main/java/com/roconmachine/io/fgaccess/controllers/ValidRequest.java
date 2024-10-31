package com.roconmachine.io.fgaccess.controllers;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;




@Data
@Builder
public class ValidRequest {

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull
    @Size(message = "First name must be between 2 and 25 characters", min = 2, max = 25)
    private String firstName;
}
