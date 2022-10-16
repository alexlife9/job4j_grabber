package ru.job4j.ood.dip;

import java.util.*;
/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип инверсии зависимостей - Dependency Inversion Principle
 *
 * @author Alex_life
 * @version 1.0
 * @since 16.10.2022
 */
public class SimpleShopService {

    /**
     * Поля. У сервиса есть единственное поле - мапа, для хранения данных.
     * С точки зрения DIP, это нарушение, потому что мы зависим от реализации, а не абстракции.
     * Решение - выделение абстракции для хранилища - ShopStore
     * и уже далее от него нужно будет реализовать InMemoryShopStore
     */

    private HashMap<User, Set<Order>> serviceDB = new HashMap<>();

    /* и в самом сервисе избавиться от зависимости на само хранилище.
    Тогда можно будет его подменить на любое другое, т.к. нет прямой зависимости.*/
    private ShopStore shopStore;

    public SimpleShopService(ShopStore shopStore) {
        this.shopStore = shopStore;
    }

    public boolean createBucket(User user) {
        if (serviceDB.containsKey(user)) {
            return false;
        }
        return serviceDB.put(user, new HashSet<>()) != null;
    }

    public boolean createOrder(User user, Order order) {
        Set<Order> orders = serviceDB.getOrDefault(user, Set.of());
        if (orders.isEmpty()) {
            return false;
        }
        return orders.add(order);
    }

    public boolean addProduct(User user, Order order, Product product) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            return false;
        }
        return orderDB.get().add(product);
    }

    private Optional<Order> findOrder(User user, Order order) {
        return serviceDB.getOrDefault(user, Set.of()).stream()
                .filter(o -> o.getId() == order.getId())
                .findFirst();
    }

    public boolean removeProduct(User user, Order order, Product product) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            return false;
        }
        return orderDB.get().remove(product.getId());
    }

    public boolean removeOrder(User user, Order order) {
        Set<Order> orders = serviceDB.get(user);
        if (orders == null) {
            return false;
        }
        return orders.remove(order);
    }

    public Check payOrder(User user, Order order) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            /**
             * Стоит обратить внимание на подобные строки. Подобная запись также является нарушением DIP,
             * потому что есть прямая зависимость самого логгирования от реализации,
             * в данном случае оно напрямую зависит от консольного вывода.
             * Решение опять использование абстракции для логирования:
             * private static final Logger LOGGER = Logger.getLogger("Shop logger");
             */
            System.out.println("Get error with " + user + " " + order);
            throw new IllegalArgumentException("Invalid order");
        }
        if (orderDB.get().isPayed()) {
            System.out.println("Get error with "  + user + " " + order);
            throw new IllegalArgumentException("Already payed!");
        }
        orderDB.get().setPayed(true);
        return new Check((int) (System.currentTimeMillis() % 1000_000), "Payed: " + orderDB.get().getId());
    }

}
