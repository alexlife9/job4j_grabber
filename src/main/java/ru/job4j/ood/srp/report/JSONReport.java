package ru.job4j.ood.srp.report;

import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

/**
 * Отчеты
 *
 * смотри Store
 *
 * @author Alex_life
 * @version 1.0
 * @since 27.09.2022
 */
public class JSONReport implements Report {

    private final Store store;

    public JSONReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var lib = new GsonBuilder().create();
        return lib.toJson(store.findBy(filter));
    }
}
