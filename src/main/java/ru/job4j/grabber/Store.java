package ru.job4j.grabber;

import java.util.List;

/**
 * Архитектура проекта - Агрегатор Java Вакансий
 *
 * Проект будет хранить данные в базе Postgresql. Связь с базой будет осуществляться через интерфейс Store.
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.09.2022
 */
public interface Store {
    /**
     * Метод save() - сохраняет объявление в базе.
     * Метод getAll() - позволяет извлечь объявления из базы.
     * Метод findById(int id) - позволяет извлечь объявление из базы по id.
     */
    void save(Post post);

    List<Post> getAll();

    Post findById(int id);
}
