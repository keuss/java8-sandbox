package com.cgi.sandbox.pojo;

import java.util.function.Supplier;

public class Person {
    private String firstName = "";
    private String lastName = "";

    public Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Supplier in java.util.function => FunctionalInterface !
    public static Person create( final Supplier<Person> supplier ) {
        return supplier.get();
    }

    public static String pettyDisplayS(final Person p) {
        return p.lastName.toUpperCase() + ", " + p.firstName;
    }

    public String pettyDisplay(final String p) {
        return p + " " + this.lastName.toUpperCase() + ", " + this.firstName;
    }

    public void print() {
        System.out.println("print Person : " + this.toString());
    }

    public static void prints(final Person p) {
        System.out.println("prints Person : " + p.toString());
    }

    public String getFirstName() {
        return this.firstName;
    }
}
