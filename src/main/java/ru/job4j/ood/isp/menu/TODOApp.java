package ru.job4j.ood.isp.menu;

import java.util.Scanner;

/**
 * Приложение для построения и вывода списка задач пользователя.
 *
 * @author Alex_life
 * @version 1.0
 * @since 09.10.2022
 */
public class TODOApp {
    private static final String ADD_TODO_ROOT = "1. Добавить задание в корень списка";
    private static final String ADD_CHILD_TODO = "2. Выбрать номер задания чтобы добавить в него подзадание";
    private static final String SHOW_TODOS = "3. Вывести список всех заданий";
    private static final String QUIT = "4. Завершить работу программы";
    private static final String ADD_TODO_HERE = "Введите номер меню для добавление в него подзадания:";
    private static final String ENTER_TODO = "Введите описание задания";
    private static final String ENTER_TODO_INNER = "Введите вложенное задание";
    private static final String TODO_CREATED_SAVE = "Задание создано и сохранено";
    private static final String LINE = System.lineSeparator();
    public static final ActionDelegate ACTION_OUT = System.out::println;

    public static void main(String[] args) {
        Menu savesTodo = new SimpleMenu();
        MenuPrinter menuPrinter = new Printer();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;        while (run) {
            System.out.println("Меню. Введите номер пункта:");
            System.out.println(ADD_TODO_ROOT);
            System.out.println(ADD_CHILD_TODO);
            System.out.println(SHOW_TODOS);
            System.out.println(QUIT);
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                System.out.println(ENTER_TODO);
                String taskName = scanner.nextLine();
                savesTodo.add(Menu.ROOT, taskName, ACTION_OUT);
                System.out.println(TODO_CREATED_SAVE);
                System.out.println(LINE);
            }

            if (choice == 2) {
                System.out.println(ADD_TODO_HERE);
                menuPrinter.print(savesTodo);
                String innerNumber = scanner.nextLine();
                /* логика выбора корневого пункта */
                System.out.println(ENTER_TODO_INNER);
                String innerTask = scanner.nextLine();
                /* тут надо как-то сделать взаимосвязь parentTask с innerNumber */
                savesTodo.add(Menu.ROOT, innerTask, ACTION_OUT); /* пока Menu.ROOT оставил вместо parentTask */
                System.out.println(TODO_CREATED_SAVE);
                System.out.println(LINE);
            }

            if (choice == 3) {
                System.out.println("Общий список заданий:");
                menuPrinter.print(savesTodo);
                System.out.println(LINE);
            }

            if (choice == 4) {
                run = false;
            }
        }
        System.out.println("Работа завершена");
    }
}
