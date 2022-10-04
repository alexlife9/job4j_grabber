package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Alex_life
 * @version 1.0
 * @since 04.10.2022
 */
class GeneralParkingTest {

    @Test
    public void generalParking() {
        GeneralParking generalParking = new GeneralParking(2, 5);
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        Truck truck1 = new Truck("truck1", 2);
        Truck truck2 = new Truck("truck2", 3);
        assertThat(generalParking.place(car1)).isTrue();
        assertThat(generalParking.place(car2)).isTrue();
        assertThat(generalParking.place(truck1)).isTrue();
        assertThat(generalParking.place(truck2)).isTrue();
    }


}