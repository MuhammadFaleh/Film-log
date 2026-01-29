package com.dev.filmlog.DTO.In;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTOIn {

    @NotBlank(message = "username must not be empty")
    @Size(min = 4, max = 40, message = "username must be between 4 and 40 in length")
    private String username;

    @NotBlank(message = "email must not be empty")
    @Size(max = 255, message = "email must be no longer than 255 in length")
    @Email(message = "email must be valid")
    private String email;
    @NotBlank(message = "password must not be empty")
    @Pattern( regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$",
            message = "Password must have at least 8 characters, " +
                    "and less than 20 characters, " +
                    "one uppercase letter, one lowercase letter, one number, " +
                    "one special character, one digit")
    private String password;

    @NotBlank(message = "full name must not be empty")
    @Size(min = 4, max = 255, message = "full name must be between 4 and 255 in length")
    private String fullName;
    @NotBlank(message = "phone number must not be empty")
    @Pattern(regexp = "^9665\\d{8}$",
            message = "Phone number must be in the format 9665xxxxxxxx")
    private String phoneNumber;

}
