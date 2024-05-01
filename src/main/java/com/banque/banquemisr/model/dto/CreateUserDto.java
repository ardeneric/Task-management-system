package com.banque.banquemisr.model.dto;

import com.banque.banquemisr.enums.UserRole;
import lombok.Data;

@Data
public class CreateUserDto {
    private String username;
    private String password;
    private UserRole role;
    private String email;
}