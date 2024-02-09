package ru.dvinni.davito.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Тип предмета
 */
@Entity
@Table(name = "types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Type extends BaseEntity {
    private ItemType itemName;

    public Type(int type) {
        this.itemName = ItemType.fromItemId(type);
    }
}
