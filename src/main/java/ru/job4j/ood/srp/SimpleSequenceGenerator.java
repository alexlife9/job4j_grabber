package ru.job4j.ood.srp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;
/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип единственной ответственности - Single Responsibility Principle
 *
 * смотри SequenceGenerator
 *
 * @author Alex_life
 * @version 1.0
 * @since 24.09.2022
 */
public class SimpleSequenceGenerator implements SequenceGenerator<Integer> {
    @Override
    public List<Integer> generate(int size) {
        Random random = new Random();
        return IntStream.range(0, size)
                .map(i -> random.nextInt()).boxed()
                .collect(Collectors.toList());
    }

    @Override
    public void print(List<Integer> numbers) {
        numbers.forEach(System.out::println);
    }
}
