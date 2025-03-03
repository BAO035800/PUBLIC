package view;
import java.awt.*;
import javax.swing.*;

public class QLnhansu extends JPanel {
    public QLnhansu() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Họ tên"));
        panel.add(new JTextField());
        panel.add(new JLabel("Tuổi"));
        panel.add(new JTextField());
        add(panel);
    }
}
