package ru.dvinni.davito.services;

import ru.dvinni.davito.dto.AddItemDTO;
import ru.dvinni.davito.views.ItemInfoDetailsModelView;
import ru.dvinni.davito.views.ItemInfoModelView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Интерфейс предметов.
 */
public interface ItemService {
    void addItem(AddItemDTO itemDTO);
    void deleteItem(String itemId);
    void setNewCost(String itemId, BigDecimal newCost);
    List<ItemInfoModelView> getAllItems();
    List<ItemInfoModelView> getAllProductItems();
    List<ItemInfoModelView> getAllProductItemsToOwner();
    List<ItemInfoModelView> getAllOwnershipItems();
    ItemInfoDetailsModelView getItem(String itemId);
}
