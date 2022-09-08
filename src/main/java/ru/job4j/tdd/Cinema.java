package ru.job4j.tdd;

/**
 * Что такое TDD
 *
 * Традиционно программисты сразу пишут полезный код, который сразу выполняет требования клиента.
 * Недостаток такого подхода в том, что можно ошибиться в дизайне. Если такое произойдет,
 * то нужно переписать уже проверенный код. Делать редизайн.
 *
 * Другой подход создания программы - это начать описывать поведение системы через тесты.
 * В таком подходе мы фокусируемся на возможности системы, а не на ее реализацию.
 * Любую систему можно описать через интерфейсы взаимодействия.
 * Такой поход называется программирование через тесты - Test Driven Development.
 *
 * Package tdd описывает кинотеатр и содержит интерфейсы:
 * 1. Киносеанс -  ru.job4j.tdd.Session
 * 2. Посетитель - ru.job4j.tdd.Account
 * 3. Билет - ru.job4j.tdd.Ticket
 * 4. Зал кинотеатра - ru.job4j.tdd.Cinema
 *
 * После этого создаются тесты которые ДО написания кода сразу прогнозируют и проверяют работу кинотеатра
 *
 * @author Alex_life
 * @version 1.0
 * @since 08.09.2022
 */
public interface Cinema {
}
