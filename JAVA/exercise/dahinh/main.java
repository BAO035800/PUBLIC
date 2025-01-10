package exercise.dahinh;

public class main {
    public static void main(String[] args) {
        Vehicle pt;
        pt = new Car();
        pt.move();
        pt.fuelType();
        pt = new Bike();
        pt.move();
        pt.fuelType();
    }
}
