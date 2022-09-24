package ru.job4j.ood.srp;

import java.util.List;

/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип единственной ответственности - Single Responsibility Principle
 *
 * смотри SequenceGenerator
 *
 * @author Alex_life
 * @version 1.0
 * @since 24.09.2022
 */
public interface SequenceGeneratorSRP<T> {
    List<T> generate(int size);
}
