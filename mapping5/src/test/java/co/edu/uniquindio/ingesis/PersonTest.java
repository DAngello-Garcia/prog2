package co.edu.uniquindio.ingesis;

import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

class PersonTest {

    @Test
    void map() {
        List<Person> list = new ArrayList<>();

        list.add(new Person("Miguel", 18));
        list.add(new Person("Juan", 20));
        list.add(new Person("Ana", 18));
        list.add(new Person("Camilo", 19));
        list.add(new Person("Miguel", 23));
        list.add(new Person("Camilo", 17));

        for (Person person : list) {
            System.out.println(person.getName() + " " + person.getAge());
        }

        System.out.println("mapping...");
        IntSummaryStatistics summaryStatistics = list.stream().mapToInt(l -> l.getName().length()).summaryStatistics();

        System.out.println("max name lenght " + summaryStatistics.getMax());
        System.out.println("min name lenght " + summaryStatistics.getMin());
        System.out.println("average name lenght " + summaryStatistics.getAverage());


    }
}