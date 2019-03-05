package pl.edu.pjwstk.lab2.domain;

public class Person {
    long id;
    String name;

    public void setId(long i) {
        id  = i;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
