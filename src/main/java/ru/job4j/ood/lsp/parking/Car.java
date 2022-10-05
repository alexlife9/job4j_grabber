package ru.job4j.ood.lsp.parking;

/**
 * Парковка машин
 *
 * смотри Transport
 *
 * @author Alex_life
 * @version 4.0
 * @since 05.10.2022
 */
public class Car extends Transport {

    public Car(String name) {
        super(name);
    }

    @Override
    public int getSize() {
        return SIZE;
    }
}
