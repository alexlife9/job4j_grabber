package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Парковка машин
 *
 * смотри Transport
 *
 * @author Alex_life
 * @version 7.0
 * @since 09.10.2022
 */
class GeneralParkingTest {
    Car car1 = new Car("car1");
    Car car2 = new Car("car2");
    Truck truck1 = new Truck("truck1", 2);
    Truck truck2 = new Truck("truck2", 10);

    @Test
    public void generalParkingCarOnCarP() {
        GeneralParking generalParking = new GeneralParking(1, 0);
        assertThat(generalParking.place(car1)).isTrue();
    }

    @Test
    public void generalParking2CaOnCarP() {
        GeneralParking generalParking = new GeneralParking(2, 0);
        assertThat(generalParking.place(car1)).isTrue();
        assertThat(generalParking.place(car2)).isTrue();
    }

    @Test
    public void generalParkingCarOnTrackP() {
        GeneralParking generalParking = new GeneralParking(0, 1);
        assertThat(generalParking.place(car1)).isFalse();
    }

    @Test
    public void generalParking2CarOnTrackP() {
        GeneralParking generalParking = new GeneralParking(1, 1);
        assertThat(generalParking.place(car1)).isTrue();
        assertThat(generalParking.place(car2)).isFalse();
    }

    @Test
    public void generalParkingTrackOnTrackP() {
        GeneralParking generalParking = new GeneralParking(0, 1);
        assertThat(generalParking.place(truck1)).isTrue();
    }

    @Test
    public void generalParkingTrackOnTrackPAndCarP() {
        GeneralParking generalParking = new GeneralParking(2, 1);
        assertThat(generalParking.place(truck2)).isTrue();
        assertThat(generalParking.place(truck1)).isTrue();
    }

    @Test
    public void generalParking2TrackOnTrackP() {
        GeneralParking generalParking = new GeneralParking(0, 2);
        assertThat(generalParking.place(truck1)).isTrue();
        assertThat(generalParking.place(truck2)).isTrue();
    }

    @Test
    public void generalParking2TrackOnCarP() {
        GeneralParking generalParking = new GeneralParking(13, 0);
        assertThat(generalParking.place(truck1)).isTrue();
        assertThat(generalParking.place(truck2)).isTrue();
    }

    @Test
    public void generalParking2TrackAnd2CarTrue() {
        GeneralParking generalParking = new GeneralParking(2, 2);
        assertThat(generalParking.place(car1)).isTrue();
        assertThat(generalParking.place(car2)).isTrue();
        assertThat(generalParking.place(truck1)).isTrue();
        assertThat(generalParking.place(truck2)).isTrue();
    }
}