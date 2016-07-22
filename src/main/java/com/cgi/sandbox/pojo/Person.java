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

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        return !(lastName != null ? !lastName.equals(person.lastName) : person.lastName != null);

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
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
