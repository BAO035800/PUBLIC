package tuan;

import java.util.Scanner;
import java.util.ArrayList;

public class b4 {
    public static int songuyento(int n) {
        int dem = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                dem++;
            }
        }
        if (dem == 2) {
            return 1;
        } else {
            return 0;
        }
    }

    public static ArrayList<Integer> array(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (songuyento(i) == 1) {
                list.add(i);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhâp n: ");
        n = sc.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        arr = array(n);
        if (arr.size() != 0) {
            for (int x : arr) {
                System.out.print(x + " ");

            }
        } else {
            System.out.println("NOT FOUND");
        }
    }
}