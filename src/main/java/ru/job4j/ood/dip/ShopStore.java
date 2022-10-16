package ru.job4j.ood.dip;

import java.util.Set;

/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип инверсии зависимостей - Dependency Inversion Principle
 *
 * @author Alex_life
 * @version 1.0
 * @since 16.10.2022
 */
public interface ShopStore {

    boolean saveUser(User user);
    boolean saveOrder(User user, Order order);
    Set<Order> getOrders(User user);
    Set<User> getUsers();

}
