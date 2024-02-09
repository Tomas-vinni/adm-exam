package ru.dvinni.davito.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dvinni.davito.models.ItemType;
import ru.dvinni.davito.models.Type;

import java.util.Optional;

/**
 * Репозиторий типов предметов.
 */
@Repository
public interface TypeRepository extends JpaRepository<Type, String> {
    Optional<Type> findByItemName(ItemType itemType);
}
