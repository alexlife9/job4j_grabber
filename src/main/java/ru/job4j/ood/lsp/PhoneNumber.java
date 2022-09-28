package ru.job4j.ood.lsp;

/**
 * Принципы ООД (объектно-ориентированного дизайна)
 * Принцип подстановки Лисков - Liskov Substitution Principle
 *
 * смотри AutoTransport
 *
 * @author Alex_life
 * @version 1.0
 * @since 27.09.2022
 */
class PhoneNumber {
    /* Рассмотрим 3. Инварианты.
    Например, есть номер телефона. Есть абонент. От него наследуется абонент какого-то оператора.
    Но при переопределении сеттера, забыли сделать проверку. Поэтому код в машине запускается успешно.
    Ошибка остается. Нарушено состояние объекта потомка, потому что в нем не соблюдено условие предка. */

    private final int countryCode;

    private final int cityCode;

    private final int number;

    public PhoneNumber(int countryCode, int cityCode, int number) {
        this.countryCode = countryCode;
        this.cityCode = cityCode;
        this.number = number;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public int getCityCode() {
        return cityCode;
    }

    public int getNumber() {
        return number;
    }
}

/* абонент */
class Subscriber {

    protected PhoneNumber phoneNumber;

    public Subscriber(PhoneNumber phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    protected void validate(PhoneNumber phoneNumber) {
        if (phoneNumber.getCountryCode() < 1 || phoneNumber.getCountryCode() > 999) {
            throw new IllegalArgumentException("Invalid country code!");
        }
        if (phoneNumber.getCityCode() < 1 || phoneNumber.getCityCode() > 999) {
            throw new IllegalArgumentException("Invalid city code!");
        }
        if (phoneNumber.getNumber() < 1 || phoneNumber.getNumber() > 999_999_999) {
            throw new IllegalArgumentException("Invalid number!");
        }
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }
}

class SomeOperatorSubscriber extends Subscriber {

    public SomeOperatorSubscriber(PhoneNumber phoneNumber) {
        super(phoneNumber);
    }

    @Override
    public void setPhoneNumber(PhoneNumber phoneNumber) {
        /* some specific logic; */
        /* Забыли сделать проверку. Возможно не валидное состояние */
        this.phoneNumber = phoneNumber;
    }
}

class ThirdRule {
    public static void main(String[] args) {
        Subscriber subscriber = new SomeOperatorSubscriber(
                new PhoneNumber(+1, 111, 111_111_111)
        );
        subscriber.setPhoneNumber(
                new PhoneNumber(-1, 111, 111_111_111)
        );
    }
}
