package ru.job4j.map;

/**
 * Аттестация учеников
 * Класс Label содержит результаты: имя и баллы. Этот класс используется как для учеников, так и для предметов
 * @author Alex_life
 * @version 1.0
 * @since 27.10.2022
 */
public record Label(String name, double score) implements Comparable<Label> {
    @Override
    public int compareTo(Label o) {
        return Double.compare(this.score, o.score);
    }
}
