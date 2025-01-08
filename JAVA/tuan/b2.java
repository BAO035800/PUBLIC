package tuan;

import java.util.Scanner;

public class b2 {
    public static void main(String[] args) {
        int a, b, c, d, max1, max2;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap vao 4 so nguyen: ");
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        d = scanner.nextInt();

        if (a > b) {
            max1 = a;
            max2 = b;
        } else {
            max1 = b;
            max2 = a;
        }

        if (c > max1) {
            max2 = max1;
            max1 = c;
        } else if (c > max2) {
            max2 = c;
        }

        if (d > max1) {
            max2 = max1;
            max1 = d;
        } else if (d > max2) {
            max2 = d;
        }

        System.out.println("max1 = " + max1);
        System.out.println("max2 = " + max2);
    }
}
