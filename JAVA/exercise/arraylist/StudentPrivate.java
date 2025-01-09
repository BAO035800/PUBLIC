package exercise.arraylist;

import java.util.Scanner;

public class StudentPrivate {
    private String name;
    private int id;

    @Override
    public String toString() {
        return "StudentPrivate [name=" + name + ", id=" + id + "]";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int setId() {
        return id;
    }

    public StudentPrivate(String name, int id) {
        setName(name);
        setId(id);
    }
}
