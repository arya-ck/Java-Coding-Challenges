package com.challenge.person;

/**
 * Most programs represent concepts and structures from real life in code.
 * In this challenge, you'll write a Java class that represents a person.
 * The Person class should have a first name, last name, and age attribute with their own getters and setters.
 * It should also have functionality for the person to introduce themselves.
 * Using the Person class, you will create several Person instances that introduce themselves.
 */
public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void introduceYourself(){
        System.out.println("Hi, my name is "+firstName+" "+lastName+" and I'm "+age);
    }
}
