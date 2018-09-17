package ru.tds.trainingsort;

/**
 * Класс объекта NumberWithFrequency, в котором хранится число и кол-во его повторений в списке.
 *
 * Date: 17.09.2018 (понедельник)
 * Project name: TrainingSort
 * Package name: ru.tds.trainingsort
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
class NumberWithFrequency {

        int value; // само число

        int frequency; // кол-во его повторений в списке

        NumberWithFrequency(int value) {
            this.frequency = 1;
            this.value = value;
        }

}
