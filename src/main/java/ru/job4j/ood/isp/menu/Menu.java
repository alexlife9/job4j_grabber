package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Создать меню
 *
 * Описание:
 * 1) Реализовать структуру для поддержания меню.
 * 2) Каждый элемент меню имеет имя. Все меню должно выводиться на экран.
 * 3) Каждый пункт меню может быть как одиночным элементом, так и иметь дочерние подпункты.
 * 4) Все меню должно выводиться на экран. В виде дерева.
 * 5) Предусмотреть возможность определять действие, когда пользователь выбирает конкретный пункт меню.
 *
 * ТЗ:
 * 1. Реализовать поиск в методе findItem() на основе DFS итератора.
 * 2. На основе метода findItem() реализовать методы add() и select().
 * 3. Также на основе существующего итератора реализовать метод iterator()
 * 4. Создать класс-реализацию MenuPrinter. Он должен печатать меню в консоль,
 * но только с отступами как примере в самом начале задания.
 * 5. Дописать тесты на метод select() и на вывод.
 * 6. Создать простенький класс TODOApp. Он должен служить для построения и вывода списка задач пользователя.
 *
 * Реализация:
 * 1. При разработке в первую очередь необходимо понять с какими сущностями нужно работать,
 * т.к. они отображают модели предметной области.
 * Для этого достаточно ориентироваться на существительные в тех. задании (в нашем случае это описание выше).
 * Из описания данного задания можно выделить сущности: меню - Menu, элемент меню - MenuItem, действие - Action.
 *
 * 2. Теперь когда есть сущности, можно на основе их свойств и поведений их описать через интерфейсы.
 * Свойства определяются состоянием объекта, т.е. его полями, а поведение это то, что заключено в его методах.
 *
 * Опишем выделенные нами сущности:
 * 1) интерфейс Action
 * 2) интерфейс MenuItem
 * 3) интерфейс Menu с вложенным классом MenuItemInfo
 * 4) интерфейс MenuPrinter
 * 5) каркас меню SimpleMenu
 *
 * @author Alex_life
 * @version 2.0
 * @since 15.10.2022
 */
public interface Menu extends Iterable<Menu.MenuItemInfo> {

    String ROOT = null; /* Константа, указывающая, что нужно добавить элемент в корень */

    boolean add(String parentName, String childName, ActionDelegate actionDelegate);

    Optional<MenuItemInfo> select(String itemName);

    class MenuItemInfo {

        private final String name;
        private final List<String> children;
        private final ActionDelegate actionDelegate;
        private final String number;

        public MenuItemInfo(MenuItem menuItem, String number) {
            this.name = menuItem.getName();
            this.children = menuItem.getChildrenName().stream().map(MenuItem::getName).collect(Collectors.toList());
            this.actionDelegate = menuItem.getActionDelegate();
            this.number = number;
        }

        public MenuItemInfo(String name, List<String> children, ActionDelegate actionDelegate, String number) {
            this.name = name;
            this.children = children;
            this.actionDelegate = actionDelegate;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public List<String> getChildren() {
            return children;
        }

        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }

        public String getNumber() {
            return number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MenuItemInfo that = (MenuItemInfo) o;
            return Objects.equals(name, that.name)
                    && Objects.equals(children, that.children)
                    && Objects.equals(number, that.number);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, children, number);
        }
    }
}