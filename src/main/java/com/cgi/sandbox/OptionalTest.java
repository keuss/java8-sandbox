package com.cgi.sandbox;

import com.cgi.sandbox.pojo.Person;

import java.util.Optional;

public class OptionalTest {

    // see http://www.nurkiewicz.com/2013/08/optional-in-java-8-cheat-sheet.html
    // doc : https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html

    public static void main(String[] args) {
        System.out.println("#### OPTIONAL TEST ####");

        // Exception in thread "main" java.lang.NullPointerException
        // Optional<Person> p0 = Optional.of(null);

        Optional<Person> p1 = Optional.ofNullable(new Person("mike", "d"));
        System.out.println("Person: " + p1.orElseGet(() -> new Person()).getFirstName());
        System.out.println(p1.map(s -> s.pettyDisplay("Hello") + "!").orElse("Hey Stranger!"));

        // static <T> Optional<T>	ofNullable(T value)
        // Returns an Optional describing the specified value, if non-null, otherwise returns an empty Optional.
        Optional<Person> p2 = Optional.ofNullable(null);
        System.out.println("Person: " + p2.orElseGet(() -> new Person()).getFirstName());
        System.out.println(p2.map(s -> s.pettyDisplay("Hello") + "!").orElse("Hey unnamed !"));

        // <U> Optional<U>	map(Function<? super T,? extends U> mapper)
        // If a value is present, apply the provided mapping function to it, and if the result is non-null, return an Optional describing the result.
        p1.map(person -> {
            System.out.println("person p1:"+person);
            return person;
        });

        // value not present !
        p2.map(person -> {
            System.out.println("person p2:"+person);
            return person;
        });
    }
}
