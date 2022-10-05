package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Парковка машин
 *
 * смотри Transport
 *
 * @author Alex_life
 * @version 4.0
 * @since 05.10.2022
 */
class GeneralParkingTest {
    Car car1 = new Car("car1");
    Car car2 = new Car("car2");
    Truck truck1 = new Truck("truck1", 3);
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
        assertThat(generalParking.place(car1)).isTrue();
    }

    @Test
    public void generalParking2CarOnTrackP() {
        GeneralParking generalParking = new GeneralParking(0, 2);
        assertThat(generalParking.place(car1)).isTrue();
        assertThat(generalParking.place(car2)).isTrue();
    }

    @Test
    public void generalParkingTrackOnTrackP() {
        GeneralParking generalParking = new GeneralParking(0, 3);
        assertThat(generalParking.place(truck1)).isTrue();
    }

    @Test
    public void generalParkingTrackOnTrackPFalse() {
        GeneralParking generalParking = new GeneralParking(0, 3);
        assertThat(generalParking.place(truck2)).isFalse();
    }

    @Test
    public void generalParkingTrackOnTrackPFalse2() {
        GeneralParking generalParking = new GeneralParking(3, 0);
        assertThat(generalParking.place(truck2)).isFalse();
    }

    @Test
    public void generalParkingTrackOnTrackPTrue() {
        GeneralParking generalParking = new GeneralParking(10, 0);
        assertThat(generalParking.place(truck2)).isTrue();
    }

    @Test
    public void generalParkingTrackOnCarP() {
        GeneralParking generalParking = new GeneralParking(3, 0);
        assertThat(generalParking.place(truck1)).isTrue();
    }

    @Test
    public void generalParking2TrackOnCarP() {
        GeneralParking generalParking = new GeneralParking(13, 0);
        assertThat(generalParking.place(truck1)).isTrue();
        assertThat(generalParking.place(truck2)).isTrue();
    }

    @Test
    public void generalParking2TrackAnd2CarTrue() {
        GeneralParking generalParking = new GeneralParking(2, 13);
        assertThat(generalParking.place(car1)).isTrue();
        assertThat(generalParking.place(car2)).isTrue();
        assertThat(generalParking.place(truck1)).isTrue();
        assertThat(generalParking.place(truck2)).isTrue();
    }

    @Test
    public void generalParking2TrackAnd2CarFalse() {
        GeneralParking generalParking = new GeneralParking(2, 10);
        assertThat(generalParking.place(car1)).isTrue();
        assertThat(generalParking.place(car2)).isTrue();
        assertThat(generalParking.place(truck1)).isTrue();
        assertThat(generalParking.place(truck2)).isFalse();
    }



}