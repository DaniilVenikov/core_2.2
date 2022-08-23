package ru.netology.venikov_d_i;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        Consumer<List<String>> print = System.out::println;

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }

        Stream<Person> stream1 = persons.stream();
        long count = stream1
                .filter(p -> p.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + count);

        Stream<Person> stream2 = persons.stream();
        List<String> familiesList = stream2
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .filter(p -> p.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        print.accept(familiesList);

        Stream<Person> stream3 = persons.stream();
        List<String> workers = stream3
                .filter(p -> p.getSex() == Sex.MAN && p.getAge() >= 18 && p.getAge() <= 65 || p.getSex() == Sex.WOMAN
                        && p.getAge() >= 18 && p.getAge() <= 60)
                .filter(p -> p.getEducation() == Education.HIGHER)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        print.accept(workers);

    }
}
