package ru.dvinni.davito.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dvinni.davito.models.Role;
import ru.dvinni.davito.models.UserRoles;

import java.util.Optional;

/**
 * Репозиторий ролей.
 */
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findRoleByRoleName(UserRoles role);
}
