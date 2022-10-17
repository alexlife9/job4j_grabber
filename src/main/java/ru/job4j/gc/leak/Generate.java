package ru.job4j.gc.leak;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Генератор комментариев
 *
 * смотри User
 *
 * Интерфейс генератора (чтение не будем реализовывать отдельно для экономии кода в задании,
 * поэтому реализуем как дефолтный метод в Generate):
 *
 * @author Alex_life
 * @version 1.0
 * @since 17.10.2022
 */
public interface Generate {

    void generate();

    default List<String> read(String path) throws IOException {
        List<String> text = new ArrayList<>();
        Files.lines(Paths.get(path))
                .forEach(text::add);
        return text;
    }
}
