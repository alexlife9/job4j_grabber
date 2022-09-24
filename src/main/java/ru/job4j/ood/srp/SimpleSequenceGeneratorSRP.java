package ru.job4j.ood.srp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;

/**
 *
 * @author Alex_life
 * @version 1.0
 * @since 24.09.2022
 */
public class SimpleSequenceGeneratorSRP implements SequenceGeneratorSRP<Integer> {
    @Override
    public List<Integer> generate(int size) {
        Random random = new Random();
        return IntStream.range(0, size)
                .map(i -> random.nextInt()).boxed()
                .collect(Collectors.toList());
    }
}
