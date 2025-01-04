package exercise;

import java.util.ArrayList;
import java.util.Scanner;

public class arraylist {
    public static void main(String[] args) {
        Scanner nhap = new Scanner(System.in);
        ArrayList<Double> mang = new ArrayList<Double>();
        double input, sum = 0.0;
        String option;
        while (true) {
            System.out.println("Nhap so: ");
            input = nhap.nextDouble();
            nhap.nextLine();
            mang.add(input);
            System.out.println("Bạn có muốn nhập thêm (Y/N)");
            option = nhap.nextLine();
            if (option.equals("N") || option.equals("n")) {
                break;
            } else if (option.equals("Y") || option.equals("y")) {
                continue;
            } else {
                System.out.println("ERROR");
                break;
            }
        }
        for (int i = 0; i < mang.size(); i++) {
            sum += mang.get(i);
        }
        System.out.println("Danh sach so: " + mang);
        System.out.println("Tong: " + sum);
    }
}