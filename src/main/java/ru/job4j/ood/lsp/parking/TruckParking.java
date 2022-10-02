package ru.job4j.ood.lsp.parking;

import java.util.List;

/**
 * Парковка машин
 *
 * смотри Transport
 *
 * TruckParking парковка только для грузовых - эту реализацию развивать не будем
 *
 * @author Alex_life
 * @version 2.0
 * @since 02.10.2022
 */
public class TruckParking implements Parking {

    @Override
    public boolean place(Transport transport) {
        return false;
    }

    @Override
    public boolean condition(Transport transport) {
        return false;
    }

    @Override
    public List<Transport> getAll() {
        return null;
    }
}
