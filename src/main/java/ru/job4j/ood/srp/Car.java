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
        if (engine.contains("ДВС")) {
            fuel = "горючее";
        }
    }

    public void color() {
        if (color.contains("розовый")) {
            System.out.println("для блондинки");
        }
    }

    public String getFuel() {
        return fuel;
    }
}
