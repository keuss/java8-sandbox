package com.cgi.sandbox;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by galloisg on 28/06/2016.
 */
public class LambdasAndSreams {

    // test from https://www.javacodegeeks.com/2014/05/java-8-features-tutorial.html
    // and http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/

    public static void main(String[] args) {
        System.out.println("#### LAMBDAS TEST ####");

        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));

        List<String> places = Arrays.asList("Paris", "New York", "Rio");
        places.sort((e1, e2) -> e1.compareTo(e2));
        // <=> (like JS)
        places.sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });
        System.out.println(places);

        System.out.println("\n#### STREAMS TEST ####");
        // http://stackoverflow.com/questions/28319064/java-8-best-way-to-transform-a-list-map-or-foreach
        // http://stackoverflow.com/questions/21959237/performance-comparision-between-scala-java-8-map-filter
        List<String> placesFiltered = places.stream().filter(s -> s.startsWith("N")).collect(Collectors.toList());
        System.out.println(placesFiltered);

        List<String> newPlaces = places.stream().map(v -> v + " City").collect(Collectors.toList());
        System.out.println(newPlaces);

    }

}
