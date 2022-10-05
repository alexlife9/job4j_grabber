package ru.job4j.ood.lsp.parking;

import static ru.job4j.ood.lsp.parking.ConstantForParking.SIZE;

/**
 * Парковка машин
 *
 * смотри Transport
 *
 * @author Alex_life
 * @version 5.0
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
