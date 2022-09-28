package ru.job4j.ood.lsp.controlfood;

import java.util.Calendar;

/**
 * @author Alex_life
 * @version 1.0
 * @since 28.09.2022
 */
public class Fish extends Food {
    public Fish(String name, Calendar expiryDate, Calendar createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
