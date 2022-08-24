package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * Job c параметрами
 *
 * @author Alex_life
 * @version 3.0
 * добавил коннект к базе при старте. Объект коннект будет передаваться в Job.
 * @since 25.08.2022
 */
public class AlertRabbit {

    private static Properties properties;

    public AlertRabbit(Properties properties) {
        AlertRabbit.properties = properties;
    }

    public static Connection initConnection() throws ClassNotFoundException {
        Class.forName(properties.getProperty("driver"));
        try {
            Connection connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return initConnection();
    }


    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader()
                .getResourceAsStream("rabbit.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection cn = initConnection()) {
            List<Long> store = new ArrayList<>();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler(); /* 1.Конфигурирование */
            /* Начало работы происходит с создания класса управляющего всеми работами.
               В объект Scheduler мы будем добавлять задачи, которые хотим выполнять периодически */
            scheduler.start();
            JobDataMap data = new JobDataMap(); /* При создании Job указываем параметры data,
                                                   в которые передаем ссылку на store */
            data.put("store", store);           /* В данном примере store это ArrayList.*/
            data.put("connection", cn);
            JobDetail job = newJob(Rabbit.class) /* 2.Создание задачи */
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule() /* 3.Создание расписания */
                    .withIntervalInSeconds(Integer.parseInt(
                            properties.getProperty("rabbit.interval"))) /* читаем строчку из пропертис */
                    .repeatForever();          /* Запускаем задачу через 10 секунд и делаем это бесконечно */
            Trigger trigger = newTrigger() /* 4.Задача выполняется через триггер */
                    .startNow()            /* Здесь можно указать, когда начинать запуск. Например сразу */
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger); /* 5.Загрузка задачи и триггера в планировщик */
            Thread.sleep(10000); /* приостанавливаем работу на указанное время (1/1000 часть секунды) */
            scheduler.shutdown(); /* завершаем работу */
            System.out.println(store); /* выводим в консоль записи из аррэйлиста */
        } catch (SchedulerException | InterruptedException | ClassNotFoundException | SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * в классе Rabbit реализуются требуемые действия
     * в данном случае - вывод в консоль
     */
    public static class Rabbit implements Job {

        public Rabbit() {
            System.out.println(hashCode());
        }

        /**
         * Каждый запуск работы вызывает конструктор.
         * Чтобы в объекте Job иметь общий ресурс нужно использовать JobExecutionContext.
         */
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
            Connection connection = (Connection) context.getJobDetail().getJobDataMap().get("connection");
            try (PreparedStatement statement = connection.prepareStatement(
                    "insert into rabbits (created_date) values (?)"
            )) {
                statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                statement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
