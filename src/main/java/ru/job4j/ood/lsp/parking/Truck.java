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
public class Truck extends Transport {

    public Truck(String name, int sizePlace) {
        super(name, sizePlace);
        validSize(sizePlace);
    }

    public void validSize(int size) {
        if (size <= SIZE) {
            throw new IllegalArgumentException("Парковочное место не подходит для грузовой машины");
        }
    }
}
