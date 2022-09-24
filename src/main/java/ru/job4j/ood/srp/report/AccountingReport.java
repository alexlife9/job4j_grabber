package ru.job4j.ood.srp.report;

import java.util.function.Predicate;

import static ru.job4j.ood.srp.report.ReportEngine.DATE_FORMAT;

/**
 * Отчеты
 *
 * смотри Store
 *
 * @author Alex_life
 * @version 1.0
 * @since 24.09.2022
 */
public class AccountingReport implements Report  {

    private final Store store;

    public AccountingReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(salaryCalculation(employee)).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public static double salaryCalculation(Employee employee) {
        double euro = 100.0;
        return employee.getSalary() * euro;
    }
}
