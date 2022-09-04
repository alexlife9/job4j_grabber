package ru.job4j.gc.prof;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.MILLIS;

/**
 * Эксперименты с различными GC
 *
 * ТЗ:
 * На базе классов в package prof создать небольшое меню.
 * Например: 1.Создание массива, 2.Сортировка пузырьком, 3. Сортировка вставками, 4. Сортировка слиянием 5. Выход.
 * В меню сначала создается массив, а затем предоставляется возможность запустить любую из сортировок.
 * Каждую сортировку запускаем самостоятельно путем выбора пункта меню,
 * чтобы пронаблюдать работу программы в паузах между сортировками.
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.09.2022
 */
public class GCTest {

    private static final int MERGESORT = 1;
    private static final int INSERTSORT = 2;
    private static final int BUBLESORT = 3;
    private static final int EXIT = 4;

    private static final List<String> LOGLIST = new ArrayList<>();

    private static void menu() {
        System.out.println();
        System.out.println("Меню:");
        System.out.println("1. Сортировка слиянием");
        System.out.println("2. Сортировка методом вставки");
        System.out.println("3. Сортировка пузырьком");
        System.out.println("4. Выход");
    }

    public static void main(String[] args) {
        RandomArray arrayValue = new RandomArray(new Random()); /* создаем перемешанный массив */
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажи размер массива на сортировку:");
        int getArray = scanner.nextInt();
        arrayValue.insert(getArray);
        LocalDateTime start = LocalDateTime.now();
        while (run) {
            menu();
            int choice = scanner.nextInt(); /* сканируем введенное число юзера */
            if (choice == MERGESORT) {
                MergeSort mergeSort = new MergeSort();
                mergeSort.sort(arrayValue);
                System.out.println("Сортировка слиянием заняла: "
                        + MILLIS.between(start, LocalDateTime.now()) + " мсек");

            }
            if (choice == INSERTSORT) {
                InsertSort insertSort = new InsertSort();
                insertSort.sort(arrayValue);
                System.out.println("Сортировка вставкой заняла: "
                        + MILLIS.between(start, LocalDateTime.now()) + " мсек");
            }
            if (choice == BUBLESORT) {
                BubbleSort bubbleSort = new BubbleSort();
                bubbleSort.sort(arrayValue);
                System.out.println("Сортировка пузырьком заняла: "
                        + MILLIS.between(start, LocalDateTime.now()) + " мсек");
                LOGLIST.add(("Сортировка пузырьком заняла: "
                        + MILLIS.between(start, LocalDateTime.now()) + " мсек"));
            }
            if (choice < 1 || choice > 4) {
                System.out.println("Введи корректное число");
            }
            if (choice == EXIT) {
                run = false;
                System.out.println("Всего хорошего!");
            }
        }
        save(LOGLIST);
    }

    public static void save(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("./data/logGS.txt")
                ))) {
            out.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
