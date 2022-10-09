package ru.job4j.ood.isp.menu;

/**
 * Класс Printer выводит в консоль сохраненное меню
 *
 * @author Alex_life
 * @version 1.0
 * @since 09.10.2022
 */
public class Printer implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (MenuItemInfo menuItemInfo : menu) {
            String number = menuItemInfo.getNumber();
            int point = number.length() - number.replaceAll("\\.", "").length();
            if (point > 1) {
                for (int i = 1; i < point; i++) {
                    System.out.print("-");
                }
            }
            System.out.println(menuItemInfo.getNumber() + menuItemInfo.getName());
        }
    }
}
