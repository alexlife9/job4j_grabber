package ru.job4j.ood.lsp.parking;

import java.util.List;

/**
 * Парковка машин
 *
 * смотри Transport
 *
 * @author Alex_life
 * @version 2.0
 * @since 02.10.2022
 */
public interface Parking {

    boolean place(Transport transport);

    boolean condition(Transport transport);

    List<Transport> getAll();
}
