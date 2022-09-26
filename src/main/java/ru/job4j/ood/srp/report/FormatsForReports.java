package ru.job4j.ood.srp.report;

import java.text.SimpleDateFormat;

/**
 * Отчеты
 *
 * смотри Store
 *
 * SimpleDateFormat является подклассом DateFormat, который позволяет форматировать ввод-вывод даты и времени
 * в рамках предопределенных стилей.
 * В отличие от DateFormat, SimpleDateFormat позволяет создавать собственные настраиваемые форматы ввода-вывода.
 *
 * Для создания экземпляра класса SimpleDateFormat используется один из 4 конструкторов:
 *     SimpleDateFormat()
 *     SimpleDateFormat(String pattern)
 *     SimpleDateFormat(String pattern, DateFormatSymbols formatSymbols)
 *     SimpleDateFormat(String pattern, Locale locale)
 *
 * @author Alex_life
 * @version 1.0
 * @since 26.09.2022
 */
public class FormatsForReports {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    public static final String LS = System.lineSeparator();
}
