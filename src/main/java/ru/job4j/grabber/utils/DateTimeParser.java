package ru.job4j.grabber.utils;

import java.time.LocalDateTime;

/**
 * Преобразование даты
 *
 * Преобразовать исходные данные, заданные  в виде строк,
 * в объекты Java для возможности дальнейшей работы с ними как с временным типом.
 *
 * @author Alex_life
 * @version 1.0
 * @since 02.09.2022
 */
public interface DateTimeParser {
    LocalDateTime parse(String parse);
}
