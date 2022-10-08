package ru.job4j.ood.lsp.parking;

/**
 * Парковка машин
 *
 * смотри Transport
 *
 * @author Alex_life
 * @version 6.0
 * @since 09.10.2022
 */
public class Car extends Transport {
    public static final int SIZE = 1;

    public Car(String name) {
        super(name);
    }

    @Override
    public int getSize() {
        return SIZE;
    }
}
