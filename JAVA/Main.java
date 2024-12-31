package JAVA;

import java.util.Scanner;

public class Main {
    public static void sapxep(int a[], int n) {
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public static void main(String[] arr) {
        Scanner nhap = new Scanner(System.in);
        String s = nhap.nextLine();
        char target = nhap.next().charAt(0);
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == target) {
                count++;
            }
        }
        System.out.println("Target" + target + ":" + count);
    }
}