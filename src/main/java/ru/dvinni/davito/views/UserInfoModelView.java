package ru.dvinni.davito.views;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * View для отображения информации о пользователе.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserInfoModelView {
    private String username;
    private String name;
    private String email;
}
