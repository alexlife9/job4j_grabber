package ru.job4j.map;

import java.util.*;

/**
 * Аттестация учеников
 * Класс AnalyzeByMap получает статистику по аттестатам
 *
 * 1. Метод averageScore() - вычисляет общий средний балл.
 * В этом методе необходимо найти сумму баллов по всем предметам у всех учеников, при этом считая количество
 * суммируемых элементов - после этих подсчетов мы просто делим общий балл на количество суммируемых элементов.
 *
 * 2. Метод averageScoreByPupil() - вычисляет средний балл по каждому ученику. То есть берем одного ученика
 * и считаем все его баллы за все предметы и делим на количество предметов.
 * Возвращает список из объекта Label (имя ученика и средний балл).
 * Реализация этого метода будет отличаться от предыдущего тем, что мы считаем сумму баллов по всем предметам
 * каждого ученика. Получив сумму баллов, мы создаем объект типа Label с именем ученика, а в качестве второго параметра
 * в конструктор передаем сумму баллов по предметам, разделенную на количество предметов ученика.
 * Полученный объект добавляем в список, который и возвращаем в конце метода;
 *
 * 3. Метод averageScoreBySubject() - вычисляет средний балл по каждому предмету. Например, собираем все баллы учеников
 * по предмету география и делим на количество учеников.
 * Возвращает список из объектов Label (название предмета и средний балл).
 * Чтобы реализовать данный метод, надо собрать временную Map<String, Integer> (в качестве реализации используем
 * LinkedHashMap) - в качестве ключа используем название предмета, в качестве значения - сумма баллов по этому
 * предмету, которую мы получим у каждого ученика.
 * Далее мы перебираем пары ключ-значение в цикле и на их основе создаем объекты типа Label -
 * в качестве первого параметра передаем ключ карты,
 * в качестве второго - значение карты разделенное на количество учеников.
 * Созданный объект добавляем в результирующий список, его мы и вернем в конце метода.
 *
 * 4. Метод bestStudent() - возвращает лучшего ученика. Лучшим считается ученик с наибольшим суммарным баллом по всем
 * предметам. Возвращает объект Label (имя ученика и суммарный балл).
 * Собираем список объектов Label за одним исключением - среднее значение баллов считать не нужно, мы оставляем
 * подсчитанную сумму баллов как есть. Далее мы сортируем список с помощью Comparator.naturalOrder()
 * (именно для этой цели у Label реализован интерфейс Comparable). Поскольку нам нужен в итоге лучший студент,
 * то в качестве результата мы возвращаем последний элемент из списка.
 *
 * 5. Метод bestSubject() - возвращает предмет с наибольшим баллом для всех студентов.
 * Возвращает объект Label (имя предмета, сумма баллов каждого ученика по этому предмету).
 * В этом методе, как и в методе averageScoreBySubject(), необходимо собрать временную карту.
 * Далее эту карту перебираем в виде пар ключ-значение и создаем объекты типа Label -
 * в качестве первого параметра используем ключ,
 * для второго - значение карты.
 * После этого мы сортируем список с помощью Comparator.naturalOrder() и в итоге возвращаем последний элемент
 * из полученного списка.
 *
 * @author Alex_life
 * @version 1.0
 * @since 27.10.2022
 */
public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double sumAllScore = 0;
        int countAllSubj = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sumAllScore += subject.score();
                countAllSubj++;
            }
        }
        return sumAllScore / countAllSubj;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> labelPupil = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sumScoreOnePupil = 0;
            int countAllSubjThisPupil = 0;
            for (Subject subject : pupil.subjects()) {
                sumScoreOnePupil += subject.score();
                countAllSubjThisPupil++;
            }
            labelPupil.add(new Label(pupil.name(), sumScoreOnePupil / countAllSubjThisPupil));
        }
        return labelPupil;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> tempMap = new LinkedHashMap<>();
        List<Label> labelSubj = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                tempMap.merge(subject.name(), subject.score(), Integer::sum);
            }
        }
        for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
            labelSubj.add(new Label(entry.getKey(), (double) entry.getValue() / pupils.size()));
        }
        return labelSubj;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> labelPupil = new ArrayList<>();
        double sumAllScoreThisPupil = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sumAllScoreThisPupil += subject.score();
            }
            labelPupil.add(new Label(pupil.name(), sumAllScoreThisPupil));
            labelPupil.sort(Comparator.naturalOrder());
        }
        return labelPupil.get(0);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> tempMap = new LinkedHashMap<>();
        List<Label> labelSubj = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                tempMap.merge(subject.name(), subject.score(), Integer::sum);
            }
        }
        for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
            labelSubj.add(new Label(entry.getKey(), entry.getValue()));
            labelSubj.sort(Comparator.naturalOrder());
        }
        return labelSubj.get(labelSubj.size() - 1);
    }
}
