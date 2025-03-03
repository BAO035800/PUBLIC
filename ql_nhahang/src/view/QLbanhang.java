package view;

import java.awt.*;
import javax.swing.*;

public class QLbanhang extends JPanel { // Thêm extends JPanel
    public QLbanhang() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Tên món"));
        panel.add(new JTextField());
        panel.add(new JLabel("Giá"));
        panel.add(new JTextField());
        add(panel);
    }
}
