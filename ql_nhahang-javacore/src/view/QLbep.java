package view;

import java.awt.*;
import javax.swing.*;

public class QLbep extends JPanel {
    private JTable table;
    public QLbep() {
        table = new JTable();
        setLayout(new BorderLayout());

        // Tạo thanh tab
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000, 600));
        
        tabbedPane.addTab("Công thức món ăn", new bepCongThucMon());
        tabbedPane.addTab("Chế biến món ăn", new bepCheBienMon());
        tabbedPane.addTab("Tồn kho", new khoTonKho());

        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        tabbedPane.setForeground(Color.BLACK);
        tabbedPane.setBackground(new Color(173, 216, 230));

        UIManager.put("TabbedPane.selected", new Color(30, 144, 255));
        UIManager.put("TabbedPane.contentAreaColor", new Color(63, 186, 231));
        UIManager.put("TabbedPane.borderHightlightColor", new Color(30, 144, 255));
        UIManager.put("TabbedPane.darkShadow", new Color(30, 144, 255));
        UIManager.put("TabbedPane.focus", new Color(30, 144, 255));

        add(tabbedPane, BorderLayout.CENTER);
    }
    public JTable getTable() {
        return table;
    }
}
