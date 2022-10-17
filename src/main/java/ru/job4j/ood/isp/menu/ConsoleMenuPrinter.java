package ru.job4j.ood.isp.menu;

/**
 * Класс Printer выводит в консоль сохраненное меню
 *
 * @author Alex_life
 * @version 3.0
 * @since 17.10.2022
 */
public class ConsoleMenuPrinter implements MenuPrinter {
    private static final String INDENT = "-";
    private static final String SEPARATOR = "\\.";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo itemInfo : menu) {
            System.out.println(INDENT.repeat(itemInfo.getNumber().split(SEPARATOR).length - 1)
                    + itemInfo.getNumber() + itemInfo.getName());
        }
    }
}
