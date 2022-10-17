package ru.job4j.gc.leak;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Генератор комментариев
 *
 * смотри User
 *
 * Хранилище постов - PostStore
 *
 * @author Alex_life
 * @version 1.0
 * @since 17.10.2022
 */
public class PostStore {
    private static Map<Integer, Post> posts = new HashMap<>();
    private AtomicInteger atomicInteger = new AtomicInteger(1);

    public Post add(Post post) {
        Integer id = atomicInteger.getAndIncrement();
        post.setId(id);
        posts.put(id, post);
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public static Collection<Post> getPosts() {
        return posts.values();
    }
}