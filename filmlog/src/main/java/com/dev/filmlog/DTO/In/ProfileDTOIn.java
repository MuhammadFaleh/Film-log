package com.dev.filmlog.DTO.In;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDTOIn {
    @NotBlank(message = "profile name should not be empty")
    @Size(min = 4, max = 255, message = "profile name should not be longer than 255 and no shorter than 4")
    private String name;

    @NotBlank(message = "profile name should not be empty")
    @Size(min = 4, max = 350, message = "profile description should not be longer than 350 and no shorter than 4")
    private String description;
}
