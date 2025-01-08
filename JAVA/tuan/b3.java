package tuan;

import java.util.Scanner;

public class b3 {
    public static void main(String[] args) {
        int n, a = 1, b = 1, temp, fibonaci = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập vào số n: ");
        n = scanner.nextInt();
        for (int i = 2; i < n; i++) {
            temp = a;
            fibonaci = a + b;
            a = fibonaci;
            b = temp;
        }
        System.out.println("So fibonaci thu n:" + fibonaci);
    }
}
