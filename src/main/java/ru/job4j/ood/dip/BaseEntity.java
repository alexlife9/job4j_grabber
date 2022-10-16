package ru.job4j.ood.dip;

import java.util.Objects;

/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип инверсии зависимостей - Dependency Inversion Principle
 *
 * Все шаблоны проектирования были придуманы как раз таким образом, чтобы код не только решал некоторую задачу дизайна,
 * но и соответствовал принципам SOLID. Таким образом,понимание этих принципов делает очевидными шаблоны проектирования.
 *
 * SOLID – это аббревиатура, составленная из первых букв названий принципов:
 * S – Single Responsibility Principle
 * O – Open-Closed Principle
 * L – Liskov Substitution Principle
 * I – Interface Segregation Principle
 * D – Dependency Inversion Principle
 *
 * Принцип инверсии зависимостей гласит:
 * Модули верхнего уровня не должны зависеть от модулей нижнего уровня. И те и другие должны зависеть от абстракций.
 * Абстракции не должны зависеть от деталей. Детали должны зависеть от абстракций.
 *
 * Проще говоря, используемые сущности в вашем коде должны быть абстракциями,
 * т.е. не должно быть прямой зависимости от реализации.
 *
 * Надо обращать внимание на:
 * - поля классов
 * - возвращаемые значения методов
 * - аргументы методов и конструкторов
 * - создаваемые объекты (вспомните пример из урока про SRP)
 *
 * Чаще всего это не касается моделей данных бизнес-логики, т.е. классов типа Item, Post, User, Role и т.д, однако если
 * они также содержат какую-то логику, то правило также действует и на них.
 *
 * Рассмотрим такой пример. Пусть нам нужно написать сервис простого интернет магазина:
 * базовая сущность - BaseEntity
 * модели - User, Product, Order, Check
 * сервис интернет магазина - SimpleShopService
 *
 * @author Alex_life
 * @version 1.0
 * @since 16.10.2022
 */
public abstract class BaseEntity {

    protected int id;

    protected String name;

    public BaseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}