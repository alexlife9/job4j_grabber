package ru.job4j.ood.lsp.parking;

/**
 * Парковка машин
 *
 * ТЗ:
 * Существует общая парковка для грузовых и легковых машин.
 * Количество парковочных мест для каждого типа машин задается на этапе создания парковки.
 *
 * Легковая машина может занять только место, предназначенное для легковой машины.
 * Грузовая машина может разместиться на месте, предназначенном для грузовых машин,
 * либо на N парковочных мест для легковых машин, стоящих рядом.
 * Важно! Легковой считается машина у которой размер равен 1, а грузовой у которой размер > 1.
 * Необходимо разработать сервис для учета парковки машин.
 *
 * Реализация:
 * 1. Делаем интерфейс Parking c методами без реализации
 * 2. Делаем модель Transport с описанием транспорта
 * 3. Реализуем класс GeneralParking от интерфейса Parking
 * 4. Делаем две реализации Transport - Car и Truck
 * 5. Шириной парковочного места пренебрегаем.
 *
 * @author Alex_life
 * @version 3.0
 * @since 04.10.2022
 */
public abstract class Transport {
    private String name;
    public static int sizePlace; /* размер места конкретного транспорта для парковки */

    public static final int SIZE_CAR = 1; /* По умолчанию для легковых = 1. Условно это площадь, равная 5м х 3м */
    public static final int SIZE_TRUCK_MAX = 4; /* максимальный размер места для грузовика = 4. Площадь = 20м х 3м */

    public Transport(String name, int sizePlace) {
        this.name = name;
        this.sizePlace = sizePlace;
    }

    public Transport(String name) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return sizePlace;
    }

    public void setSize(int size) {
        this.sizePlace = size;
    }
}
