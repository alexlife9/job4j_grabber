package ru.job4j.map;

import java.util.List;

/**
 * Аттестация учеников
 * Класс Pupil описывает ученика
 * @author Alex_life
 * @version 1.0
 * @since 27.10.2022
 */
public record Pupil(String name, List<Subject> subjects) {
}
