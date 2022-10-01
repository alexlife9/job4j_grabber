package ru.job4j.ood.lsp.controlfood;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Хранилище продуктов
 *
 * @author Alex_life
 * @version 3.0
 * @since 01.10.2022
 */
class ControlQualityTest {
    LocalDate expiryDate;
    LocalDate createDate;
    Warehouse warehouse;
    Shop shop;
    Trash trash;
    private ControlQuality controlQuality;

    @BeforeEach
    public void init() {
        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();
        controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
    }

    @Test
    public void whenWarehouse() {
        expiryDate = LocalDate.now().plusDays(30); /* сдохнет через 30 дней */
        createDate = LocalDate.now().minusDays(2); /* сделано 2 дня назад */
        Food fish = new Fish("Fish", expiryDate, createDate, 100.0, 20);
        controlQuality.distribution(fish);
        assertThat(warehouse.getFoods().contains(fish)).isTrue();
    }

    @Test
    public void whenShop() {
        expiryDate = LocalDate.now().plusDays(20); /* сдохнет через 20 дней */
        createDate = LocalDate.now().minusDays(30); /* сделано 30 дней назад */
        Food meat = new Meat("Meat", expiryDate, createDate, 100.0, 20);
        controlQuality.distribution(meat);
        assertThat(shop.getFoods().contains(meat)).isTrue();
    }

    @Test
    public void whenTrash() {
        expiryDate = LocalDate.now().plusDays(0); /* сдохло сегодня */
        createDate = LocalDate.now().minusDays(30); /* сделано 30 дней назад */
        Food milk = new Milk("Milk", expiryDate, createDate, 100.0, 20);
        controlQuality.distribution(milk);
        assertThat(trash.getFoods().contains(milk)).isTrue();
    }

    @Test
    public void whenShopDiscount() {
        expiryDate = LocalDate.of(2022, 10, 5); /* сдохнет через 5 дней */
        createDate = LocalDate.of(2022, 9, 10); /* сделано 20 дней назад */
        Food fish = new Fish("Fish", expiryDate, createDate, 120.0, 30);
        controlQuality.distribution(fish);
        assertThat(shop.getFoods().contains(fish)).isTrue();
        assertThat(fish.getPrice()).isEqualTo(84);
    }

    @Test
    public void when2Shop1Discount() {
        Food fish = new Fish("Fish", LocalDate.now().plusDays(20), LocalDate.now().minusDays(30), 100.0, 50);
        Food milk = new Milk("Milk", LocalDate.now().plusDays(5), LocalDate.now().minusDays(25), 150.0, 30);
        controlQuality.distribution(fish);
        controlQuality.distribution(milk);
        assertThat(shop.getFoods().contains(fish)).isTrue();
        assertThat(shop.getFoods().contains(milk)).isTrue();
        assertThat(fish.getPrice()).isEqualTo(100);
        assertThat(milk.getPrice()).isEqualTo(105);
    }

    @Test
    public void whenDistribution() {
        Food fish = new Fish("Fish", LocalDate.now().plusDays(30), LocalDate.now().minusDays(1), 100.0, 50);
        Food milk = new Milk("Milk", LocalDate.now().plusDays(0), LocalDate.now().minusDays(30), 100.0, 50);
        Food meat = new Meat("Meat", LocalDate.now().plusDays(30), LocalDate.now().minusDays(30), 100.0, 50);
        Food meatD = new Meat("Meat", LocalDate.now().plusDays(5), LocalDate.now().minusDays(25), 200.0, 40);
        controlQuality.distribution(fish);
        controlQuality.distribution(milk);
        controlQuality.distribution(meat);
        controlQuality.distribution(meatD);
        assertThat(warehouse.getFoods().contains(fish)).isTrue();
        assertThat(trash.getFoods().contains(milk)).isTrue();
        assertThat(shop.getFoods().contains(meat)).isTrue();
        assertThat(shop.getFoods().contains(meatD)).isTrue();
        assertThat(fish.getPrice()).isEqualTo(100);
        assertThat(milk.getPrice()).isEqualTo(100);
        assertThat(meat.getPrice()).isEqualTo(100);
        assertThat(meatD.getPrice()).isEqualTo(120);
    }

}