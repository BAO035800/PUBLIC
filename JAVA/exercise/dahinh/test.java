package exercise.dahinh;

class Animal {
    void eat() {
        System.out.println("eating...");
    }
}

class Dog extends Animal {
    void eat() {
        System.out.println("Dog is eating...");
    }
}

class Cat extends Animal {
    void eat() {
        System.out.println("Cat is eating...");
    }
}

class Babydog extends Dog {
    void eat() {
        System.out.println("Baby dog is eating...");
    }
}

public class test {
    public static void main(String[] args) {
        Animal a;
        a = new Animal();
        a.eat();
    }
}
