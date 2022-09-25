package ru.job4j.ood.ocp;

/**
 * Пример №1 нарушение OCP
 * Если понадобится изменить параметры при получении памяти, то нужно будет менять логику метода, что может поломать
 * уже написанные реализации
 *
 * @author Alex_life
 * @version 1.0
 * @since 25.09.2022
 */
public class Memory {

    public void getMem(Mem mem) {

    }

    static class Mem {

    }
}
