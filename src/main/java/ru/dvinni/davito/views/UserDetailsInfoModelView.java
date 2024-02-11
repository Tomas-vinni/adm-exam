package ru.dvinni.davito.views;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dvinni.davito.models.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * View для отображения детальной информации о пользователе
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDetailsInfoModelView {
    private String username;
    private String name;
    private String email;
    private BigDecimal money;
    private List<ItemInfoModelView> ownershipItems;
    private List<ItemInfoModelView> productItems;
}
