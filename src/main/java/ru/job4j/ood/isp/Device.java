package ru.job4j.ood.isp;

/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип разделения интерфейсов - Interface Segregation Principle
 *
 * смотри Figure
 *
 * Пример 3.
 * При больших интерфейсах программа становится сильносвязной,
 * так как при изменении интерфейса придется внести изменения в реализации.
 *
 * Попробуем спроектировать интерфейс устройства
 *
 * @author Alex_life
 * @version 1.0
 * @since 03.10.2022
 */
public interface Device {
    void in(String data);
    void calculate();
    void output();
}

class Computer implements Device {

    private String buffer;

    @Override
    public void in(String data) {
        this.buffer = data;
    }

    @Override
    public void calculate() {
        this.buffer = "Calc by computer: " + buffer;
    }

    @Override
    public void output() {
        System.out.println(buffer);
    }

}

/* Обратите внимание, что у сервера нет устройств ввода/вывода. Но ему приходиться поддерживать интерфейс Device.*/
class Server implements Device {

    @Override
    public void in(String data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void calculate() {
        System.out.println("Do some work!");
    }

    @Override
    public void output() {
        throw new UnsupportedOperationException();
    }
}

class TV implements Device {

    private String command;

    @Override
    public void in(String data) {
        this.command = command;
    }

    @Override
    public void calculate() {
        System.out.println("Execute: " + command);
    }

    @Override
    public void output() {
        System.out.println("Show TV program");
    }
}

/* А что если нам нужно добавить метод подключения к интернету connect().
Во-первых, придется реализовать его в существующих классах.
Во-вторых, опять может возникнуть ситуация, что не все устройства поддерживают подключение к интернету.
Какое здесь решение? Разделение интерфейсов, причем нам нужно подписать под нужные интерфейсы только нужные реализации.
Например, для сервера только Calculator, Internet */
interface Input {
    void in(String data);
}

interface Output {
    void output();
}

interface Calculator {
    void calculate();
}

interface Internet {
    void connect();
}

class ServerNew implements Calculator, Internet {

    @Override
    public void calculate() {
        System.out.println("Do work!");
    }

    @Override
    public void connect() {
        System.out.println("Try connect ...");
    }
}