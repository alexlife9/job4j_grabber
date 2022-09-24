package ru.job4j.ood.srp.report;

import java.util.function.Predicate;

/**
 * Отчеты
 *
 * смотри Store
 *
 * @author Alex_life
 * @version 1.0
 * @since 24.09.2022
 */
public interface Report {
    String generate(Predicate<Employee> filter);
}
