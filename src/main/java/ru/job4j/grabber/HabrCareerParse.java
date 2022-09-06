package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

/**
 * Парсинг HTML страницы
 *
 * По техническому заданию получаем данные с сайта - https:\\career.habr.com/vacancies/java_developer
 *
 * @author Alex_life
 * @version 9.0
 * мелкие правки, конструктор заменил
 * @since 06.09.2022
 */
public class HabrCareerParse implements Parse {

    /**
     * Глубина поиска страниц
     * Ссылка на сайт для парсинга
     * Ссылка непосредственно на страницу с вакансиями
     * Парсер времени создания вакансии
     */
    public static final int PAGES = 5;

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);

    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws IOException {
        Parse parserVacancy = new HabrCareerParse(new HabrCareerDateTimeParser());
        List<Post> vacancy = parserVacancy.list(PAGE_LINK);
        for (Post vacancies : vacancy) {
            System.out.println(vacancies);
        }
    }

    /**
     * Метод retrieveDescription извлекает описание вакансии
     * Признак вакансии содержится в теге ".style-ugc". Это можно увидеть исследовав искомую страницу
     * @param link ссылка на вакансию
     * @return описание вакансии
     */
    private String retrieveDescription(String link) {
        try {
            Connection cn = Jsoup.connect(link);           /* коннект к переданному в параметрах урлу */
            Document doc = cn.get();                       /* получаем структуру страницы */
            Element descr = doc.selectFirst(".style-ugc"); /* ищем и сохраняем элемент страницы */
            return descr.text(); /* сохраняем содержимое элемента страницы в виде текста */
        } catch (IOException e) {
            throw new IllegalArgumentException(); /* если аргументы не корректные, то прерываем программу */
        }
    }

    /**
     * Метод getPost сканирует непосредственно саму вакансию
     * @param row - строчка отдельной вакансии
     * @return возвращаем (в виде готового объекта) вакансию с данными: название, линк, описание, дата
     */
    private Post getPost(Element row) {
        try {
            /* ищем элемент вакансии по признаку вакансии в структуре страницы и извлекаем ссылку на этот элемент */
            Element titleElement = row.selectFirst(".vacancy-card__title").child(0);

            /* получаем название вакансии */
            String nameVacancy = titleElement.text();

            /* ищем элемент даты размещения вакансии и извлекаем ссылку на этот элемент */
            Element dataElement = row.selectFirst(".vacancy-card__date").child(0);

            /* Ссылки находятся в виде атрибута, поэтому их значение надо получить как значение атрибута. */
            String linkVacancy = String.format("%s%s", SOURCE_LINK, titleElement.attr("href"));
            String dataVacancy = dataElement.attr("datetime");

            String description = retrieveDescription(linkVacancy);

            return new Post(
                    nameVacancy,
                    linkVacancy,
                    description,
                    dateTimeParser.parse(dataVacancy));
        } catch (Exception e) {
            throw new IllegalArgumentException(); /* если аргументы не корректные, то прерываем программу */
        }
    }

    /**
     * Метод list загружает список всех вакансий
     * @param link - полный урл вакансии без номера страницы
     * @return - список вакансий в виде массива листа
     */
    @Override
    public List<Post> list(String link) {
        List<Post> listVacancy = new ArrayList<>();
        try {
            for (int pageValue = 1; pageValue <= PAGES; pageValue++) {

                /* соединяемся с указанной в параметре страницей и получаем ее структуру в документ */
                Connection connection = Jsoup.connect(link + pageValue);
                Document document = connection.get();

                /* получаем все вакансии на текущей странице: */
                Elements rows = null;
                if (document != null) {
                    rows = document.select(".vacancy-card__inner");
                }

                /* проходим по всем полученным вакансиям и добавляем их в массив вакансий */
                if (rows != null) {
                    for (Element row : rows) {
                        listVacancy.add(getPost(row));
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(); /* если аргументы не корректные, то прерываем программу */
        }
        return listVacancy;
    }
}

