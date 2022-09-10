package ru.job4j.template;

import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Шаблонизатор
 *
 * @author Alex_life
 * @version 1.0
 * @since 10.09.2022
 */
@Disabled
class GeneratorTest {
    private final static String TEMPLATE = "I am a ${name}, Who are ${subject}?";

    @Test
    public void whenGeneratedCorrect() {
        Generator generator = new GeneratorTemplate();
        Map<String, String> args = Map.of(
                "name", "Petr",
                "subject", "you");
        String expected = "I am a Petr, Who are you?";
        String result = generator.produce(TEMPLATE, args);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenGeneratedIncorrectKey() {
        Generator generator = new GeneratorTemplate();
        Map<String, String> args = Map.of(
                "name", "Petr",
                "surname", "you");
        Assert.assertThrows(IllegalArgumentException.class, () -> generator.produce(TEMPLATE, args));
    }

    @Test
    public void whenGeneratedOverKey() {
        Generator generator = new GeneratorTemplate();
        Map<String, String> args = Map.of(
                "name", "Petr",
                "subject", "you",
                "surname", "Arsentev");
        Assert.assertThrows(IllegalArgumentException.class, () -> generator.produce(TEMPLATE, args));
    }
}