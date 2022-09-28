package ru.job4j.ood.lsp;

import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип подстановки Лисков - Liskov Substitution Principle
 *
 * смотри AutoTransport
 *
 * @author Alex_life
 * @version 1.0
 * @since 27.09.2022
 */
class WorkDays implements Iterable<Integer> {
    /* Рассмотрим 2. Постусловия.
    Допустим есть бухгалтерия, которая считает по табелю сколько работник отработал и если он отработал норму,
    то высчитывает для него зарплату. Далее мы наследуемся допустим для бухгалтерии магазина.
    В нем мы забываем про условие когда добавляем специфическое поведение и когда запускаем пример,
    то получаем, что недобросовестный работник получает зарплату. */

    private Map<LocalDate, Integer> workDays = new LinkedHashMap<>();

    public void add(LocalDate date, int hours) {
        workDays.put(date, hours);
    }

    @Override
    public Iterator<Integer> iterator() {
        return workDays.values().iterator();
    }
}

class CountingRoom {

    protected int normHours;

    protected int payPerHour;

    public CountingRoom(int normHours, int payPerHour) {
        this.normHours = normHours;
        this.payPerHour = payPerHour;
    }

    public int pay(WorkDays workDays) {
        int factHours = 0;
        for (Integer hoursPerDay : workDays) {
            factHours += hoursPerDay;
        }
        if (factHours < normHours) {
            throw new IllegalArgumentException("Worker didn't work enough!");
        }
        return factHours * payPerHour;
    }
}

class ShopCountingRoom extends CountingRoom {

    public ShopCountingRoom(int normHours, int payPerHour) {
        super(normHours, payPerHour);
    }

    @Override
    public int pay(WorkDays workDays) {
        int factHours = 0;
        for (Integer hoursPerDay : workDays) {
            factHours += hoursPerDay;
        }
        return factHours * payPerHour;
    }
}

class SecondRule {
    public static void main(String[] args) {

        WorkDays workDays = new WorkDays();
        workDays.add(LocalDate.of(2020, Month.DECEMBER, 1), 8);
        workDays.add(LocalDate.of(2020, Month.DECEMBER, 2), 6);
        workDays.add(LocalDate.of(2020, Month.DECEMBER, 3), 7);

        CountingRoom countingRoom = new ShopCountingRoom(3 * 8, 500);
        System.out.println(countingRoom.pay(workDays));
    }
}
