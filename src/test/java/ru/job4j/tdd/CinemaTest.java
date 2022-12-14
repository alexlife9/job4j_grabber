package ru.job4j.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
 * Чтобы описать тесты, потребовалось создать пустые классы реализующие нужные интерфейсы.
 *
 *
 * Принцип AAA
 * Автоматизированное тестирование – это процесс написания тестов в виде повторно запускаемого блока кода.
 * Тест – это проверка заведомо нужного поведения программы.
 * В качестве противопоставления автоматизированному тестированию существует ручное тестирование. Оно предполагает,
 * что тест выполняется не компьютером, а человеком, он сам делает проверки и решает, что верно, а что нет.
 * Например, когда запускаем main, мы смотрим как ваша программа работает и что-то проверяем,
 * то этот процесс можно назвать ручным тестированием.
 *
 * Все тесты базируются на принципе AAA – Arrange Act Assert.
 * Arrange – указание входных и ожидаемых данных программы.
 * Act – выполнение программы путем передачи входных и получение выходных данных.
 * Assert – сравнение выходных и ожидаемых данных.
 *
 * @author Alex_life
 * @version 3.0
 * @since 12.09.2022
 */
@Disabled
public class CinemaTest {

    @Test
    public void whenBuy3D() {
        /* класс AccountCinema реализует интерфейс Account */
        Account account = new AccountCinema();

        /* класс Cinema3D реализует интерфейс Cinema */
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();

        /* в классе Cinema3D будет метод buy для покупки билетов на сеансы 3D-фильмов */
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new TicketUniversal());
    }

    @Test
    public void whenBuyNormal() {
        Account account = new AccountCinema();
        Cinema cinema = new CinemaNormal();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new TicketUniversal());
    }

    @Test
    public void whenFind3D() {
        Cinema cinema = new Cinema3D();
        /* в классе Cinema3D будет метод add для брони билетов */
        cinema.add(new Session3D());

        /* в классе Cinema3D будет метод find для поиска пустого места в зале */
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isNull();
    }

    @Test
    public void whenFindNormal() {
        Cinema cinema = new CinemaNormal();
        cinema.add(new SessionNormal());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isNull();
    }

    /* проверка возможности покупки на указанное место */
    @Test()
    public void whenInvalidPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D(); /* на 3D фильм. можно добавить тоже самое на обычный фильм */
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, -1, 1, date);
        });
    }

    /* проверка возможности покупки на указанную дату */
    @Test()
    public void whenInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar freedate = new GregorianCalendar(2000, 12, 01);
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 1, 1, freedate);
        });
    }

    /* проверка невозможности покупки двух билетов разными людьми на одно место и на одно время */
    @Test()
    public void whenTwoTicketsOnePlaceOneDate() {
        Account account = new AccountCinema();
        Account account2 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2000, 12, 27, 17, 30);
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 1, 1, date);
            cinema.buy(account2, 1, 1, date);
        });
    }

}