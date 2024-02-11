package ru.dvinni.davito.services;

import ru.dvinni.davito.dto.UserRegistrationDTO;
import ru.dvinni.davito.models.User;

/**
 * Интерфейс сервиса авторизации и аутентификации.
 */
public interface AuthService {
    User getAuthUser();
    void register(UserRegistrationDTO userRegistrationDTO);
}
