package ru.job4j.grabber;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/**
 * Архитектура проекта - Агрегатор Java Вакансий
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.09.2022
 */
public interface Grab {
    void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException;
}
