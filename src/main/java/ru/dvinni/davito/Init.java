package ru.dvinni.davito;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.dvinni.davito.models.Item;
import ru.dvinni.davito.models.ItemType;
import ru.dvinni.davito.models.Role;
import ru.dvinni.davito.models.Type;
import ru.dvinni.davito.models.User;
import ru.dvinni.davito.models.UserRoles;
import ru.dvinni.davito.repositories.ItemRepository;
import ru.dvinni.davito.repositories.RoleRepository;
import ru.dvinni.davito.repositories.TypeRepository;
import ru.dvinni.davito.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Инициализация базовых сущностей.
 */
@Component
@RequiredArgsConstructor
public class Init implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TypeRepository typeRepository;
    private final ItemRepository itemRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initTypes();
        initUsers();
    }

    private void initRoles() {
        if (roleRepository.count() == 0) {
            var adminRole = new Role(UserRoles.ADMIN);
            var userRole = new Role(UserRoles.USER);

            roleRepository.save(userRole);
            roleRepository.save(adminRole);
        }
    }

    private void initTypes() {
        if (typeRepository.count() == 0) {
            var ownershipType = new Type(ItemType.OWNERSHIP);
            var productType = new Type(ItemType.PRODUCT);

            typeRepository.save(ownershipType);
            typeRepository.save(productType);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initUser();
        }
    }

    private void initAdmin() {
        var adminRole = roleRepository.findRoleByRoleName(UserRoles.ADMIN).orElseThrow();
        var adminUser = new User("admin", passwordEncoder.encode(defaultPassword), "admin@example.com", "Admin");

        adminUser.setRoles(Set.of(adminRole));
        adminUser.setMoney(new BigDecimal(0));
        userRepository.save(adminUser);
    }

    private void initUser() {
        var userRole = roleRepository.findRoleByRoleName(UserRoles.USER).orElseThrow();

        var seller = new User("seller", passwordEncoder.encode(defaultPassword), "seller@exsample.com", "SellerUser");
        seller.setRoles(Set.of(userRole));
        seller.setMoney(new BigDecimal(500000));
        var buyer =  new User("buyer", passwordEncoder.encode(defaultPassword), "buyer@exsample.com", "BuyerUser");
        buyer.setMoney(new BigDecimal(600000));
        buyer.setRoles(Set.of(userRole));

        var ownershipType = typeRepository.findByItemName(ItemType.OWNERSHIP).orElseThrow();
        var productType = typeRepository.findByItemName(ItemType.PRODUCT).orElseThrow();


        Item item_1 = new Item("Car", new BigDecimal(100500), "super car", productType, seller);
        Item item_2 = new Item("Computer", new BigDecimal(10500), "powered computer", ownershipType, buyer);

        seller.setItems(List.of(item_1));
        buyer.setItems(List.of(item_2));

        userRepository.save(seller);
        userRepository.save(buyer);

        itemRepository.save(item_1);
        itemRepository.save(item_2);

    }
}
