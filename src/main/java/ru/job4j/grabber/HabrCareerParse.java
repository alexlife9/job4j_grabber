package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

/**
 * Парсинг HTML страницы
 *
 * По техническому заданию получаем данные с сайта - https: career.habr.com/vacancies/java_developer
 *
 * @author Alex_life
 * @version 6.0
 * добавил отображение описания вакансии
 * @since 03.09.2022
 */
public class HabrCareerParse {

    /**
     * Есть две константы.
     * Первая это ссылка на сайт в целом.
     * Вторая указывает непосредственно на страницу с вакансиями
     */
    private static final String SOURCE_LINK = "https://career.habr.com";

    public static final int PAGES = 5;

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);

    public static void main(String[] args) throws IOException {

        for (int pageValue = 1; pageValue <= PAGES; pageValue++) {
            /* создаем объект класса для доступа к нестатическому методу */
            HabrCareerParse dcr = new HabrCareerParse();

            /* получаем страницу, чтобы с ней можно было работать: */
            Connection connection = Jsoup.connect(PAGE_LINK + pageValue);
            Document document = connection.get();

            /* создаем объект даты */
            HabrCareerDateTimeParser dateParser = new HabrCareerDateTimeParser();

            /* получаем все вакансии страницы: */
            Elements rows = document.select(".vacancy-card__inner");

        /* Проходимся по каждой вакансии и извлекаем нужные для нас данные.
        Сначала получаем элементы содержащие название и ссылку.
        Стоит обратить внимание, что дочерние элементы можно получать через индекс - метод child(0)
        или же через селектор - select(".vacancy-card__title"). */
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkTitleEl = titleElement.child(0);

                /* Получаем дату и ссылку на нее */
                Element dataElement = row.select(".vacancy-card__date").first();
                Element linkDataEl = dataElement.child(0);

            /* Наконец получаем данные непосредственно.
            text() возвращает все содержимое элемента в виде текста, т.е. весь текст что находится вне тегов HTML.
            Ссылка находится в виде атрибута, поэтому ее значение надо получить как значение атрибута.
            Для этого служит метод attr()*/
                String vacancyName = titleElement.text();
                LocalDateTime vacancyData = dateParser.parse(linkDataEl.attr("datetime"));
                String link = String.format("%s%s", SOURCE_LINK, linkTitleEl.attr("href"));
                try {
                    System.out.printf("%s %s %s %s %n",
                            vacancyData,
                            vacancyName,
                            link,
                            dcr.retrieveDescription(link));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * метод извлекает описание вакансии
     * @param link ссылка на вакансию
     * @return описание вакансии
     */
    private String retrieveDescription(String link) throws IOException {
        Connection cn = Jsoup.connect(link);
        Document doc = cn.get();
        Element description = doc.select(".style-ugc").first();
        return description.text();
    }
}

