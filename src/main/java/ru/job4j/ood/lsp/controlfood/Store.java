package ru.job4j.ood.lsp.controlfood;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Хранилище продуктов
 * Интерфейс Store содержит три метода:
 * 1. checkSort - проверяет срок годности еды для решения куда распределить продукт.
 * Конкретные реализации прописываем в классах-наследниках
 * 2. expirationDatePercentage - вычисление остатка срока годности в процентах
 * 3. возвращает список добавленных продуктов
 *
 * @author Alex_life
 * @version 1.0
 * @since 28.09.2022
 */
public interface Store {

    void checkSort(Food food);

    default double expirationDatePercentage(Food food) {
        Date currentDate = Calendar.getInstance().getTime();
        Date expirationDate = food.getExpiryDate().getTime();
        Date createDate = food.getCreateDate().getTime();
        return ((expirationDate.getTime() - currentDate.getTime()) * 100.0)
                / (expirationDate.getTime() - createDate.getTime());
    };

    List<Food> getFoods();
}
