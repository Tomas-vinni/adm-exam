package ru.dvinni.davito.views;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * View для отображения детальной информации о предмете.
 */
@Getter
@Setter
@NoArgsConstructor
public class ItemInfoDetailsModelView {
    private String id;
    private String itemName;
    private BigDecimal cost;
    private String description;
    private UserInfoModelView owner;
}
