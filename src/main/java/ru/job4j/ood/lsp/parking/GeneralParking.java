package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.parking.Car.SIZE;

/**
 * Парковка машин
 *
 * смотри Transport
 *
 * GeneralParking - общая парковка для легковых и грузовых
 *
 * @author Alex_life
 * @version 7.0
 * @since 09.10.2022
 */
public class GeneralParking implements Parking {
    private final List<Transport> carParkingList; /* список всех легковушек, занявших места на паркинге */
    private final List<Transport> truckParkingList; /* список всех грузовиков, занявших места на паркинге */
    private int amountPlaceCar; /* кол-во свободных мест на парковке для легковушек */
    private int amountPlaceTruck; /* кол-во свободных мест на парковке для грузовиков */

    public GeneralParking(int placeCar, int placeTruck) {
        this.amountPlaceCar = placeCar;
        this.amountPlaceTruck = placeTruck;
        this.carParkingList = new ArrayList<>(amountPlaceCar);
        this.truckParkingList = new ArrayList<>(amountPlaceTruck);
    }

    public boolean validSizeAndAddTransport(Transport tr) {
        int size = tr.getSize();
        if (size == SIZE && amountPlaceCar >= SIZE) { /* если размера тачки = 1 И есть свободные легковые места */
            carParkingList.add(tr);
            amountPlaceCar--; /* уменьшаем кол-во свободных мест */
            return true;
        }
        if (size > SIZE && amountPlaceTruck >= SIZE) { /* если размер тачки > 1 И есть свободные грузовые места */
            truckParkingList.add(tr);
            amountPlaceTruck--;
            return true;
        }
        /* если грузовик не лезет на грузовые места и есть свободные легковые
        * Легковая машина может занять только место, предназначенное для легковой машины */
        if (amountPlaceTruck < SIZE && amountPlaceCar >= size) {
            carParkingList.add(tr);
            amountPlaceCar = amountPlaceCar - size;
            return true;
        }
        return false;
    }

    @Override
    public boolean place(Transport transport) {
        boolean rsl = false;
        if (!condition()) {
            rsl = validSizeAndAddTransport(transport);
        }
        return rsl;
    }

    /**
     * проверяем не заняты ли еще все места на общей парковке
     * @return если мест вообще нет, то вернется true
     */
    @Override
    public boolean condition() {
        return amountPlaceCar < carParkingList.size() && amountPlaceTruck < truckParkingList.size();
    }

    /* возвращаем список всего транспорта находящегося на парковке */
    @Override
    public List<Transport> getAll() {
        List<Transport> all = new ArrayList<>();
        all.addAll(carParkingList);
        all.addAll(truckParkingList);
        return all;
    }
}
