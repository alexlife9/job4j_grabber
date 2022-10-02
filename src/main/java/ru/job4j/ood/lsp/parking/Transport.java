package ru.job4j.ood.lsp.parking;

/**
 * Парковка машин
 *
 * ТЗ:
 * Существует общая парковка для грузовых и легковых машин.
 * Количество парковочных мест для каждого типа машин задается на этапе создания парковки.
 *
 * Легковая машина может занять только место, предназначенное для легковой машины.
 * Грузовая машина может разместиться на месте, предназначенном для грузовых машин,
 * либо на N парковочных мест для легковых машин, стоящих рядом.
 * Важно! Легковой считается машина у которой размер равен 1, а грузовой у которой размер > 1.
 * Необходимо разработать сервис для учета парковки машин.
 *
 * Реализация:
 * 1. Делаем интерфейсы Transport и Parking
 * 2. Реализуем классы Car и Truck от интерфейса Transport
 * 3. Реализуем класс CarParking и TruckParking
 *
 * @author Alex_life
 * @version 1.0
 * @since 01.10.2022
 */
public interface Transport {
}