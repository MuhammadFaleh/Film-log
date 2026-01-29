package com.dev.filmlog.DTO.In;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTOIn {

    @NotEmpty(message = "Sorry, review title can't be empty, please try again")
    @Size(max = 60, message = "Sorry, review title can't be more than 60 characters, please try again")
    private String title;
    @NotEmpty(message = "Sorry, review description can't be empty, please try again")
    @Size(max = 350, message = "Sorry, review description can't be more than 350 characters, please try again")
    private String description;
    @NotNull(message = "Sorry, review rating can't be empty, please try again")
    @PositiveOrZero(message = "Sorry, review rating can't be negative, please try again")
    private Double rating;
    @NotNull(message = "Sorry, review rating can't be empty, please try again")
    private Integer mediaId;
}
