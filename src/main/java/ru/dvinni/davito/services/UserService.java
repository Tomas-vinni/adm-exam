package ru.dvinni.davito.services;

import ru.dvinni.davito.dto.AddUserDTO;
import ru.dvinni.davito.views.UserDetailsInfoModelView;
import ru.dvinni.davito.views.UserInfoModelView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Интерфейс пользователей.
 */
public interface UserService {
    void saveUser(AddUserDTO userDTO);
    List<UserInfoModelView> getAllUsers();
    UserDetailsInfoModelView getUser(String userId);
    void deleteUser(String userId);
    void buyItem(String itemId);
    void sellItem(String itemId);
    void depositAccount(BigDecimal money);
}
