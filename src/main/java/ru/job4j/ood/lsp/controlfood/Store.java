package ru.job4j.ood.lsp.controlfood;

import java.util.List;

/**
 * Хранилище продуктов
 * Интерфейс Store содержит три метода:
 * 1. checkSort - проверяет срок годности еды для решения куда распределить продукт.
 * Конкретные реализации прописываем в классах-наследниках
 * 2. expirationDatePercentage - вычисление остатка срока годности в процентах - Вынес в отдельный класс
 * 3. возвращает список добавленных продуктов
 *
 * @author Alex_life
 * @version 3.0
 * @since 17.10.2022
 */
public interface Store {

    default boolean checkSort(Food food) {
        return false;
    }

    List<Food> getFoods();

    void clear();
}
