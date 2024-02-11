package ru.dvinni.davito.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Пользователь.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseUser {

    @OneToMany(mappedBy = "owner")
    private List<Item> items;

    @Column(name = "money", nullable = false)
    private BigDecimal money;

    public User(String username,
                String password,
                String email,
                String name) {
        super(username, password, email, name, new HashSet<>());
        this.items = new ArrayList<>();
        this.money = new BigDecimal(0);
    }
}
