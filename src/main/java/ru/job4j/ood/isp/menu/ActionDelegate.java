package ru.job4j.ood.isp.menu;

/**
 * Создать меню
 *
 * смотри Menu
 *
 * 1) Action.
 * Ничего конкретного о действиях мы знать не можем, поэтому наши действия будут представлять собой делегаты.
 * По сути, делегат будет просто вызывать клиентскую логику. Что в ней будет нас не касается.
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.10.2022
 */
public interface ActionDelegate {

    void delegate();

}
