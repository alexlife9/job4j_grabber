package ru.job4j.ood.lsp;

/**
 * Пример нарушения принципа LSP
 * Предусловия (Preconditions) не могут быть усилены в подклассе
 * Постусловия (Postconditions) не могут быть ослаблены в подклассе.
 *
 * @author Alex_life
 * @version 1.0
 * @since 02.10.2022
 */
public class BreachLSP2 {

    /* есть строитель со способностью строить дома */
    static class Builder {
        protected String name;
        protected int age;
        protected String skill = "строить_дома";

        public Builder(String name, int age, String skill) {
            this.name = name;
            this.age = age;
            this.skill = skill;
        }

        public void checkAge() {
            if (age < 18) {
                throw new IllegalArgumentException("приходи когда подрастешь");
            }
        }

        public void checkSkill(String skill) {
            if (!this.skill.equals("строить_дома")) {
                throw new IllegalArgumentException("вы не строите дома");
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSkill() {
            return skill;
        }

        public void setSkill(String skill) {
            this.skill = skill;
        }

        @Override
        public String toString() {
            return "Builder{"
                    + "name='" + name + '\''
                    + ", age=" + age
                    + ", skill='" + skill + '\''
                    + '}';
        }
    }

    static class BuilderChildOne extends Builder {

        public BuilderChildOne(String name, int age, String skill) {
            super(name, age, skill);
        }

        /* в методе checkAge происходит усиление предусловия в классе наследнике - это нарушение LSP
        * Предусловие - это условие, которое проверяет корректность аргументов конструктора или метода */
        @Override
        public void checkAge() {
            if (age < 18 && skill.equals("строить_дома")) {
                throw new IllegalArgumentException("не подходишь");
            }
        }
    }

    static class BuilderChildTwo extends Builder {

        public BuilderChildTwo(String name, int age, String skill) {
            super(name, age, skill);
        }

        /* в методе checkSkill происходит ослабление постусловия в классе наследнике - это нарушение LSP
        * Постусловие - это условие, налагаемое на возвращаемое значение метода */
        public void checkSkill(String skill) {
            System.out.println("я и сам в каком то роде моряк");
        }
    }

    static class BuilderChildThree extends Builder {

        public BuilderChildThree(String name, int age, String skill) {
            super(name, age, skill);
        }

        /* не соблюдаются условия предка - нарушается инвариантность.
        * Инвариант - это условие, которое постоянно на протяжении существования объекта.
        * Все условия базового класса также должны быть сохранены и в подклассе */
        @Override
        public void setSkill(String skill) {
            super.setSkill(skill);
        }
    }

}
