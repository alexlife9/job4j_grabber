package ru.job4j.ood.lsp.controlfood;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Хранилище продуктов
 *
 * Вынес реализацию вычисления срока годности в процентах в отдельный класс, так как логика может в будущем поменяться
 * Заменил на LocalDate для более наглядного отображения разбежки сроков годности в тестах
 * @author Alex_life
 * @version 1.0
 * @since 30.09.2022
 */
public class Percent {
    public static double expirationDatePercentage(Food food) {
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = food.getExpiryDate();
        LocalDate createDate = food.getCreateDate();
        return (DAYS.between(expirationDate, currentDate) * 100.0)
                / (DAYS.between(expirationDate, createDate));
    }
}
