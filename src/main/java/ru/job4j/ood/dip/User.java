package ru.job4j.ood.dip;

/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип инверсии зависимостей - Dependency Inversion Principle
 *
 * @author Alex_life
 * @version 1.0
 * @since 16.10.2022
 */
public class User extends BaseEntity {
    public User(int id, String name) {
        super(id, name);
    }
}
