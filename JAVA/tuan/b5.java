package tuan;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class b5 {
    public static void main(String[] args) {
        int k;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập k:");
        k = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        int x;
        while (k != 0) {
            x = k % 8;
            list.add(x);
            k = k / 8;
        }
        Collections.reverse(list);
        // int n = list.size();
        // for (int i = 0; i < n / 2; i++) {
        // int temp = list.get(i);
        // list.set(i, list.get(n - i - 1));
        // list.set(n - i - 1, temp);
        // }
        for (int y : list) {
            System.out.print(y + "");
        }
    }
}
