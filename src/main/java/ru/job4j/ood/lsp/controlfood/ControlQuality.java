package ru.job4j.ood.lsp.controlfood;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранилище продуктов
 *
 * Класс ControlQuality - это обработчик перераспределения продуктов в место использования через Store.
 *
 * Класс должен перераспределять еду по хранилищам в зависимости от условий:
 * 1. Если срок годности израсходован меньше чем на 25% направить в Warehouse;
 * 2. Если срок годности от 25% до 75% - направить в Shop;
 * 3. Если срок годности меньше 25% то выставить новую цену на продукт с учетом скидки и отправить в Shop;
 * 4. Если срок годности вышел, то отправить продукт в мусорку.
 *
 * @author Alex_life
 * @version 3.0
 * @since 17.10.2022
 */
public class ControlQuality {
    List<Store> storage;

    public ControlQuality(List<Store> storeList) {
        this.storage = storeList;
    }

    /**
     * метод distribution добавляет поступающие продукты в storage на дальнейшее распределение в интерфейс Store,
     * реализации которого уже и занимаются определение куда добавить продукты в зависимости от срока.
     * после добавления продукта останавливаем цикл
     * @param food поступающий продукт на распределение
     */
    public void distribution(Food food) {
        for (Store store : storage) {
            if (store.checkSort(food)) {
                break;
            }
        }
    }

    public void resort() {
        List<Food> tempFood = new ArrayList<>();
        for (Store stores : storage) {
            List<Food> temp =  stores.getFoods();
            tempFood.addAll(temp);
            stores.clear();
        }
        for (Food food : tempFood) {
            distribution(food);
        }

    }
}
