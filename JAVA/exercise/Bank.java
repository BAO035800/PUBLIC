package exercise;

import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner nhap = new Scanner(System.in);
        Bank thongtin = new Bank();
        System.out.println("So tai khoan: ");
        thongtin.setSoTk(nhap.nextInt());
        nhap.nextLine();

        System.out.println("Ten tai khoan: ");
        thongtin.setTenTk(nhap.nextLine());

        System.out.println("So du tai khoan: ");
        thongtin.setSoduTk(nhap.nextFloat());

        System.out.println("Tien rut: ");
        thongtin.setRutTk(nhap.nextFloat());

        thongtin.setTien(thongtin.getSoduTk(), thongtin.getRutTk());

        System.out.println("So tai khoan: " + thongtin.getSoTk());
        System.out.println("Ten tai khoan: " + thongtin.getTenTk());
        System.out.println("So du tai khoan: " + thongtin.getTien());
    }
}
