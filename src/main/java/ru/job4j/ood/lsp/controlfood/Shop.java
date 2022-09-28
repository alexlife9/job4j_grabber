package ru.job4j.ood.lsp.controlfood;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.controlfood.ControlQuality.PERCENT_DISCOUNT;
import static ru.job4j.ood.lsp.controlfood.ControlQuality.PERCENT_FRESH;

/**
 * Хранилище продуктов
 *
 * @author Alex_life
 * @version 1.0
 * @since 28.09.2022
 */
public class Shop implements Store {
    List<Food> shopFoodList = new ArrayList<>();

    @Override
    public void checkSort(Food food) {
        if (expirationDatePercentage(food) <= PERCENT_FRESH && expirationDatePercentage(food) >= PERCENT_DISCOUNT) {
            System.out.println("срок годности: " + expirationDatePercentage(food));
            shopFoodList.add(food);
        }
        if (expirationDatePercentage(food) <= PERCENT_DISCOUNT && expirationDatePercentage(food) > 0) {
            food.setPrice(food.getDiscount());
            System.out.println("срок годности меньше 25%: " + expirationDatePercentage(food));
            shopFoodList.add(food);
        }
    }

    @Override
    public List<Food> getFoods() {
        return List.copyOf(shopFoodList);
    }
}
