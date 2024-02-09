package ru.dvinni.davito.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dvinni.davito.models.Item;
import ru.dvinni.davito.models.ItemType;
import ru.dvinni.davito.models.Type;
import ru.dvinni.davito.models.User;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий предметов.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    Optional<Item> findItemByItemName(String itemName);
    Optional<Item> findItemById(String itemId);
    List<Item> findItemByOwner(User owner);
    List<Item> findItemByItemType(Type itemType);
    List<Item> findItemByOwnerAndItemType(User owner, Type itemType);
}
