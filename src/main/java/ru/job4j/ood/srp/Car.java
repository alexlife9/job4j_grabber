package ru.job4j.ood.srp;

/**
 * Пример #1 нарушения принципа SRP
 * 1. а если тип двигателя будет другой?
 * 2. а если цвет будет другой?
 *
 * @author Alex_life
 * @version 1.0
 * @since 24.09.2022
 */
public class Car {
    private String engine;
    private String fuel;
    private String color;

    public void typeFuel() {
    }

    public void color() {

    }

    public String getFuel() {
        return fuel;
    }
}
