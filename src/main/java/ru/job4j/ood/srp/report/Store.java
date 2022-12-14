package ru.job4j.ood.srp.report;

import java.util.List;
import java.util.function.Predicate;

/**
 * Отчеты
 *
 * Задание:
 * В компании появилась необходимость в генерации отчетов.
 * Есть база данных со всеми сотрудниками компании.
 * 1.Сотрудники описываются моделью Employee
 * 2.Доступ к базе данных осуществляется через интерфейс Store
 * 3.В компании есть три департамента: бухгалтерия, программисты, HR. Всем департаментам нужны отчеты.
 * Программисты реализовали для них через интерфейс Report единую систему отчетов - ReportEngine
 *
 * ТЗ:
 * 1.Через месяц применения системы отчетности отдел программистов потребовал отчеты в виде html
 * (отчет нужно изобразить с помощью строк. Форматтеры использовать не нужно).
 * 2.Отдел бухгалтерии попросил изменить вид зарплаты.
 * 3.Отдел HR попросил выводить сотрудников в порядке убывания зарплаты и убрать поля даты найма и увольнения.
 *
 * Решение:
 * 1. Создадим 3 отдельных класса для каждого департамента: AccountingReport, ItReport и HrReport
 * 2. Реализуем в каждом классе метод generate со своей логикой согласно заданию
 *
 * @author Alex_life
 * @version 2.0
 * добавил поддержку форматов XML и JSON
 * @since 27.09.2022
 */
public interface Store {

    void add(Employee em);

    List<Employee> findBy(Predicate<Employee> filter);
}
