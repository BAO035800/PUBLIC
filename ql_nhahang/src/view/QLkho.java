package view;
import java.awt.*;
import javax.swing.*;

public class QLkho extends JPanel {
    public QLkho() {
        setLayout(new BorderLayout());
        
        JLabel label = new JLabel("📌 Giao diện quản lý kho nguyên liệu", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        
        add(label, BorderLayout.CENTER);
    }
}
