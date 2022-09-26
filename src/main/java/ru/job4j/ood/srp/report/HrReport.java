package ru.job4j.ood.srp.report;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

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
public class HrReport implements Report {

    private final Store store;

    public HrReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(LS);
        for (Employee employee : salaryDesc(store.findBy(filter))) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(LS);
        }
        return text.toString();
    }


    private List<Employee> salaryDesc(List<Employee> employees) {
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        return employees;

    }
}
