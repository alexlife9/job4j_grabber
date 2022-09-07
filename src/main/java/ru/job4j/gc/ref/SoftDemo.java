package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Виды ссылок для сборщика мусора
 *
 * 2. Soft Reference - мягкая ссылка. Объекты, на которые ссылаются безопасные ссылки,
 * удаляются только если JVM не хватает памяти, т.е. они могут пережить более одной сборки мусора.
 * Данный тип ссылок подходит для реализации кэша - такой структуры данных, при которой часть данных запоминается,
 * а потом часто переиспользуется.
 * Например, можно запоминать данные из файлов или тяжелых запросов.
 * При нехватке памяти JVM может удалить объекты по этим ссылкам, если на них нет сильных ссылок.
 *
 * Есть контракт для данного типа ссылок: GC гарантировано удалит с кучи все объекты,
 * доступные только по soft-ссылке, перед тем как бросит OutOfMemoryError.
 *
 * @author Alex_life
 * @version 1.0
 * @since 07.09.2022
 */
public class SoftDemo {

    public static void main(String[] args) {
        example1();
        example2();
        unsafe();
        safe();
    }

    /**
     * В первом методе несмотря на то, что мы за'null'уляем сильную ссылку, на объект остается безопасная ссылка,
     * а это значит объект будет удален только при нехватке памяти.
     */
    private static void example1() {
        Object strong = new Object();
        SoftReference<Object> soft = new SoftReference<>(strong);
        strong = null;
        System.out.println(soft.get());
    }

    /**
     * Во втором методе, мы создаем много объектов, но на них ссылается безопасная ссылка.
     * При создании большого количества объектов при малом хипе мы увидим, что объекты начнут удаляться,
     * т.к. перестанет хватать памяти.
     */
    private static void example2() {
        List<SoftReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++) {
            objects.add(new SoftReference<Object>(new Object() {
                String value = String.valueOf(System.currentTimeMillis());

                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            }));
        }
        System.gc();
        int liveObject = 0;
        for (SoftReference<Object> ref : objects) {
            Object object = ref.get();
            if (object != null) {
                liveObject++;
            }
        }
        System.out.println(liveObject);
    }

    /**
     * Корректным использованием безопасных ссылок является сначала получение сильной ссылки на данные,
     * а потом работа с сильной ссылкой.
     * Это гарантирует, что в интервалах получения сильной ссылки из безопасной GC не затрет объект.
     * Это касается не только локальных переменных, но и возвращаемых значений и аргументов.
     */
    private static void unsafe() {
        List<SoftReference<Object>> someData = new ArrayList<>();
        someData.add(new SoftReference<Object>(new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Object removed!");
            }
        }));
        if (someData.get(0).get() != null) {
            System.out.println(someData + "Object is not null");
        } else {
            System.out.println(someData + "Object is null");
        }
        System.gc();
        System.out.println(someData.get(0).get());
    }

    private static void safe() {
        List<SoftReference<Object>> someData = new ArrayList<>();
        someData.add(new SoftReference<>(new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Object removed!");
            }
        }));
        Object strong = someData.get(0).get();
        if (strong != null) {
            System.out.println(strong + " Object is not null");
        } else {
            System.out.println(strong + " Object is null");
        }
        System.gc();
        System.out.println(strong);
        System.out.println(someData);
    }
}
