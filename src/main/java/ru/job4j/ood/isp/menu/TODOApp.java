package ru.job4j.ood.isp.menu;

import java.util.Scanner;

/**
 * Приложение для построения и вывода списка задач пользователя.
 *
 * @author Alex_life
 * @version 3.0
 * @since 17.10.2022
 */
public class TODOApp {
    private static final int ADD_ROOT_TODO = 1;
    private static final int ADD_CHILD_TODO = 2;
    private static final int SHOW_TODOS = 3;
    private static final int QUIT = 4;
    private static final String TASK_MSG = "Введите описание основного задания";
    private static final String TASK_NUMBER_MSG = "Введите номер меню для добавление в него подзадания:";
    private static final String INNER_TASK_MSG = "Введите описание вложенного задания";
    private static final String CREATED_SAVE_MSG = "Задание создано и сохранено";
    private static final String ALL_TODOS_MSG = "Список всех заданий:";
    private static final String QUIT_MSG = "Работа завершена";
    private static final String LS = System.lineSeparator();
    private static final ActionDelegate ACTION_PRINTLN = System.out::println;
    private static final String START_MENU = """
            Меню. Введите номер пункта:
            1. Добавить задание в корень списка
            2. Добавить подзадание
            3. Вывести список всех заданий
            4. Завершить работу программы
            """;

    public static void main(String[] args) {
        Menu savesTodo = new SimpleMenu();
        MenuPrinter menuPrinter = new ConsoleMenuPrinter();
        Scanner scanner = new Scanner(System.in);
        String parentName;
        String childName;
        int userInput = -1;
        while (QUIT != userInput) {
            System.out.println(START_MENU);
            userInput = scanner.nextInt();
            if (ADD_ROOT_TODO == userInput) {
                System.out.println(TASK_MSG);
                parentName = scanner.next();
                savesTodo.add(Menu.ROOT, parentName, ACTION_PRINTLN);
                System.out.println(CREATED_SAVE_MSG);
                System.out.println(LS);
            }

            if (ADD_CHILD_TODO == userInput) {
                System.out.println(TASK_NUMBER_MSG);
                parentName = scanner.next();
                if (savesTodo.select(parentName).isEmpty()) {
                    savesTodo.add(Menu.ROOT, parentName, ACTION_PRINTLN);
                    System.out.println(savesTodo);
                }
                System.out.println(INNER_TASK_MSG);
                childName = scanner.next();
                savesTodo.add(parentName, childName, ACTION_PRINTLN);
                System.out.println(CREATED_SAVE_MSG);
                System.out.println(LS);
            }

            if (SHOW_TODOS == userInput) {
                System.out.println(ALL_TODOS_MSG);
                menuPrinter.print(savesTodo);
                System.out.println(LS);
            }
        }
        System.out.println(QUIT_MSG);
    }
}
