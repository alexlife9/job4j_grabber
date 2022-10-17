package ru.job4j.ood.lsp.controlfood;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.controlfood.ConstantFood.PERCENT_TRASH;
import static ru.job4j.ood.lsp.controlfood.Percent.expirationDatePercentage;

/**
 * Хранилище продуктов
 *
 * @author Alex_life
 * @version 3.0
 * @since 17.10.2022
 */
public class Trash implements Store {
    private final List<Food> trashFoodList = new ArrayList<>();

    /* Если срок годности вышел (=0%), то отправить продукт в мусорку.*/
    @Override
    public boolean checkSort(Food food) {
        if (expirationDatePercentage(food) <= PERCENT_TRASH) {
            System.out.println("продукт сдох! срок годности: " + expirationDatePercentage(food));
            trashFoodList.add(food);
        }
        return false;
    }

    @Override
    public List<Food> getFoods() {
        return List.copyOf(trashFoodList);
    }

    @Override
    public void clear() {
        trashFoodList.clear();
    }
}
