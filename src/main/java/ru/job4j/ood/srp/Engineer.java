package ru.job4j.ood.srp;

/**
 * Пример #2 нарушения принципа SRP
 * 1. инженер может быть не только строитель
 * 2. логика поедания еды тоже должна быть в отельном классе
 * 3. равно как и метод ехать на работу
 *
 * @author Alex_life
 * @version 1.0
 * @since 24.09.2022
 */
public class Engineer {

    public void builder(String house) {

    }

    public void eat(String plov, int tarelka) {

    }

    public void goToWork(String transport, int time) {

    }

}
