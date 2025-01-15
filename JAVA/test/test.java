package test;

public class test {
    public static void main(String[] args) {

        try {
            System.out.println("Khối try chạy.");
            int result = 10 / 0; // Gây lỗi
        } catch (ArithmeticException e) {
            System.out.println("Ngoại lệ xảy ra: " + e.getMessage());
        } finally {
            System.out.println("Khối finally luôn chạy.");
        }
    }
}
