package exercise.kethua;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        nhanvienfulltime nv1 = new nhanvienfulltime();
        nhanvienparttime nv2 = new nhanvienparttime();
        System.out.println("Nhap thong tin nhan vien fulltime");
        System.out.print("Nhap ten:");
        nv1.ten = sc.nextLine();
        System.out.print("Nhap tuoi:");
        nv1.tuoi = sc.nextInt();
        nv1.setHeso(2);
        System.out.print("Nhap luong co ban:");
        nv1.setLuongCoBan(sc.nextDouble());
        nv1.hienThithongtin(nv1.ten, nv1.tuoi);
        System.out.println("Nhap thong tin nhan vien parttime");
        System.out.print("Nhap ten:");
        nv2.ten = sc.nextLine();
        sc.nextLine();
        System.out.print("Nhap tuoi:");
        nv2.tuoi = sc.nextInt();
        System.out.print("Nhap gio lam:");
        nv2.setGiolam(sc.nextInt());
        System.out.print("Nhap luong theo gio:");
        nv2.setLuongtheogio(sc.nextDouble());
        nv2.hienThithongtin(nv2.ten, nv2.tuoi);
    }
}
