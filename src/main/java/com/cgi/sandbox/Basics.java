package com.cgi.sandbox;

import com.cgi.sandbox.pojo.Person;

import java.util.Arrays;
import java.util.List;

public class Basics {

    // Some examples taken from : http://winterbe.com/posts/2014/03/16/java-8-tutorial/

    public static void main(String[] args) {
        System.out.println("#### Functional Interfaces ####");

        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);

        System.out.println("\n#### Method and Constructor References ####");

        // Class::static_method
        Person p = new Person("keuss", "g");
        final List<Person> persons = Arrays.asList(p);
        persons.forEach(Person::prints);

        Converter<Person, String> cps = Person::pettyDisplayS;
        System.out.println(cps.convert(p));

        // Class::method
        persons.forEach(Person::print);

        // instance::method
        Converter<String, String> cp = p::pettyDisplay;
        System.out.println(cp.convert("Hello"));

        // Class::new
        final Person p2 = Person.create(Person::new);
        System.out.println(p2);
    }
}
