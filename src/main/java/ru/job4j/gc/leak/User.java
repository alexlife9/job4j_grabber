package ru.job4j.gc.leak;

import java.util.Objects;

/**
 * Генератор комментариев
 *
 * Суть такая – при добавлении поста генерируем к нему 50 комментариев от вымышленных пользователей.
 * Комментарий формируется из трех рандомных цитат и случайного пользователя.
 * Комментарий будем составлять пользуясь золотым фондом цитат.
 *
 * Реализация простая, но в коде есть места, в которых происходит утечка памяти, а также присутствуют намеренные ошибки
 * которые приводят к созданию лишних объектов в хипе.
 *
 * Демонстрация утечки в VisualVM:
 * Запуск с параметрами -XX:+UseSerialGC -Xmx15m -Xms15m -Xlog:gc:log.txt. Посмотрим на сборку в одном потоке.
 * Важно. Если у Вас на графике заметно большее потребление памяти (в общем или процессы чтения из файлов),
 * чем в примере, увеличьте хип хотя бы до 20. На каждой машине данный процесс индивидуальный.
 * Чтобы наглядно показать утечку память, следовать такому алгоритму:
 * - запускаем программу и ждем 30 секунд;
 * - создаем 100 постов, снова ждем 30 секунд;
 * - удаляем все посты;
 * - через 30 секунд создаем 100 постов и ждем одну минуту.
 * Общая работа программы  – 2,5 минуты.
 *
 * Запуск программы - в классе Menu
 *
 * @author Alex_life
 * @version 1.0
 * @since 17.10.2022
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
