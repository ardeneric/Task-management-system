package com.banque.banquemisr.model.dto;

import com.banque.banquemisr.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserDto {
    @NotNull(message = "Username is mandatory")
    private String username;
    @NotNull(message = "Password is mandatory")
    private String password;
    @NotNull(message = "Rolse is mandatory, ADMIN or USER")
    private UserRole role;
    @NotNull(message = "Email is mandatory")
    private String email;
}