package ru.job4j.ood.srp.report;

import java.text.SimpleDateFormat;
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
public class ItReport implements Report  {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private final Store store;

    public ItReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>ItReport</title>")
                .append("</head>")
                .append("<body>")
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        text.append("</body>")
                .append("</html>");
        return text.toString();
    }
}
