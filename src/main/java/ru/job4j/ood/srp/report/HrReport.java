package ru.job4j.ood.srp.report;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Отчеты
 *
 * смотри Store
 *
 * @author Alex_life
 * @version 1.0
 * @since 24.09.2022
 */
public class HrReport implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private final Store store;

    public HrReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : salaryDesc(store.findBy(filter))) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }


    public List<Employee> salaryDesc(List<Employee> employees) {
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        return employees;

    }
}
