package ru.job4j.ood.lsp.controlfood;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.controlfood.ConstantFood.PERCENT_FRESH;
import static ru.job4j.ood.lsp.controlfood.Percent.expirationDatePercentage;

/**
 * Хранилище продуктов
 *
 * @author Alex_life
 * @version 2.0
 * @since 30.09.2022
 */
public class Warehouse implements Store {
    private final List<Food> warehouseFoodList = new ArrayList<>();

    /* Если остаток срока годности выше 75% - направить в Warehouse */
    @Override
    public boolean checkSort(Food food) {
        if (expirationDatePercentage(food) >= PERCENT_FRESH) {
            System.out.println("на склад! большой срок годности: " + expirationDatePercentage(food));
            warehouseFoodList.add(food);
        }
        return false;
    }

    @Override
    public List<Food> getFoods() {
        return List.copyOf(warehouseFoodList);
    }
}
