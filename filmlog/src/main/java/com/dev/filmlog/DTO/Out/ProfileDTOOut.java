package com.dev.filmlog.DTO.Out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProfileDTOOut {
    private String profileName;
    private String description;
}
