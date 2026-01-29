package com.dev.filmlog.DTO.Out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDTOOut {
    private Integer id;
    private String username;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String role;
}
