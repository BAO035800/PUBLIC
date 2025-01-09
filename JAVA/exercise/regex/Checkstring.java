package exercise.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checkstring {
    public static void main(String[] args) {
        Scanner nhap = new Scanner(System.in);
        Checkstringprivate check = new Checkstringprivate();
        System.out.println("Nhập CCCD: ");
        check.setCccd(check.nhapCccd(nhap.nextLine()));
        System.out.println("Nhập mật khẩu: ");
        check.setMk(check.nhapMk(nhap.nextLine()));
        System.out.println("Nhập email: ");
        check.setEmail(check.nhapEmail(nhap.nextLine()));
        System.out.println("CCCD: " + check.getCccd());
        System.out.println("Mật khẩu: " + check.getMk());
        System.out.println("email: " + check.getEmail());
    }
}
