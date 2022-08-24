package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * Quartz
 *
 * @author Alex_life
 * @version 1.0
 * @since 23.08.2022
 */
public class AlertRabbit {
    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler(); /* 1.Конфигурирование */
            /* Начало работы происходит с создания класса управляющего всеми работами.
               В объект Scheduler мы будем добавлять задачи, которые хотим выполнять периодически */
            scheduler.start();
            JobDetail job = newJob(Rabbit.class).build(); /* 2.Создание задачи.
                                                          quartz каждый раз создает объект с типом org.quartz.Job */
            SimpleScheduleBuilder times = simpleSchedule() /* 3.Создание расписания */
                    .withIntervalInSeconds(10) /* Конструкция настраивает периодичность запуска */
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
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
        }
    }


}
