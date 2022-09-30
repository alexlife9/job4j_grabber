package ru.job4j.ood.lsp.controlfood;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;

/**
 * Хранилище продуктов
 *
 * Класс Food описывает продукты
 *
 * @author Alex_life
 * @version 2.0
 * Заменил на LocalDate для более наглядного отображения разбежки сроков годности в тестах
 * @since 30.09.2022
 */
public abstract class Food {
    private final String name;
    private final LocalDate expiryDate;
    private final LocalDate createDate;
    private double price; /* цена будет меняться с учетом скидки, поэтому не финал */
    private final int discount; /* процент скидки на товар */

    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0
                && discount == food.discount
                && Objects.equals(name, food.name)
                && Objects.equals(expiryDate, food.expiryDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price, discount);
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", expiryDate=" + expiryDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
