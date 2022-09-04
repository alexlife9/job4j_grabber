package ru.job4j.gc.prof;

/**
 * Эксперименты с различными GC
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.09.2022
 */
/* Класс, который генерирует массив: */
public interface Data {
    void insert(int elements);

    int[] getClone();
}
