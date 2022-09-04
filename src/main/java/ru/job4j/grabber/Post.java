package ru.job4j.grabber;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Модель данных - Post
 *
 * @author Alex_life
 * @version 2.0
 * @since 02.09.2022
 */
public class Post {
    /**
     *  - id типа int - идентификатор вакансии (берется из нашей базы данных);
     *  - title типа String - название вакансии;
     *  - link типа String - ссылка на описание вакансии;
     *  - description типа String - описание вакансии;
     *  - created типа LocalDateTime - дата создания вакансии.
     */
    private int id;
    private final String title;
    private final String link;
    private final String description;
    private final LocalDateTime created;

    public Post(int id, String title, String link, String description, LocalDateTime created) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.created = created;
    }

    public Post(String title, String link, String description, LocalDateTime created) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.created = created;
    }

    public Post(LocalDateTime created, String link, String title, String description) {
        this.created = created;
        this.link = link;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id && Objects.equals(link, post.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", link='" + link + '\''
                + ", description='" + description + '\''
                + ", created=" + created
                + '}';
    }
}
