package ru.job4j.ood.isp;

import java.util.Iterator;

/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип разделения интерфейсов - Interface Segregation Principle
 *
 * смотри Figure
 *
 * Пример 2.
 * Деревья поддерживают различные обходы, поэтому написание интерфейса Traversable является нарушением ISP.
 * Всем деревьям придется реализовывать все обходы (либо наследоваться от абстрактного дерева которое это делает).
 * Но опять же другие программные сущности попросту не будут их использовать, но будут зависеть от них.
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.10.2022
 */
public interface Traversable<T> {

    Iterator<T> preOrder();
    Iterator<T> inOrder();
    Iterator<T> postOrder();

    Iterator<T> bfs();
    Iterator<T> dfs();
}

/* Последние два обхода на самом деле обходы для графов (дерево частный случай графа),
а первые три именно для деревьев, поэтому исправить можно следующим образом: */
interface TreeTraversable<T> {
    Iterator<T> preOrder();
    Iterator<T> inOrder();
    Iterator<T> postOrder();
}

interface GraphTraversable<T> {
    Iterator<T> bfs();
    Iterator<T> dfs();
}
