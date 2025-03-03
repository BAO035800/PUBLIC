package view;

import java.awt.*;
import javax.swing.*;

public class QLbanhang extends JPanel { // Thêm extends JPanel
    public QLbanhang() {
        setLayout(new BorderLayout());

        // Tạo thanh tab
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panelBan = createTablePanel();
        JPanel panelMenu = new JPanel();
        JPanel panelHoaDon = new JPanel();

        tabbedPane.addTab("Bàn", panelBan);
        tabbedPane.addTab("Menu", panelMenu);
        tabbedPane.addTab("Hóa đơn bán hàng", panelHoaDon);

        add(tabbedPane, BorderLayout.NORTH);
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new GridLayout(3, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Lưới bàn ăn (3 hàng, 4 cột)
        JPanel tableGrid = new JPanel(new GridLayout(3, 4, 10, 10));
        for (int i = 1; i <= 12; i++) {
            JButton tableButton = new JButton();
            tableButton.setPreferredSize(new Dimension(50, 50));
            tableButton.setBackground(Color.LIGHT_GRAY);
            tableGrid.add(tableButton);
        }

        // Panel chứa lưới bàn ăn + nút chức năng
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(tableGrid, BorderLayout.CENTER);

        // Nút chức năng
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton btnThem = createStyledButton("Thêm");
        JButton btnSua = createStyledButton("Sửa");
        JButton btnXoa = createStyledButton("Xóa");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);

        // Thêm nút vào bên phải
        centerPanel.add(buttonPanel, BorderLayout.EAST);

        // Panel chú thích trạng thái bàn
        JPanel legendPanel = new JPanel();
        JLabel redBox = new JLabel("  ");
        redBox.setOpaque(true);
        redBox.setBackground(Color.RED);
        redBox.setPreferredSize(new Dimension(20, 20));

        JLabel grayBox = new JLabel("  ");
        grayBox.setOpaque(true);
        grayBox.setBackground(Color.LIGHT_GRAY);
        grayBox.setPreferredSize(new Dimension(20, 20));

        legendPanel.add(redBox);
        legendPanel.add(new JLabel("Đã đặt  "));
        legendPanel.add(grayBox);
        legendPanel.add(new JLabel("Còn trống"));

        // Khu vực database
        JPanel databasePanel = new JPanel();
        databasePanel.setPreferredSize(new Dimension(0, 100));
        databasePanel.setBackground(Color.decode("#B3E5FC"));
        JLabel dbLabel = new JLabel("DATABASE", SwingConstants.CENTER);
        dbLabel.setFont(new Font("Arial", Font.BOLD, 18));
        databasePanel.add(dbLabel);

        // Gộp tất cả panel lại
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(legendPanel, BorderLayout.SOUTH);
        panel.add(databasePanel, BorderLayout.SOUTH);

        return panel;
    }
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(Color.decode("#69B9EB"));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));

        // Hiệu ứng hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#4A90E2"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#69B9EB"));
            }
        });

        return button;
    }
}
