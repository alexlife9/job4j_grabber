package ru.job4j.ood.isp;

/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип разделения интерфейсов - Interface Segregation Principle
 *
 * смотри Figure
 *
 * Пример 4.
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.10.2022
 */
public interface Transport {
    boolean fly(); /* умеет летать */
    boolean swim(); /* умеет плавать */
    boolean ride(); /* умеет ездить */

    int loadCapacity(); /* грузоподъемность */
    void fuelConsumption(); /* потребление топлива */
}

/* очевидно что при реализации Самолета - ему не нужен метод "летать"
* либо при реализации велосипеда вообще нужен будет только метод ride */
