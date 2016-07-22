package com.cgi.sandbox;

import com.cgi.sandbox.pojo.Person;

import java.util.*;
import java.util.stream.Collectors;

public class LambdasAndSreams {

    // test from https://www.javacodegeeks.com/2014/05/java-8-features-tutorial.html,
    // http://winterbe.com/posts/2014/03/16/java-8-tutorial/
    // and http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/

    public static void main(String[] args) {
        System.out.println("#### LAMBDAS TEST ####");

        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        List<String> places = Arrays.asList("Paris", "New York", "Rio");
        places.sort((e1, e2) -> e1.compareTo(e2));
        // <=> (like JS)
        places.sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });
        System.out.println(places);

        Collections.sort(names, (a, b) -> a.compareTo(b));
        System.out.println(names);

        System.out.println("\n#### STREAMS TEST ####");
        // http://stackoverflow.com/questions/28319064/java-8-best-way-to-transform-a-list-map-or-foreach
        // http://stackoverflow.com/questions/21959237/performance-comparision-between-scala-java-8-map-filter
        List<String> placesFiltered = places.stream().filter(s -> s.startsWith("N")).collect(Collectors.toList());
        System.out.println(placesFiltered);

        List<String> newPlaces = places.stream().map(v -> v + " City").collect(Collectors.toList());
        System.out.println(newPlaces);

        Person p1 = new Person("keuss", "g"), p2 = new Person("steve", "j");
        final List<Person> persons = Arrays.asList(p1, p2);
        List<String> lp = persons.stream().map(Person::getFirstName).collect(Collectors.toList());
        List<String> lp2 = persons.stream().map(Person::pettyDisplayS).collect(Collectors.toList());
        System.out.println(lp); // [keuss, steve]
        System.out.println(lp2); // [G, keuss, J, steve]


        System.out.println("\n#### OTHERS STREAMS TEST ####");
        final List<Optional<Person>> personsOption = Arrays.asList(Optional.of(new Person("keuss", "g")),
                Optional.of(new Person("steve", "j")), Optional.empty());

       persons
            .stream()
            .map(v -> v.getFirstName().toUpperCase())
            .sorted((a, b) -> b.compareTo(a))
            .forEach(System.out::println);
        //STEVE
        //KEUSS

        personsOption
            .stream()
            .filter(Optional::isPresent)
            .forEach(v -> System.out.println(v.get().getFirstName()));
        //keuss
        //steve

        System.out.println("\n#### SIMPLE INTERSECTION LIST TEST ####");
        List<Person> p1List = new ArrayList<>(Arrays.asList(new Person("keuss", "g"), new Person("july", "d"), new Person("steve", "j")));
        List<Person> p2List = new ArrayList<>(Arrays.asList(new Person("Dakeuss", "g"), new Person("july", "m"), new Person("steve", "j")));

        List<Person> pResultList = p1List.stream().filter(c -> p2List.contains(c)).collect(Collectors.toList());
        System.out.println(pResultList.size() + "->" + pResultList.get(0));


    }

}
