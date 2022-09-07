package ru.job4j.grabber;

import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * PsqlStore
 *
 * @author Alex_life
 * @version 1.0
 * @since 07.09.2022
 */
public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;

    /**
     * метод осуществляет подключение к БД с через указанный файл настроек
     * @param cfg файл настроек
     */
    public PsqlStore(Properties cfg) throws Exception {
        try (InputStream in = PsqlStore.class.getClassLoader().getResourceAsStream("psqlstore.properties")) {
            cfg.load(in);
            Class.forName(cfg.getProperty("driver"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("login"),
                    cfg.getProperty("password")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод сохраняет вакансию в БД
     * @param post - вакансия
     */
    @Override
    public void save(Post post) {
        String sql = "insert into post(name, text, link, created) values (?, ?, ?, ?);";
        try (PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getDescription());
            ps.setString(3, post.getLink());
            ps.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * метод читает из БД все сохраненные вакансии
     * @return общий список вакансий
     */
    @Override
    public List<Post> getAll() {
        List<Post> vacancy = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement("select * from post;")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Post post = new Post(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getTimestamp(5).toLocalDateTime());
                vacancy.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vacancy;
    }

    /**
     * метод ищет вакансию по айди
     * @param id - айди вакансии
     * @return - найденную вакансию
     */
    @Override
    public Post findById(int id) {
        Post post = null;
        String sql = String.format("select id, name, text, link, created from post where id = %s;", id);
        try (PreparedStatement ps = cnn.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                post = new Post(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getTimestamp(5).toLocalDateTime());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post;
    }

    /**
     * метод закрывает связь с БД
     */
    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        PsqlStore psqlStore = new PsqlStore(properties);
        HabrCareerParse habrCareerParse = new HabrCareerParse(new HabrCareerDateTimeParser());
        String pageLink = "https://career.habr.com/vacancies/java_developer?page=1";

        for (Post vacancy : habrCareerParse.list(pageLink)) {
            psqlStore.save(vacancy);
            System.out.println(vacancy);
        }
        System.out.println(psqlStore.findById(1));
        System.out.println(psqlStore.getAll());

    }
}
