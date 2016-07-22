package com.cgi.sandbox;

import com.cgi.sandbox.pojo.Person;

import java.util.*;
import java.util.stream.Collectors;

public class LambdasAndSreams {

    // test from https://www.javacodegeeks.com/2014/05/java-8-features-tutorial.html,
    // http://winterbe.com/posts/2014/03/16/java-8-tutorial/
    // and http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
    // API doc : http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
    // and http://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html

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

        System.out.println("\n#### STREAMS TEST FILTER LASY ####");
        String nFirst = names.stream()
                .peek(p -> System.out.println("will filter " + p))
                .filter(s -> s.startsWith("m"))
                .findFirst()
                .get();
        System.out.println(nFirst);

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
        List<Person> p1List = new ArrayList<>(Arrays.asList(new Person("mark", "f"), new Person("keuss", "g"), new Person("july", "d"), new Person("steve", "j")));
        List<Person> p2List = new ArrayList<>(Arrays.asList(new Person("Dakeuss", "g"), new Person("july", "m"), new Person("steve", "j")));

        List<Person> pResultList = p1List.stream().filter(c -> p2List.contains(c)).collect(Collectors.toList());
        System.out.println(pResultList.size() + "->" + pResultList.get(0));


        System.out.println("\n#### INTERSECTION LIST OTHER TEST ####");
        //http://stackoverflow.com/questions/23696317/java-8-find-first-element-by-predicate

        // peek : This method exists mainly to support debugging, where you want to see the elements
        // as they flow past a certain point in a pipeline
        List<Person> pResultList2 = p1List
                .stream()
                .peek(p -> System.out.println("\nwill map " + p))
                .map(pers1 -> p2List
                                .stream()
                                .peek(p -> System.out.println("will filter " + p))
                                .filter(pers2 -> pers2.getFirstName().equals(pers1.getFirstName()))
                                .peek(p -> System.out.println("findFirst " + p))
                                .findFirst()
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        pResultList2.forEach(System.out::println);

        /*
            will map Person{firstName='mark', lastName='f'}
            will filter Person{firstName='Dakeuss', lastName='g'}
            will filter Person{firstName='july', lastName='m'}
            will filter Person{firstName='steve', lastName='j'}

            will map Person{firstName='keuss', lastName='g'}
            will filter Person{firstName='Dakeuss', lastName='g'}
            will filter Person{firstName='july', lastName='m'}
            will filter Person{firstName='steve', lastName='j'}

            will map Person{firstName='july', lastName='d'}
            will filter Person{firstName='Dakeuss', lastName='g'}
            will filter Person{firstName='july', lastName='m'}
            findFirst Person{firstName='july', lastName='m'}

            will map Person{firstName='steve', lastName='j'}
            will filter Person{firstName='Dakeuss', lastName='g'}
            will filter Person{firstName='july', lastName='m'}
            will filter Person{firstName='steve', lastName='j'}
            findFirst Person{firstName='steve', lastName='j'}
            Person{firstName='july', lastName='m'}
            Person{firstName='steve', lastName='j'}
         */

        /*
            Laziness-seeking. Many stream operations, such as filtering, mapping, or duplicate removal, can be implemented lazily,
            exposing opportunities for optimization. For example, "find the first String with three consecutive vowels"
            need not examine all the input strings. Stream operations are divided into intermediate (Stream-producing)
            operations and terminal (value- or side-effect-producing) operations. Intermediate operations are always lazy.

            => Filter does not scan the whole stream. It's an intermediate operation, which returns a lazy stream !
         */
        System.out.println("\n#### INTERSECTION LIST OTHER WEIRD TEST ####");
        p1List
                .parallelStream()
                .map(pers1 -> {
                    Optional<Person> pMatch = p2List
                            .stream()
                            .filter(pers2 -> pers2.getFirstName().equals(pers1.getFirstName()))
                            .findFirst();
                    if (pMatch.isPresent())
                        // here can mix object creation
                        return new Person(pers1.getFirstName(), pMatch.get().getLastName());
                    else
                        return null;
                })
                .filter(Objects::nonNull)
                .forEach(Person::print);


    }

}
