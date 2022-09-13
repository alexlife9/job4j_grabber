package ru.job4j.grabber;

import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * PsqlStore
 *
 * @author Alex_life
 * @version 5.0
 * @since 13.09.2022
 */
public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;

    private final Properties cfg;

    public PsqlStore(Properties prop) throws Exception {
        this.cfg = prop;
        initConnection();
    }

    /**
     * метод осуществляет подключение к БД с через указанный файл настроек
     */
    public void initConnection() throws SQLException {
        try {
            Class.forName(cfg.getProperty("driver"));
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        cnn = DriverManager.getConnection(
                cfg.getProperty("url"),
                cfg.getProperty("login"),
                cfg.getProperty("password")
        );
    }

    /**
     * метод сохраняет вакансию в БД
     * @param post - вакансия
     */
    @Override
    public void save(Post post) {
        try (PreparedStatement ps = cnn.prepareStatement(
                "insert into post(title, link, description, created)"
                        + "values (?, ?, ?, ?) on conflict (link) do nothing;")) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getLink());
            ps.setString(3, post.getDescription());
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
        List<Post> vacancies = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement(
                "select * from post;")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Post post = intoResult(resultSet);
                vacancies.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vacancies;
    }

    /**
     * метод ищет вакансию по айди
     * @param id - айди вакансии
     * @return - найденную вакансию
     */
    @Override
    public Post findById(int id) {
        Post vacancy = null;
        try (PreparedStatement ps = cnn.prepareStatement(
                "select id, title, link, description, created from post where id = ?")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                vacancy = intoResult(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vacancy;
    }

    public Post intoResult(ResultSet resSet) throws SQLException {
        return new Post(
                resSet.getInt(1),
                resSet.getString(2),
                resSet.getString(3),
                resSet.getString(4),
                resSet.getTimestamp(5).toLocalDateTime());
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

    public static void main(String[] args) {
        try {
            Properties config = new Properties();
            InputStream in = PsqlStore.class.getClassLoader().getResourceAsStream("psqlstore.properties");
            config.load(in);
            try (PsqlStore psqlStore = new PsqlStore(config)) {
                HabrCareerParse habrCareerParse = new HabrCareerParse(new HabrCareerDateTimeParser());
                String pageLink = "https://career.habr.com/vacancies/java_developer?page=1";

                for (Post vacancy : habrCareerParse.list(pageLink)) {
                    psqlStore.save(vacancy);
                    System.out.println(vacancy);
                }
                System.out.println(psqlStore.findById(1));
                System.out.println(psqlStore.getAll());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
