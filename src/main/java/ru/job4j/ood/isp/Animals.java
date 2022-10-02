package ru.job4j.ood.isp;

/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип разделения интерфейсов - Interface Segregation Principle
 *
 * смотри Figure
 *
 * Пример 5.
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.10.2022
 */
public interface Animals {
    boolean mammals(); /* млекопитающие */
    boolean bipeds(); /* двуногие */
    boolean arthropods(); /* членистоногие */
    boolean fish(); /* рыбы */
    boolean bird(); /* птицы */
    void run(); /* умеет бегать */
    void fly(); /* умеет летать */
}

/* тут смешались в кучу "кони, люди и вода". Очевидно надо разделять интерфейсы по логике действия методов */