package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Принципы Kiss, Dry и Yagni
 *
 * 1. KISS - keep it simple and short (Keep It Simple, Stupid) - Будь проще. Код должен быть простым и коротким.
 * Этот принцип гласит, что простые системы будут работать лучше и надежнее.
 * Не придумывайте к задаче более сложного решения, чем ей требуется. Иногда самое разумное решение оказывается и
 * самым простым. Написание производительного, эффективного и простого кода – это прекрасно.
 *
 * 2. DRY - don't repeat yourself - Не повторяйтесь. Дословно "не повторяй себя".
 * То есть, старайтесь использовать уже существующие методы, чтобы решить новую задачу. Не копируйте код.
 * Дублирование кода – пустая трата времени и ресурсов. Вам придется поддерживать одну и ту же логику и
 * тестировать код сразу в двух местах, причем если вы измените код в одном месте, его нужно будет изменить и в другом.
 *
 * 3. YAGNI - You aren't gonna need it - Вам это не понадобится. Принцип пересекается со вторым.
 * Подумайте, стоит ли создавать новый метод. Можно ли решить задачу уже существующими методами.
 *
 * 4. Big Design Up Front-  Глобальное проектирование прежде всего.
 * Прежде чем переходить к реализации, убедитесь, что все хорошо продумано.
 *
 * 5. SOLID
 * --S) Single-responsibility principle - Принцип единственной ответственности.
 * Каждый объект, класс и метод должны отвечать только за что-то одно.
 * Если ваш объект/класс/метод делает слишком много, вы получите спагетти-код.
 *
 * --O) Open–closed principle - Принцип открытости-закрытости.
 * Программные объекты должны быть открыты для расширения, но закрыты для модификации.
 * Нельзя переопределять методы или классы, просто добавляя дополнительные функции по мере необходимости.
 *
 * --L) Liskov substitution principle - Принцип подстановки Лисков.
 * Объекты старших классов должны быть заменимы объектами подклассов,
 * и приложение при такой замене должно работать так, как ожидается.
 *
 * --I) Interface segregation principle - Принцип разделения интерфейсов.
 * Объекты не должны зависеть от интерфейсов, которые они не используют.
 *
 * --D) Dependency inversion principle - Принцип инверсии зависимостей.
 * Мы должны полагаться на абстракции, а не на конкретные реализации. Компоненты ПО должны иметь низкую связность и
 * высокую согласованность. Заботиться нужно не о том, как что-то устроено, а о том, как оно работает.
 *
 * 6. Avoid Premature Optimization - Избегайте преждевременной оптимизации.
 *
 *
 *
 *
 * @author Alex_life
 * @version 2.0
 * @since 07.09.2022
 */

/* Поиск максимального и минимального элемента по критерию */
public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return forMax(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return forMax(value, comparator.reversed());
    }

    public <T> T forMax(List<T> value, Comparator<T> comparator) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Пустое значение");
        }
        T max = value.get(0);
        for (T t : value) {
            if (comparator.compare(t, max) > 0) {
                max = t;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(9, 2, 5, 4, 1, 7, 6);
        System.out.println(maxMin.max(list, Integer::compareTo));
        System.out.println(maxMin.min(list, Integer::compareTo));
    }
}
