package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * Quartz
 *
 * @author Alex_life
 * @version 2.0
 * добавил файл rabbit.properties для чтения настроек
 * @since 24.08.2022
 */
public class AlertRabbit {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader()
                .getResourceAsStream("rabbit.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler(); /* 1.Конфигурирование */
            /* Начало работы происходит с создания класса управляющего всеми работами.
               В объект Scheduler мы будем добавлять задачи, которые хотим выполнять периодически */
            scheduler.start();
            JobDetail job = newJob(Rabbit.class).build(); /* 2.Создание задачи.
                                                          quartz каждый раз создает объект с типом org.quartz.Job */
            SimpleScheduleBuilder times = simpleSchedule() /* 3.Создание расписания */
                    .withIntervalInSeconds(Integer.parseInt(
                            properties.getProperty("rabbit.interval"))) /* читаем строчку из пропертис */
                    .repeatForever();          /* Запускаем задачу через 10 секунд и делаем это бесконечно */
            Trigger trigger = newTrigger() /* 4.Задача выполняется через триггер */
                    .startNow()            /* Здесь можно указать, когда начинать запуск. Например сразу */
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger); /* 5.Загрузка задачи и триггера в планировщик */
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    /**
     * в классе Rabbit реализуются требуемые действия
     * в данном случае - вывод в консоль
     */
    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
        }
    }
}
