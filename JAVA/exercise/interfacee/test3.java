package exercise.interfacee;

class test4 implements test1, test2 {
    public void eat() {
        System.out.println("Eating");
    }

    public void sound() {
        System.out.println("Sound");
    }
}

public class test3 {
    public static void main(String[] args) {
        test2 t = new test4();
        t.run();
        t.sound();
    }
}
