package ru.dvinni.davito.models;

import lombok.Getter;

/**
 * Тип предмета торговли.
 */
@Getter
public enum ItemType {

    OWNERSHIP(0),
    PRODUCT(1);

    private final int itemId;

    ItemType(int itemId) {
        this.itemId = itemId;
    }

    public static ItemType fromItemId(int itemId) {
        return ItemType.values()[itemId];
    }
}
