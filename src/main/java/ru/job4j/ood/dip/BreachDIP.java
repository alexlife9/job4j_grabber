package ru.job4j.ood.dip;

/**
 * Пример нарушения Принципа инверсии зависимостей - Dependency Inversion Principle
 *
 * @author Alex_life
 * @version 1.0
 * @since 16.10.2022
 */
public class BreachDIP {

    /* есть абстрактный класс Строитель
    * от него реализуем каменщика, электрика и маляра */
    public abstract class Builder {
        private String name;

        public Builder(String name) {
            this.name = name;
        }
    }

    class Stonemason extends Builder {
        public Stonemason(String name) {
            super(name);
        }
    }

    class Electrician extends Builder {
        public Electrician(String name) {
            super(name);
        }
    }

    class Painter extends Builder {
        public Painter(String name) {
            super(name);
        }
    }

    /* есть интерфейс с какой-то работой */
    interface Work {
        boolean work(Builder builder);
    }

    class LayingBricks implements Work {
        @Override
        public boolean work(Builder stonemason) {
            return false;
        }
    }

    class ConductElectricity implements Work {
        @Override
        public boolean work(Builder electric) {
            return false;
        }
    }

    class Coloring implements Work {
        @Override
        public boolean work(Builder painter) {
            return false;
        }
    }

    /* у нас есть строители и мы хотим построить дом */
    class BuildingHhouse {

        /* для этого мы собираем всех вместе
        * и это будет 1.Нарушение DIP:
        * поля представлены не абстракциями, а реализациями */
        private Stonemason stonemason;
        private Electrician electrician;
        private Painter painter;

        private LayingBricks layingBricks;
        private ConductElectricity conductElectricity;
        private Coloring coloring;

        /* 2. Нарушение DIP:
        * аргументы конструктора представлены не абстракциями, а реализациями */
        public BuildingHhouse(Stonemason stonemason, Electrician electrician, Painter painter,
                              LayingBricks layingBricks, ConductElectricity conductElectricity, Coloring coloring) {
            this.stonemason = stonemason;
            this.electrician = electrician;
            this.painter = painter;
            this.layingBricks = layingBricks;
            this.conductElectricity = conductElectricity;
            this.coloring = coloring;
        }

        public void wallFinishing(Electrician electrician, ConductElectricity conductElectricity) {
            /* тут 3. Нарушение DIP, потому что что аргументы метода преставлены реализациями, а не абстракциями
            * из-за этого мы не можем в методе "ОтделкаСтены" задействовать маляров */
        }

        public Coloring coloring(Builder builder) {
            Coloring coloring1 = new Coloring();
            /* 4. Нарушение DIP - возвращаемое значение представлено конкретной реализацией, а не абстракцией */
            return coloring1;
        }
    }
}
