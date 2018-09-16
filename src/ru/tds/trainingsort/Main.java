package ru.tds.trainingsort;

import java.io.*;
import java.util.*;

/**
 * Класс, в котором реализовано сортировка массива по частоте повторений числа по убыванию.
 * Если 2 элемента имеют одинаковую частоту, выводится тот, который был первым.
 * <p>
 * Date: 06.09.2018 (четверг)
 * Project name: TrainingSort
 * Package name: ru.tds.trainingsort
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Main {

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";
    private static final String REGEX = " ";

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = readFromFile();
        System.out.println("Исходный массив: " + arrayList);
        sortArrayByFrequency(arrayList);
        System.out.println("Отсортированный массив: " + arrayList);
        writeArrayToFile(arrayList);
    }

    /**
     * Метод для чтения содержимого файла fileName.
     *
     * @return ArrayList с числами, прочитанными из файла
     */
    private static ArrayList<Integer> readFromFile() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String string;
            String[] splitString;
            while ((string = reader.readLine()) != null) {
                splitString = string.split(REGEX);
                for (String aSplitString : splitString) {
                    arrayList.add(Integer.parseInt(aSplitString));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }


    /**
     * Сортирует ArrayList по частоте повторения в нём чисел в порядке убывания.
     *
     * @param array список, который нужно отсортировать
     */
    private static void sortArrayByFrequency(ArrayList<Integer> array) {
        ArrayList<NumberWithFrequency> tmpArrayList = new ArrayList<>();
        for (int num : array) {
            boolean foundedRepeat = false;
            for (NumberWithFrequency currentNumber : tmpArrayList) {
                if (num == currentNumber.value) {
                    foundedRepeat = true;
                    currentNumber.frequency++;
                    break;
                }
            }
            if (!foundedRepeat) {
                tmpArrayList.add(new NumberWithFrequency(num));
            }
        }
        sortNumbersWithFrequency(tmpArrayList);
        array.clear();
        for (NumberWithFrequency currentNum : tmpArrayList) {
            while (currentNum.frequency-- > 0) {
                array.add(currentNum.value);
            }
        }
    }

    /**
     * Сортирует ArrayList с объектами NumberWithFrequency по полю frequency в порядке убывания.
     *
     * @param arrayList ArrayList с объектами NumberWithFrequency
     */
    private static void sortNumbersWithFrequency(ArrayList<NumberWithFrequency> arrayList) {
        NumberWithFrequency tmp;
        int start = 0;
        int end = arrayList.size() - 1;
        do {
            for (int i = 0; i < end; i++) {
                if (arrayList.get(i).frequency < arrayList.get(i + 1).frequency) {
                    tmp = arrayList.get(i);
                    arrayList.set(i, arrayList.get(i + 1));
                    arrayList.set(i + 1, tmp);
                }
            }
            end--;
            for (int i = end; i > start; i--) {
                if (arrayList.get(i).frequency > arrayList.get(i - 1).frequency) {
                    tmp = arrayList.get(i);
                    arrayList.set(i, arrayList.get(i - 1));
                    arrayList.set(i - 1, tmp);
                }
            }
            start++;
        } while (start < end);
    }


    /**
     * Метод для записи списка в текстовый файл output.txt
     *
     * @param arrayList список, который нужно записать в текстовый файл
     */
    private static void writeArrayToFile(ArrayList<Integer> arrayList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            for (int element : arrayList) {
                writer.write(String.valueOf(element) + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Класс объекта NumberWithFrequency, в котором хранится число и кол-во его повторений в списке.
     */
    private static class NumberWithFrequency {

        int value; // само число

        int frequency; // кол-во его повторений в списке

        NumberWithFrequency(int value) {
            this.frequency = 1;
            this.value = value;
        }
    }

}
