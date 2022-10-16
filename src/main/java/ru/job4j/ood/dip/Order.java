package ru.job4j.ood.dip;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип инверсии зависимостей - Dependency Inversion Principle
 *
 * Входные параметры в классе Order.
 * Во-первых, этот класс нарушает SRP, потому что представляет как саму модель заказа, так и АПИ для работы с ней.
 * Во-вторых, он нарушает DIP, потому что опять же сохранение идет в память, нам нужно здесь аналогично
 * абстрагироваться от самого хранилища, создав для него отдельный интерфейс SimpleOrderService.
 * Но зависимость от хранилища уже будет в сервис заказа, а сервис магазина будет зависеть от сервиса заказов.
 *
 * public class SimpleOrderService implements OrderService {
 *
 *     private OrderStore orderStore;
 *
 *     public SimpleOrderStore(OrderStore orderStore) {
 *         this.orderStore = orderStore;
 *     }
 * }
 *
 * и в классе ShopService:
 *
 * private OrderService orderService;
 *
 * public SimpleShopService(ShopStore shopStore, OrderService orderService) {
 *     this.shopStore = shopStore;
 *     this.orderService = orderService;
 * }
 *
 *
 *
 * @author Alex_life
 * @version 1.0
 * @since 16.10.2022
 */
public class Order {

    private int id;

    private boolean isPayed;

    private Map<Integer, Product> products = new HashMap<>();

    public boolean add(Product product) {
        if (products.containsKey(product.getId())) {
            return false;
        }
        return products.put(product.getId(), product) != null;
    }

    public boolean remove(int id) {
        return products.remove(id) != null;
    }

    public void clear() {
        products.clear();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
