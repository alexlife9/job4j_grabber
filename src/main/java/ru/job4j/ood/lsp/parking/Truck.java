package ru.job4j.ood.lsp.parking;

/**
 * Парковка машин
 *
 * смотри Transport
 *
 * @author Alex_life
 * @version 3.0
 * @since 04.10.2022
 */
public class Truck extends Transport {

    public Truck(String name, int sizePlace) {
        super(name, sizePlace);
    }

/*    public void validSize(int size) {
        if (size <= SIZE_CAR) {
            throw new IllegalArgumentException("Парковочное место не подходит для грузовой машины");
        }
    }*/
}
