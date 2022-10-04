package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.parking.Transport.SIZE_CAR;
import static ru.job4j.ood.lsp.parking.Transport.SIZE_TRUCK_MAX;

/**
 * Парковка машин
 *
 * смотри Transport
 *
 * GeneralParking - общая парковка для легковых и грузовых
 *
 * @author Alex_life
 * @version 3.0
 * @since 04.10.2022
 */
public class GeneralParking implements Parking {

    List<Transport> carParkingList = new ArrayList<>(); /* список всех легковушек, занявших места на паркинге */
    List<Transport> truckParkingList = new ArrayList<>(); /* список всех грузовиков, занявших места на паркинге */
    int amountPlaceCar; /* кол-во свободных мест на парковке для легковушек */
    int amountPlaceTruck; /* кол-во свободных мест на парковке для грузовиков */

    public GeneralParking(int placeCar, int placeTruck) {
        this.amountPlaceCar = placeCar;
        this.amountPlaceTruck = placeTruck;
    }

    public void validSizeTransport(Transport tr) {
        if (SIZE_CAR == Transport.sizePlace) {
            carParkingList.add(tr);
            amountPlaceCar--; /* уменьшаем кол-во свободных мест */
        }
        if (SIZE_TRUCK_MAX == Transport.sizePlace) {
            truckParkingList.add(tr);
            amountPlaceTruck--; /* уменьшаем кол-во свободных мест */
        }
        /* тут написать дальнейшую логику чтобы грузовик занимал несколько легковых мест
        и несколько легковых одно место предназначенное для грузовика */
    }

    @Override
    public boolean place(Transport transport) {
        if (condition()) {
            validSizeTransport(transport);
        }
        return false;
    }

    /* проверяем не заняты ли еще все места на общей парковке */
    @Override
    public boolean condition() {
        return carParkingList.size() != amountPlaceCar && truckParkingList.size() != amountPlaceTruck;
    }

    /* возвращаем список всего транспорта находящегося на парковке */
    @Override
    public List<Transport> getAll() {
        List<Transport> all = new ArrayList<>();
        all.add((Transport) carParkingList);
        all.add((Transport) truckParkingList);
        return all;
    }
}
