package tuan;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class b6 {
    public static void main(String[] args) {
        ArrayList<SinhVien> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.print("Số lượng sinh viên: ");
        n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Sinh viên thứ " + (i + 1));

            System.out.print("Ma sinh vien: ");
            String maSV = sc.nextLine();

            System.out.print("Ten sinh vien: ");
            String tenSV = sc.nextLine();

            System.out.print("Điem trung binh: ");
            float dtb = sc.nextFloat();
            sc.nextLine();
            SinhVien sv = new SinhVien();
            sv.maSV = maSV;
            sv.tenSV = tenSV;
            sv.dtb = dtb;
            list.add(sv);
        }
        Collections.sort(list, new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien o1, SinhVien o2) {
                if (o1.dtb == o2.dtb) {
                    int result = o1.getTen().compareTo(o2.getTen());
                    if (result == 0) {
                        return 0;
                    } else if (result < 0) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else if (o1.dtb > o2.dtb) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        System.out.println("Danh sach sau sap xep:");
        for (SinhVien x : list) {
            System.out.println(x.maSV + "-" + x.tenSV + "-" + x.dtb);
        }
    }
}
