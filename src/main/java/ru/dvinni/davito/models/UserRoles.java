package ru.dvinni.davito.models;

import lombok.Getter;

/**
 * Набор ролей пользователя.
 */
@Getter
public enum UserRoles {

    ADMIN(0),
    USER(1);

    private final int roleId;

    UserRoles(int roleId) {
        this.roleId = roleId;
    }

    public static UserRoles fromRoleId(int roleId) {
        return UserRoles.values()[roleId];
    }
}
