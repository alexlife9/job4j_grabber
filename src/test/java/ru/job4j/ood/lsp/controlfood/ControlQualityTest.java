package ru.job4j.ood.lsp.controlfood;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Хранилище продуктов
 *
 * @author Alex_life
 * @version 2.0
 * @since 30.09.2022
 */
class ControlQualityTest {
    LocalDate expiryDate;
    LocalDate createDate;
    Warehouse warehouse;
    Shop shop;
    Trash trash;
    private ControlQuality controlQuality;

    @Before
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
/*
    @Test
    public void whenShop() {
        expiryDate.add(Calendar.DAY_OF_MONTH, 20); *//* сдохнет через 20 дней *//*
        createDate.add(Calendar.DAY_OF_MONTH, -30); *//* сделано 30 дней назад *//*
        Food meat = new Meat("Meat", expiryDate, createDate, 100.0, 20);
        controlQuality.distribution(meat);
        assertThat(warehouse.getFoods().contains(meat)).isTrue();
    }

    @Test
    public void whenTrash() {
        expiryDate.add(Calendar.DAY_OF_MONTH, 0); *//* сдохло сегодня *//*
        createDate.add(Calendar.DAY_OF_MONTH, -30); *//* сделано 30 дней назад *//*
        Food milk = new Milk("Milk", expiryDate, createDate, 100.0, 20);
        controlQuality.distribution(milk);
        assertThat(warehouse.getFoods().contains(milk)).isTrue();
    }

    @Test
    public void whenWarehousedDiscount() {
        expiryDate.add(Calendar.DAY_OF_MONTH, 0); *//* сдохло сегодня *//*
        createDate.add(Calendar.DAY_OF_MONTH, -30); *//* сделано 30 дней назад *//*
        Food milk = new Milk("Milk", expiryDate, createDate, 120.0, 20);
        controlQuality.distribution(milk);
        assertThat(warehouse.getFoods().contains(milk)).isTrue();
        assertThat(milk.getPrice()).isEqualTo(84);
    }*/

}