package ru.job4j.grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.*;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Grabber
 * Собственно это уже сама программа которая объединяет Парсер, Планировщик и Хранилище
 *
 * @author Alex_life
 * @version 2.0
 * @since 11.09.2022
 */
public class Grabber implements Grab {
    private final Properties cfg = new Properties();

    /* хранилище */
    public Store store() throws Exception {
        return new PsqlStore(cfg);
    }

    /* планировщик */
    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        return scheduler;
    }

    /* читаем настройки */
    public void cfg() throws IOException {
        try (InputStream in = Grabber.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
    }

    /**
     /**
     * метод init загружает настройки Планировщика
     *
     * 1.создаем хранилище JobDataMap.
     * 2.создаем задачу для Планировщика - JobDetail
     * 3.добавляем в планировщик интервал с которым будет выполняться задача
     * 4.добавляем триггер, в котором указали что запуск задачи будет производиться сразу
     * 5.Загружаем задачу и триггер в планировщик с помощью метода scheduleJob
     * @param parse парсер из интерфейса Parse
     * @param store хранилище из интерфейса Store
     * @param scheduler планировщик
     */
    @Override
    public void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException {
        JobDataMap data = new JobDataMap();
        data.put("store", store);
        data.put("parse", parse);
        JobDetail job = newJob(GrabJob.class)
                .usingJobData(data)
                .build();
        SimpleScheduleBuilder times = simpleSchedule()
                .withIntervalInSeconds(Integer.parseInt(cfg.getProperty("time")))
                .repeatForever();
        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(times)
                .build();
        scheduler.scheduleJob(job, trigger);
    }

    public static class GrabJob implements Job {
        /**
         * метод execute выполняет всю основную работу парсера
         *
         * 1.получаем объекты из входящих данных с помощью методов getJobDetail и getJobDataMap
         * 2.передаем полученные объекты в хранилище и парсер.
         * 3.добавляем в хранилище все вакансии из указанного линка
         *
         * @param context ресурс входящих данных
         */
        @Override
        public void execute(JobExecutionContext context) {
            JobDataMap map = context.getJobDetail().getJobDataMap();
            Store store = (Store) map.get("store");
            Parse parse = (Parse) map.get("parse");
            List<Post> vacancy;
            try {
                vacancy = parse.list("https://career.habr.com/vacancies/java_developer?page=");
                vacancy.forEach(store::save);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Grabber grab = new Grabber();
        grab.cfg();
        Scheduler scheduler = grab.scheduler();
        Store store = grab.store();
        grab.init(new HabrCareerParse(new HabrCareerDateTimeParser()), store, scheduler);
    }
}
