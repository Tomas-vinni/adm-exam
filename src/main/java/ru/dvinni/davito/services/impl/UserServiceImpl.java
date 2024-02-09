package ru.dvinni.davito.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dvinni.davito.dto.AddUserDTO;
import ru.dvinni.davito.models.Item;
import ru.dvinni.davito.models.ItemType;
import ru.dvinni.davito.models.User;
import ru.dvinni.davito.repositories.ItemRepository;
import ru.dvinni.davito.repositories.TypeRepository;
import ru.dvinni.davito.repositories.UserRepository;
import ru.dvinni.davito.services.AuthService;
import ru.dvinni.davito.services.UserService;
import ru.dvinni.davito.views.UserDetailsInfoModelView;
import ru.dvinni.davito.views.UserInfoModelView;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис пользователей.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;
    private final TypeRepository typeRepository;

    @Override
    public void saveUser(AddUserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
    }

    @Override
    public List<UserInfoModelView> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserInfoModelView.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailsInfoModelView getUser(String username) {
        return modelMapper.map(userRepository.findUserByUsername(username), UserDetailsInfoModelView.class);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteUserByUsername(username);
    }

    @Override
    @Transactional
    public void sellItem(String itemId) {
        User sellerUser = authService.getAuthUser();
        Item item = itemRepository.findItemById(itemId).orElseThrow(() -> new RuntimeException("Предмет не найден"));
        if (sellerUser.getItems().contains(item)) {
            item.getItemType().setItemName(ItemType.PRODUCT);
            itemRepository.save(item);
            userRepository.save(sellerUser);
        } else
            throw new RuntimeException("Пользователь не является владельцем предмета");
    }

    @Override
    @Transactional
    public void buyItem(String itemId) {

        User buyerUser = authService.getAuthUser();
        Item item = itemRepository.findItemById(itemId).orElseThrow(() -> new RuntimeException("Предмет не найден"));
        User ownerUser = item.getOwner();

        if (buyerUser.getMoney().subtract(item.getCost()).compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("No money =(");
        }

        item.setOwner(buyerUser);
        item.setItemType(typeRepository.findByItemName(ItemType.OWNERSHIP).orElseThrow());
        buyerUser.setMoney(buyerUser.getMoney().subtract(item.getCost()));
        buyerUser.getItems().add(item);
        ownerUser.setMoney(ownerUser.getMoney().add(item.getCost()));
        ownerUser.getItems().remove(item);

        userRepository.save(buyerUser);
        userRepository.save(ownerUser);
        itemRepository.save(item);
    }

    @Override
    public void depositAccount(BigDecimal money) {
        User user = authService.getAuthUser();

        if (money.doubleValue() < 0) {
            throw new RuntimeException("Неправильная сумма пополнения");
        }

        user.setMoney(user.getMoney().add(money));

        userRepository.save(user);
    }
}
