package ru.job4j.ood.lsp.parking;

import java.util.List;

/**
 * Парковка машин
 *
 * смотри Transport
 *
 * @author Alex_life
 * @version 3.0
 * @since 04.10.2022
 */
public interface Parking {

    boolean place(Transport transport); /* метод добавляет транспорт и занимает место на парковке */

    boolean condition(); /* метод определяет остались ли свободные места */

    List<Transport> getAll(); /* метод возвращает все занятые места на парковке */
}
