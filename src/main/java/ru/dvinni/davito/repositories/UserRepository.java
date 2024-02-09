package ru.dvinni.davito.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import ru.dvinni.davito.models.User;

import java.util.Optional;

/**
 * Репозиторий пользователей.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);

    @Modifying
    @Transactional
    void deleteUserByUsername(String username);
}
