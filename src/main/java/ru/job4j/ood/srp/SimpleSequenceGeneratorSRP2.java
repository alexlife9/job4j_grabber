package ru.job4j.ood.srp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
public class SimpleSequenceGeneratorSRP2 implements SequenceGeneratorSRP<Integer> {

    private final NumberGenerator<Integer> numberGenerator;

    public SimpleSequenceGeneratorSRP2(NumberGenerator<Integer> numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public List<Integer> generate(int size) {
        return IntStream.range(0, size)
                .map(i -> numberGenerator.generate()).boxed()
                .collect(Collectors.toList());
    }
}
