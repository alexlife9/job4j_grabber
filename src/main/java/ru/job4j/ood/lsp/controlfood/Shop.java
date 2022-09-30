package ru.job4j.ood.lsp.controlfood;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.controlfood.ConstantFood.*;
import static ru.job4j.ood.lsp.controlfood.Percent.expirationDatePercentage;

/**
 * Хранилище продуктов
 *
 * @author Alex_life
 * @version 2.0
 * @since 30.09.2022
 */
public class Shop implements Store {
    private final List<Food> shopFoodList = new ArrayList<>();

    /* Если остаток срока годности от 25% до 75% - направить в Shop
    * Если остаток срока годности меньше 25% - применить скидку discount в % и направить в Shop */
    @Override
    public boolean checkSort(Food food) {
        if (expirationDatePercentage(food) > PERCENT_DISCOUNT && expirationDatePercentage(food) < PERCENT_FRESH) {
            System.out.println("срок годности: " + expirationDatePercentage(food));
            shopFoodList.add(food);
        }
        if (expirationDatePercentage(food) > PERCENT_TRASH && expirationDatePercentage(food) <= PERCENT_DISCOUNT) {
            food.setPrice(food.getPrice() - (food.getPrice() * food.getDiscount() / PERCENT_FULL_FRESH));
            System.out.println("срок годности меньше 25%: " + expirationDatePercentage(food));
            shopFoodList.add(food);
        }
        return false;
    }

    @Override
    public List<Food> getFoods() {
        return List.copyOf(shopFoodList);
    }
}
