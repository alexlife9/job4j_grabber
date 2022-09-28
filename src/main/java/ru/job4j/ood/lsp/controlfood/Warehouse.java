package ru.job4j.ood.lsp.controlfood;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.controlfood.ControlQuality.PERCENT_FRESH;

/**
 * Хранилище продуктов
 *
 * @author Alex_life
 * @version 1.0
 * @since 28.09.2022
 */
public class Warehouse implements Store {
    List<Food> warehouseFoodList = new ArrayList<>();

    /* Если срок годности израсходован меньше чем на 25% - направить в Warehouse */
    @Override
    public void checkSort(Food food) {
        if (expirationDatePercentage(food) >= PERCENT_FRESH) {
            System.out.println("на склад! большой срок годности: " + expirationDatePercentage(food));
            warehouseFoodList.add(food);
        }
    }

    @Override
    public List<Food> getFoods() {
        return List.copyOf(warehouseFoodList);
    }
}
