package view;

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    private JPanel contentPanel; // Khu vực hiển thị nội dung
    private CardLayout cardLayout; // Điều khiển chuyển đổi nội dung
    private JButton currentButton = null; // Biến lưu trạng thái nút được chọn

    public Main() {
        // Cấu hình JFrame
        setTitle("Quản Lý Nhà Hàng");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Hiển thị ở giữa màn hình

        // Thanh Menu
        setJMenuBar(createMenuBar());
        
        // Menu bên trái
        JPanel menuPanel = createMenuPanel();

        // Panel hiển thị nội dung
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(new Home(), "TrangChu");
        contentPanel.add(new QLbanhang(), "BanHang");
        contentPanel.add(new QLkho(), "Kho");
        contentPanel.add(new QLnhansu(), "NhanSu");

        // Thêm vào JFrame
        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Mặc định hiển thị trang chủ
        setButtonFocus((JButton) menuPanel.getComponent(1));
        cardLayout.show(contentPanel, "TrangChu");

        setVisible(true);
    }

    // Tạo thanh menu
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Account");
        JMenuItem logoutItem = new JMenuItem("Đăng xuất");
        JMenuItem exitItem = new JMenuItem("Thoát");

        exitItem.addActionListener(e -> System.exit(0));
        menu.add(logoutItem);
        menu.add(exitItem);
        menuBar.add(menu);
        return menuBar;
    }

    // Tạo menu bên trái
    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridLayout(6, 1));
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));
        menuPanel.setBackground(Color.decode("#69B9EB"));

        JLabel adminLabel = new JLabel("XIN CHÀO ADMIN", SwingConstants.CENTER);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 14));
        menuPanel.add(adminLabel);

        String[] buttonNames = {"TRANG CHỦ", "QUẢN LÝ BÁN HÀNG", "QUẢN LÝ NHÂN SỰ", "QUẢN LÝ KHO"};
        String[] panelKeys = {"TrangChu", "BanHang", "NhanSu", "Kho"};
        
        for (int i = 0; i < buttonNames.length; i++) {
            JButton button = createStyledButton(buttonNames[i]);
            final String panelKey = panelKeys[i];
            button.addActionListener(e -> {
                cardLayout.show(contentPanel, panelKey);
                setButtonFocus(button);
            });
            menuPanel.add(button);
        }
        return menuPanel;
    }

    // Tạo nút bấm có kiểu dáng đẹp
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(new Color(194, 239, 255));
        button.setFont(new Font("Arial", Font.BOLD, 12));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button != currentButton) {
                    button.setBackground(new Color(111, 205, 240));
                }
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button != currentButton) {
                    button.setBackground(new Color(194, 239, 255));
                }
            }
        });
        return button;
    }

    // Cập nhật trạng thái khi nút được chọn
    private void setButtonFocus(JButton selectedButton) {
        selectedButton.setFont(new Font("Arial", Font.BOLD, 14));
        selectedButton.setBackground(new Color(111, 205, 240));

        if (currentButton != null && currentButton != selectedButton) {
            currentButton.setFont(new Font("Arial", Font.PLAIN, 12));
            currentButton.setBackground(new Color(194, 239, 255));
        }
        currentButton = selectedButton;
    }

    public static void main(String[] args) {
        Login loginForm = new Login();
    }
}
