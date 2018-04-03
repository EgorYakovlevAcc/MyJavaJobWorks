package main.java;

public class User {
    String name;
    String surname;
    String age;
    String country;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public User(String name, String surname, String age, String country) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.country  = country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }
}
