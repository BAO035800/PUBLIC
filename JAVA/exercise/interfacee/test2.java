package exercise.interfacee;

public interface test2 {
    public void sound();

    default void run() {
        System.out.println("Running");
    }
}
