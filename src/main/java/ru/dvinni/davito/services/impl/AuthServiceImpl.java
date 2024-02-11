package ru.dvinni.davito.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dvinni.davito.dto.UserRegistrationDTO;
import ru.dvinni.davito.models.Role;
import ru.dvinni.davito.models.User;
import ru.dvinni.davito.models.UserRoles;
import ru.dvinni.davito.repositories.RoleRepository;
import ru.dvinni.davito.repositories.UserRepository;
import ru.dvinni.davito.services.AuthService;

import java.util.Set;

/**
 * Сервис авторизации и аутентификации.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User getAuthUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == null) {
            throw new RuntimeException("Пользователь не авторизован");
        }
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь на найден"));
    }

    @Override
    public void register(UserRegistrationDTO userRegistrationDTO) {
        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            throw new RuntimeException("Пароли не совпадают");
        }

        User user = modelMapper.map(userRegistrationDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findRoleByRoleName(UserRoles.USER).orElseThrow(() -> new RuntimeException("Роль пользователя не найдена"));
        user.setRoles(Set.of(role));

        userRepository.save(user);
    }
}
