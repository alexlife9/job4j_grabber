package ru.job4j.ood.lsp.controlfood;

import java.util.Calendar;
import java.util.Objects;

/**
 * Хранилище продуктов
 *
 * Класс Food описывает продукты
 *
 * @author Alex_life
 * @version 1.0
 * @since 28.09.2022
 */
public class Food {
    private final String name;
    private final Calendar expiryDate;
    private final Calendar createDate;
    private double price; /* цена будет меняться с учетом скидки, поэтому не финал */
    private final int discount;

    public Food(String name, Calendar expiryDate, Calendar createDate, double price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public Calendar getCreateDate() {
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
