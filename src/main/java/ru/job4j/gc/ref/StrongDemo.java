package ru.job4j.gc.ref;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Виды ссылок для сборщика мусора
 *
 * 1. Strong Reference. Сильная ссылка является "обычной" ссылкой, которую мы привыкли создавать.
 * При данном типе ссылок объекты удаляются только если на них нет сильной ссылки
 * или они находятся в составе объекта на который нет ссылки.
 *
 * @author Alex_life
 * @version 1.0
 * @since 07.09.2022
 */
public class StrongDemo {

    public static void main(String[] args) throws InterruptedException {
        example1(); /* все объекты с null-ссылками - удаляются */
        example2(); /* видим что удаляются даже вложенные объекты */
        example3(); /* OutOfMemoryError: Java heap space. Есть неиспользуемые ссылки на созданные объекты */
    }

    /**
     * В этом методе создаем объекты и далее их за'null'яем.
     * Вызываем сборщик мусора и ждем некоторое время. Объекты удаляются, т.к. ссылки на них null
     */
    private static void example1() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            objects[i] = new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            };
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Во втором методе, мы создаем объекты вместе с вложенными.
     * Удаляя внешние объекты как в примере выше удаляются и вложенные, не смотря на то что они не null.
     */
    private static void example2() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            Object object = new Object() {
                Object innerObject = new Object() {
                    @Override
                    protected void finalize() throws Throwable {
                        System.out.println("Remove inner object!");
                    }
                };
            };
            objects[i] = object;
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Проблема ссылок типа Strong Reference является то, что если в программе есть неиспользуемые ссылки
     * на созданные объекты, то они не будут удалены, что приведет к утечке памяти, что в свою очередь может привести
     * к ошибке OutOfMemoryException - ситуации когда программе не хватает выделенной памяти.
     *
     * К примеру код из метода example3 явно приведет к этой ошибке.
     * Чтобы быстрее ее увидеть можно выставить аргументы -Xmx4m -Xms4m
     */
    private static void example3() {
        List<String> strings = new ArrayList<>();
        while (true) {
            strings.add(String.valueOf(System.currentTimeMillis()));
        }
    }
}