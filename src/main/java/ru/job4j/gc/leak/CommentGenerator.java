package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Генератор комментариев
 *
 * смотри User
 *
 * Генератор комментариев. Также при создании заполним список фразами, а при вызове generate зачистим список,
 * а затем сгенерируем 50 комментариев из случайных фраз.
 *
 * @author Alex_life
 * @version 3.0
 * @since 18.10.2022
 */
public class CommentGenerator implements Generate {
    public static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";
    public static final String SEPARATOR = System.lineSeparator();
    public static final int COUNT = 50;

    /* интенсивное использование статических переменных может приводить к утечке памяти.
    В Java статические поля имеют срок службы, который обычно соответствует всему сроку службы работающего приложения*/
    private List<Comment> comments = new ArrayList<>();
    private List<String> phrases;
    private UserGenerator userGenerator;
    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        for (int i = 0; i < COUNT; i++) {
            String comment = String.format("%s%s%s%s%s",
                    phrases.get(random.nextInt(phrases.size())), SEPARATOR,
                    phrases.get(random.nextInt(phrases.size())), SEPARATOR,
                    phrases.get(random.nextInt(phrases.size())));
            comments.add(new Comment(comment,
                    userGenerator.randomUser()));
        }
    }
}
