package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Поиск максимального и минимального элемента по критерию
 *
 * @author Alex_life
 * @version 1.0
 * @since 07.09.2022
 */
class MaxMinTest {
    @Test
    void whenNull() {
        MaxMin maxMin = new MaxMin();
        List<Integer> values = List.of();
        assertThatThrownBy(() -> maxMin.forMax(values, Integer::compare))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Пустое значение");
    }

    @Test
    void whenMax() {
        MaxMin max = new MaxMin();
        List<Integer> values = List.of(9, 2, 5, 4, 1, 7, 6);
        assertThat(max.max(values, Integer::compare)).isEqualTo(9);
    }

    @Test
    void whenMix() {
        MaxMin min = new MaxMin();
        List<Integer> values = List.of(9, 2, 5, 4, 1, 7, 6);
        assertThat(min.min(values, Integer::compare)).isEqualTo(1);
    }

}