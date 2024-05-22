package org.example;

import org.example.Human;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // создание списка объектов humans
        List <Human> humans = Arrays.asList(
                new Human(25, "John", "Doe", LocalDate.of(1995, 6, 25), 70),
                new Human(30, "Alice", "Smith", LocalDate.of(1990, 7, 10), 65),
                new Human(20, "Bob", "Johnson", LocalDate.of(2000, 8, 15), 80)
        );

        List <Human> sortedByName = humans.stream()
                .sorted((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()))
                .collect(Collectors.toList());
        System.out.println("Сортировка по имени:");
        sortedByName.forEach(System.out::println);
        System.out.println();

        List <Human> bornAfter2000June24 = humans.stream()
                .filter(date -> date.getBirthDate().isAfter(LocalDate.of(2000, 6, 24)))
                .collect(Collectors.toList());
        System.out.println("Фильтрация по возрасту:");
        bornAfter2000June24.forEach(System.out::println);
        System.out.println();

        List <Human> sortedByLastName = humans.stream()
                .sorted((p1, p2) -> p1.getLastName().compareTo(p2.getLastName()))
                .collect(Collectors.toList());
        System.out.println("Сортировка по фамилии:");
        sortedByLastName.forEach(System.out::println);
        System.out.println();

        int agesSum = humans.stream().mapToInt(Human::getAge).sum();
        System.out.println("Сумма всех возрастов: " + agesSum);
    }
}
