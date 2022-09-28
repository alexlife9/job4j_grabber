package ru.job4j.ood.lsp.controlfood;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.controlfood.ControlQuality.PERCENT_TRASH;

/**
 * Хранилище продуктов
 *
 * @author Alex_life
 * @version 1.0
 * @since 28.09.2022
 */
public class Trash implements Store {
    List<Food> trashFoodList = new ArrayList<>();

    /* Если срок годности вышел (=0%), то отправить продукт в мусорку.*/
    @Override
    public void checkSort(Food food) {
        if (expirationDatePercentage(food) <= PERCENT_TRASH) {
            System.out.println("продукт сдох! срок годности: " + expirationDatePercentage(food));
            trashFoodList.add(food);
        }
    }

    @Override
    public List<Food> getFoods() {
        return List.copyOf(trashFoodList);
    }
}
