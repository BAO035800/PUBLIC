package tuan;

import java.util.Scanner;

public class b1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập vào số giây: ");
        int hh, mm, ss;
        int s = scanner.nextInt();
        hh = s / 3600;
        mm = (s % 3600) / 60;
        ss = (s % 3600) % 60;
        System.out.printf("%02d:%02d:%02d", hh, mm, ss);
    }
}
