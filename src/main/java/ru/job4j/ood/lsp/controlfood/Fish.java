package ru.job4j.ood.lsp.controlfood;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * @author Alex_life
 * @version 2.0
 * @since 30.09.2022
 */
public class Fish extends Food {
    public Fish(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
