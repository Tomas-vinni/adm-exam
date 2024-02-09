package ru.dvinni.davito.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.dvinni.davito.models.Type;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) для добавления предмета.
 */
@Getter
@Setter
@NoArgsConstructor
public class AddItemDTO {

    @Size(min = 3, max = 255, message = "В имени должно быть от 3 до 255 символов.")
    private String itemName;

    @Min(value = 0)
    private BigDecimal cost;
    private String description;
}
