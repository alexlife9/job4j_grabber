package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
public class HabrCareerDateTimeParser implements DateTimeParser {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public LocalDateTime parse(String parse) {

        return LocalDateTime.parse(parse, DATE_TIME_FORMATTER);
    }
}
