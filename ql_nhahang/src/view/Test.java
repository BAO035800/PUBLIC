package view;
import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JProgressBar Update");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true); // Hiển thị phần trăm

        frame.add(progressBar);
        frame.setVisible(true);

        // Tạo luồng cập nhật tiến trình
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(50); // Giả lập tiến trình chạy chậm
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setValue(i); // Cập nhật tiến trình
            }
        }).start();
    }
}
