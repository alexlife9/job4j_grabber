package ru.job4j.ood.lsp.controlfood;

import java.util.Calendar;

/**
 * Хранилище продуктов
 *
 * @author Alex_life
 * @version 1.0
 * @since 28.09.2022
 */
public class Meat extends Food {
    public Meat(String name, Calendar expiryDate, Calendar createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
