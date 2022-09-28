package ru.job4j.ood.lsp.controlfood;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Хранилище продуктов
 *
 * @author Alex_life
 * @version 1.0
 * @since 28.09.2022
 */
class ControlQualityTest {
    Calendar expiryDate = Calendar.getInstance();
    Calendar createDate = Calendar.getInstance();
    Warehouse warehouse = new Warehouse();
    Shop shop = new Shop();
    Trash trash = new Trash();
    List<Store> foodList = List.of(warehouse, shop, trash);
    ControlQuality controlQuality = new ControlQuality(foodList);

    @Test
    public void whenWarehouse() {
        expiryDate.add(Calendar.DAY_OF_MONTH, 30); /* сдохнет через 30 дней */
        createDate.add(Calendar.DAY_OF_MONTH, -2); /* сделано 2 дня назад */
        Food fish = new Fish("Fish", expiryDate, createDate, 99.9, 20);
        controlQuality.distribution(fish);
        assertThat(warehouse.getFoods().get(0).getName()).isEqualTo("Fish");
        assertThat(shop.getFoods().size()).isEqualTo(0);
        assertThat(trash.getFoods().size()).isEqualTo(0);
    }

    @Test
    public void whenShop() {
        expiryDate.add(Calendar.DAY_OF_MONTH, 20); /* сдохнет через 20 дней */
        createDate.add(Calendar.DAY_OF_MONTH, -30); /* сделано 30 дней назад */
        Food meat = new Meat("Meat", expiryDate, createDate, 99.9, 20);
        controlQuality.distribution(meat);
        assertThat(warehouse.getFoods().size()).isEqualTo(0);
        assertThat(shop.getFoods().get(0).getName()).isEqualTo("Meat");
        assertThat(trash.getFoods().size()).isEqualTo(0);
    }

    @Test
    public void whenTrash() {
        expiryDate.add(Calendar.DAY_OF_MONTH, 0); /* сдохло сегодня */
        createDate.add(Calendar.DAY_OF_MONTH, -30); /* сделано 30 дней назад */
        Food milk = new Milk("Milk", expiryDate, createDate, 99.9, 20);
        controlQuality.distribution(milk);
        assertThat(warehouse.getFoods().size()).isEqualTo(0);
        assertThat(shop.getFoods().size()).isEqualTo(0);
        assertThat(trash.getFoods().get(0).getName()).isEqualTo("Milk");
    }
}