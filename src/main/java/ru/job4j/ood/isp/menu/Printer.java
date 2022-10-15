package ru.job4j.ood.isp.menu;

/**
 * Класс Printer выводит в консоль сохраненное меню
 *
 * @author Alex_life
 * @version 2.0
 * @since 15.10.2022
 */
public class Printer implements MenuPrinter {
    private static final String SEPARATOR = "-";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo test4 : menu) {
            String test = test4.getNumber();
            String[] test2 = test.split("\\.");
            int test3 = test2.length - 1;
            System.out.println(SEPARATOR.repeat(test3) + test4.getNumber() + test4.getName());
        }
    }
}
