package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.function.Predicate;

/**
 * Отчеты
 *
 * смотри Store
 *
 * @author Alex_life
 * @version 2.0
 * @since 30.09.2022
 */
public class JSONReport implements Report {

    private Gson gson;
    private final Store store;

    public JSONReport(Store store) {
        this.store = store;
        try {
            this.gson = new GsonBuilder().create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
