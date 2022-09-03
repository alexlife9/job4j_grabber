package ru.job4j.grabber;

import java.io.IOException;
import java.util.List;

/**
 * Архитектура проекта - Агрегатор Java Вакансий
 *
 * Интерфейс Parse описывает парсинг сайта
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.09.2022
 */
public interface Parse {
    /**
     * Этот компонент позволяет собрать короткое описание всех объявлений,
     * а так же загрузить детали по каждому объявлению.
     * list(link) - этот метод загружает список объявлений по ссылке типа - https://www.sql.ru/forum/job-offers/1
     * Описание компонента через интерфейс позволяет расширить наш проект.
     * Например, осуществить сбор данных с других площадок
     */
    List<Post> list(String link) throws IOException;
}
