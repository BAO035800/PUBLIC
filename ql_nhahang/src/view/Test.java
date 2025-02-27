package View;

import java.awt.*;
import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CardLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Tạo CardLayout và JPanel chứa các thẻ
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // Tạo các thẻ (panel) khác nhau
        JPanel card1 = new JPanel();
        card1.add(new JLabel("Card 1"));

        JPanel card2 = new JPanel();
        card2.add(new JLabel("Card 2"));

        // Thêm các thẻ vào JPanel chính và gán tên cho từng thẻ
        cardPanel.add(card1, "Card 1");
        cardPanel.add(card2, "Card 2");

        // Tạo nút để chuyển đổi giữa các thẻ
        // JButton switchButton = new JButton("Switch to Card 2");
        // switchButton.addActionListener(e -> cardLayout.show(cardPanel, "Card 2"));

        frame.add(cardPanel, BorderLayout.CENTER);
        // frame.add(switchButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}

