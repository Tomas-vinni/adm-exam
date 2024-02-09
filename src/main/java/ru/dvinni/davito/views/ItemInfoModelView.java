package ru.dvinni.davito.views;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * View для отображения информации о предмете.
 */
@Getter
@Setter
@NoArgsConstructor
public class ItemInfoModelView {
    private String id;
    private String itemName;
    private BigDecimal cost;
}
