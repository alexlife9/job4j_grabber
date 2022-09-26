package ru.job4j.ood.srp.report;

import java.util.function.Predicate;

import static ru.job4j.ood.srp.report.FormatsForReports.DATE_FORMAT;
import static ru.job4j.ood.srp.report.FormatsForReports.LS;

/**
 * Отчеты
 *
 * смотри Store
 *
 * @author Alex_life
 * @version 2.0
 * @since 26.09.2022
 */
public class AccountingReport implements Report  {

    private final Store store;

    public static final double EURO = 100.0;

    public AccountingReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(LS);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary() / EURO).append(";") /* перевели зарплату из рублей в евро*/
                    .append(LS);
        }
        return text.toString();
    }
}
