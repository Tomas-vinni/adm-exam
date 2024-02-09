package ru.dvinni.davito.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) для регистрации пользователя.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDTO {
    @NotEmpty(message = "Поле не должно быть пустым.")
    @Size(min = 3, max = 255, message = "В имени должно быть от 3 до 255 символов.")
    private String username;

    @NotEmpty(message = "Поле не должно быть пустым.")
    @Size(min = 3, max = 255, message = "В имени должно быть от 3 до 255 символов.")
    private String name;

    @NotEmpty(message = "Поле не должно быть пустым.")
    @Size(min = 8, max = 255, message = "Пароль должен содержать от 8 до 255 символов.")
    private String password;

    @NotEmpty(message = "Поле не должно быть пустым.")
    private String confirmPassword;

    @NotEmpty(message = "Поле не должно быть пустым.")
    @Size(max = 255, message = "Email не должен содержать больше 255 символов.")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+.+.[A-Za-z]{2,4}$", message = "Email должен быть валиден.")
    private String email;

    private BigDecimal money;
}
