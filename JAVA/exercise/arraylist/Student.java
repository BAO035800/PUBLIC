package exercise.arraylist;

import java.util.ArrayList;

public class Student {
    public static void main(String[] args) {
        StudentPrivate s1 = new StudentPrivate("Nguyen Gia Bao", 1);
        StudentPrivate s2 = new StudentPrivate("Nguyen Gia Ba", 2);
        StudentPrivate s3 = new StudentPrivate("Tran Van A", 3);
        StudentPrivate s4 = new StudentPrivate("Tran Ha Linh", 4);
        StudentPrivate s5 = new StudentPrivate("Pham Ngoc Trinh", 5);
        ArrayList<StudentPrivate> arr = new ArrayList<>();
        arr.add(s1);
        arr.add(s2);
        arr.add(s3);
        arr.add(s4);
        arr.add(s5);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getName().startsWith("Nguyen")) {
                System.out.println(arr.get(i));
            }
        }
    }
}
