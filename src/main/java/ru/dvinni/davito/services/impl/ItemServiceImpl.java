package ru.dvinni.davito.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.dvinni.davito.dto.AddItemDTO;
import ru.dvinni.davito.models.Item;
import ru.dvinni.davito.models.ItemType;
import ru.dvinni.davito.models.Type;
import ru.dvinni.davito.models.User;
import ru.dvinni.davito.repositories.ItemRepository;
import ru.dvinni.davito.repositories.TypeRepository;
import ru.dvinni.davito.repositories.UserRepository;
import ru.dvinni.davito.services.AuthService;
import ru.dvinni.davito.services.ItemService;
import ru.dvinni.davito.views.ItemInfoDetailsModelView;
import ru.dvinni.davito.views.ItemInfoModelView;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис предметов.
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final AuthService authService;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final TypeRepository typeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void addItem(AddItemDTO itemDTO) {
        Item item = modelMapper.map(itemDTO, Item.class);
        User owner = authService.getAuthUser();

        item.setOwner(owner);
        item.setItemType(typeRepository.findByItemName(ItemType.PRODUCT).orElseThrow());
        owner.getItems().add(item);

        userRepository.save(owner);
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(String itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    @Transactional
    public void setNewCost(String itemId, BigDecimal newCost) {
        User owner = authService.getAuthUser();
        Item item = itemRepository.findItemById(itemId).orElseThrow(() -> new RuntimeException("Предмет не найден"));

        if (!owner.getItems().contains(item)) {
            throw new RuntimeException("Пользователь не является владельцем предмета");
        }

        item.setCost(newCost);

        itemRepository.save(item);
    }

    @Override
    public List<ItemInfoModelView> getAllItems() {
        return itemRepository.findAll().stream()
                .map(item -> modelMapper.map(item, ItemInfoModelView.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemInfoModelView> getAllProductItems() {
        Type type = typeRepository.findByItemName(ItemType.PRODUCT).orElseThrow();
        return itemRepository.findItemByItemType(type).stream()
                .map(item -> modelMapper.map(item, ItemInfoModelView.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemInfoModelView> getAllProductItemsToOwner() {
        Type type = typeRepository.findByItemName(ItemType.PRODUCT).orElseThrow();
        User owner = authService.getAuthUser();
        return itemRepository.findItemByOwnerAndItemType(owner, type).stream()
                .map(item -> modelMapper.map(item, ItemInfoModelView.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemInfoModelView> getAllOwnershipItems() {
        Type type = typeRepository.findByItemName(ItemType.OWNERSHIP).orElseThrow();
        User owner = authService.getAuthUser();
        return itemRepository.findItemByOwnerAndItemType(owner, type).stream()
                .map(item -> modelMapper.map(item, ItemInfoModelView.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemInfoDetailsModelView getItem(String itemId) {
        return modelMapper.map(itemRepository.findItemById(itemId), ItemInfoDetailsModelView.class);
    }
}
