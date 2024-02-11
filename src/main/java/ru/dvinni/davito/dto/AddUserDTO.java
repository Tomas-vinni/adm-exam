package ru.dvinni.davito.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) для добавления пользователя.
 */
@Getter
@Setter
@NoArgsConstructor
public class AddUserDTO {
    private String username;
    private String password;
    private String name;
    private String email;
}
