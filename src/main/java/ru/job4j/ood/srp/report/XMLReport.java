package ru.job4j.ood.srp.report;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
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
public class XMLReport implements Report {

    private final Store store;

    public XMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var emp = new Employee(store.findBy(filter));
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(emp, writer);
                xml = writer.getBuffer().toString();
            }
        }  catch (IOException | JAXBException ex) {
            ex.printStackTrace();
        }
        return xml;
    }
}
