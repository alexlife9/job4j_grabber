package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.ood.srp.report.FormatsForReports.DATE_FORMAT;
import static ru.job4j.ood.srp.report.FormatsForReports.LS;

/**
 * Отчеты
 *
 * смотри Store
 *
 * @author Alex_life
 * @version 3.0
 * @since 30.09.2022
 */

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(LS)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(LS);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenAccountingReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new AccountingReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(LS)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary() / AccountingReport.EURO).append(";")
                .append(LS);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenItReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ItReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>ItReport</title>")
                .append("</head>")
                .append("<body>")
                .append("Name; Hired; Fired; Salary;")
                .append(LS)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(LS)
                .append("</body>")
                .append("</html>");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHrReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Stepan", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        Report engine = new HrReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(LS)
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(LS)
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(LS);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenJSONReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Gson gson = new GsonBuilder().create();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new JSONReport(store);
        StringBuilder expect = new StringBuilder()
                .append("[{")
                .append("\"name\":").append(gson.toJson(worker.getName())).append(",")
                .append("\"hired\":").append(gson.toJson(worker.getHired())).append(",")
                .append("\"fired\":").append(gson.toJson(worker.getFired())).append(",")
                .append("\"salary\":").append(gson.toJson(worker.getSalary()))
                .append("}]");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenXMLReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new XMLReport(store);
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String expect = String.format("""
                           <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                           <employees>
                               <employees>
                                   <fired>%s</fired>
                                   <hired>%s</hired>
                                   <name>%s</name>
                                   <salary>%s</salary>
                               </employees>
                           </employees>
                           """,
                date.format(worker.getHired().getTime()),
                date.format(worker.getFired().getTime()),
                worker.getName(),
                worker.getSalary());
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}