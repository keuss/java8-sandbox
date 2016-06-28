package com.cgi.sandbox;

import com.cgi.sandbox.pojo.Person;

import java.util.Optional;

public class OptionalTest {

    // see http://www.nurkiewicz.com/2013/08/optional-in-java-8-cheat-sheet.html

    public static void main(String[] args) {
        System.out.println("#### OPTIONAL TEST ####");

        // Exception in thread "main" java.lang.NullPointerException
        // Optional<Person> p0 = Optional.of(null);

        Optional<Person> p1 = Optional.of(new Person("mike", "d"));
        System.out.println("Person: " + p1.orElseGet(() -> new Person()).getFirstName());
        System.out.println(p1.map(s -> s.pettyDisplay("Hello") + "!").orElse("Hey Stranger!"));

        Optional<Person> p2 = Optional.ofNullable(null);
        System.out.println("Person: " + p2.orElseGet(() -> new Person()).getFirstName());
        System.out.println(p2.map(s -> s.pettyDisplay("Hello") + "!").orElse("Hey unnamed !"));

    }
}
