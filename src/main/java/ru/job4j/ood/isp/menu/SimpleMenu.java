package ru.job4j.ood.isp.menu;

import java.util.*;

/**
 * Создать меню
 *
 * смотри Menu
 *
 * 5) каркас меню SimpleMenu
 * - ItemInfo. Он служит для того, чтобы скомпоновать пункт меню и номер пункта (1.1., 1.1.1. и т.д.).
 * - SimpleMenuItem. Это реализация MenuItem для SimpleMenu.
 * - DFSIterator. Это итератор, основанный на поиске в глубину. Но немного модифицированный,
 * поскольку нам удобно читать пункты меню сверху-вниз и слева-направо.
 *
 * @author Alex_life
 * @version 2.0
 * @since 15.10.2022
 */
public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>(); /* список корневых пунктов меню */

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        /* если дочерний пункт существует, то не добавляем в корень */
        if (findItem(childName).isPresent()) {
            return false;
        }

        /* сравнимаем имя корневого пункта с текущим пунктом. если true, то добавляем в корень новый пункт меню */
        if (Objects.equals(Menu.ROOT, parentName)) {
            return rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        }

        return findItem(parentName).map(info ->
                info.menuItem.getChildrenName()
                .add(new SimpleMenuItem(childName, actionDelegate)))
                .orElse(false);
    }

    /* метод выбора пункта меню. возвращает результат поиска по меню */
    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(info -> new MenuItemInfo(info.menuItem, info.number));
    }

    /* переопределенный метод iterator */
    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {

            final DFSIterator dfsIterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = dfsIterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> info = Optional.empty();
        DFSIterator iterator = new DFSIterator();
        ItemInfo itemInfo;
        while (iterator.hasNext()) {
            itemInfo = iterator.next();
            if (itemInfo.menuItem.getName().equals(name)) {
                info = Optional.of(itemInfo);
                break;
            }
        }
        return info;
    }

    /* класс SimpleMenuItem - это реализация MenuItem для SimpleMenu */
    private static class SimpleMenuItem implements MenuItem {

        private final String name; /* имя пункта меню */
        private final List<MenuItem> childrenName = new ArrayList<>(); /* лист вложенных пунктов */
        private final ActionDelegate actionDelegate; /* некое действие которое будет делаться при выборе пункта меню */

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildrenName() {
            return childrenName;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    /* класс DFSIterator - это итератор, основанный на поиске в глубину. Но немного модифицированный,
    поскольку нам удобно читать пункты меню сверху-вниз и слева-направо. */
    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>(); /* очередь из пунктов меню */

        Deque<String> numbers = new LinkedList<>(); /* очередь из номеров */

        /* в конструкторе итератора идем по всем корневым пунктам меню, добавляем их в стек, и также добавляеем к ним
        * сразу номера по возрастанию от единицы, добавляя после номера точку-разделитель */
        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        /* пока существует элемент в стеке идем по итератору. вызываем next */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildrenName();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    /* класс ItemInf служит для того чтобы скомпоновать пункт меню и номер подпункта (1.1., 1.1.1. и т.д.) */
    private static class ItemInfo {
        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}
