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
 * @version 5.0
 * @since 26.08.2022
 */
public class AlertRabbit {

    public Connection initConnection(Properties pr)  throws ClassNotFoundException, SQLException {
        Class.forName(pr.getProperty("driver"));
        return DriverManager.getConnection(
                pr.getProperty("url"),
                pr.getProperty("login"),
                pr.getProperty("password")
        );
    }

    /**
     * метод для чтения файла с настройками
     */
    public Properties loadProp() {
        Properties prop = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader()
                .getResourceAsStream("rabbit.properties")) {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AlertRabbit alertRabbit = new AlertRabbit(); /* создаем объект класса,
                                                         поскольку нельзя напрямую обратиться к нестатическим методам*/
        Connection cn = alertRabbit.initConnection(alertRabbit.loadProp()); /* подключаемся к БД,
                    при помощи отдельного метода в параметры которого передали метод считывающий файл с настройками */
        try {
            List<Long> store = new ArrayList<>();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler(); /* 1.Конфигурирование */
            /* Начало работы происходит с создания класса управляющего всеми работами.
               В объект Scheduler мы будем добавлять задачи, которые хотим выполнять периодически */
            scheduler.start(); /* запускаем планировщик */
            JobDataMap data = new JobDataMap(); /* При создании Job указываем параметры data,
                                                   в которые передаем ссылку на store */
            data.put("store", store);           /* В данном примере store это ArrayList.*/
            data.put("connection", cn); /* добавляем в БД запись о подключении */
            JobDetail job = newJob(Rabbit.class) /* 2.Создание задачи */
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule() /* 3.Создание расписания */
                    .withIntervalInSeconds(Integer.parseInt(
                            alertRabbit.loadProp().getProperty("rabbit.interval"))) /* читаем строчку из пропертис */
                    .repeatForever();          /* Запускаем задачу через 10 секунд и делаем это бесконечно */
            Trigger trigger = newTrigger() /* 4.Задача выполняется через триггер */
                    .startNow()            /* Здесь можно указать, когда начинать запуск. Например сразу */
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger); /* 5.Загрузка задачи и триггера в планировщик */
            Thread.sleep(10000); /* приостанавливаем работу на указанное время (1/1000 часть секунды) */
            scheduler.shutdown(); /* завершаем работу */
            System.out.println(store); /* выводим в консоль записи из аррэйлиста */
        } catch (SchedulerException | InterruptedException se) {
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
                    "insert into rabbit (created_date) values (?)"
            )) {
                statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                statement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
