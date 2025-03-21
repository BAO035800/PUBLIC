package view;
import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    private JPanel contentPanel; // Khu vực hiển thị nội dung
    private CardLayout cardLayout; // Điều khiển chuyển đổi nội dung
    // Hàm tùy chỉnh button
// Biến để lưu button đang được nhấn
private JButton currentButton = null;
private void setButtonFocus(JButton selectedButton) {
    // Đổi font in đậm và màu nền của nút hiện tại
    selectedButton.setFont(new Font("Arial", Font.BOLD, 14));
    selectedButton.setBackground(new Color(111, 205, 240)); // Màu khi được chọn

    // Đặt lại font và màu nền của các nút khác
    for (Component component : selectedButton.getParent().getComponents()) {
        if (component instanceof JButton && component != selectedButton) {
            component.setFont(new Font("Arial", Font.PLAIN, 12)); // Trả về font thường
            component.setBackground(new Color(194, 239, 255)); // Màu mặc định
        }
    }
}
private void styleButton(JButton button) {
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setContentAreaFilled(false);
    button.setOpaque(true);
    button.setBackground(new Color(194, 239, 255)); // Màu nền mặc định (xanh nhạt)
    button.setFont(new Font("Arial", Font.BOLD, 12));

    // Sự kiện hover
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            if (button != currentButton) { // Chỉ đổi màu hover nếu chưa được chọn
                button.setBackground(new Color(111, 205, 240)); // Xanh dương sáng khi hover
            }
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            if (button != currentButton) { // Không đổi màu nếu đang được chọn
                button.setBackground(new Color(194, 239, 255)); // Trả lại màu gốc
            }
        }
    });

    // Xử lý sự kiện click
    button.addActionListener(e -> {
        // Nếu có button khác đang được chọn, đổi nó về màu gốc
        if (currentButton != null && currentButton != button) {
            currentButton.setBackground(new Color(194, 239, 255)); // Màu gốc
        }

        currentButton = button;
        button.setBackground(new Color(111, 205, 240)); // Màu  khi được chọn
    });
}


    public Main() {
        // Cấu hình JFrame
        setTitle("Quản Lý Nhà Hàng");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //Menu tổng
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Accout");
        JMenuItem menuItem = new JMenuItem("Đăng xuất");
        JMenuItem menuItem1 = new JMenuItem("Thoát");
        menu.add(menuItem);
        menu.add(menuItem1);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        // Panel chính
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        // Panel menu bên trái
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1)); // 5 hàng
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));
        menuPanel.setBackground(Color.decode("#69B9EB")); // Màu xanh

        // Tiêu đề Admin
        JLabel adminLabel = new JLabel("XIN CHÀO ADMIN", SwingConstants.CENTER);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 14));
        menuPanel.add(adminLabel);

        // Nút bấm
        JButton btnTrangChu = new JButton("TRANG CHỦ");
        JButton btnBanHang = new JButton("QUẢN LÝ BÁN HÀNG");
        
        JButton btnNhanSu = new JButton("QUẢN LÝ NHÂN SỰ");
        
        JButton btnKho = new JButton("QUẢN LÝ KHO");
        styleButton(btnTrangChu);
        styleButton(btnBanHang);
        styleButton(btnNhanSu);
        styleButton(btnKho);

        // Thêm nút vào menu
        menuPanel.add(btnTrangChu);
        menuPanel.add(btnBanHang);
        menuPanel.add(btnNhanSu);
        menuPanel.add(btnKho);

        // Panel hiển thị nội dung chính
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Thêm các panel từ file khác
        contentPanel.add(new Home(), "TrangChu");
        contentPanel.add(new QLbanhang(), "BanHang");
        contentPanel.add(new QLkho(), "Kho");
        contentPanel.add(new QLnhansu(), "NhanSu");

        // Xử lý sự kiện khi bấm nút
        btnTrangChu.addActionListener(e -> {
            cardLayout.show(contentPanel, "TrangChu");
            setButtonFocus(btnTrangChu); // Đổi màu và in đậm
        });
        btnBanHang.addActionListener(e -> {
            cardLayout.show(contentPanel, "BanHang");
            setButtonFocus(btnBanHang); // Đổi màu và in đậm
        });
        btnNhanSu.addActionListener(e -> {
            cardLayout.show(contentPanel, "NhanSu");
            setButtonFocus(btnNhanSu); // Đổi màu và in đậm
        });
        btnKho.addActionListener(e -> {
            cardLayout.show(contentPanel, "Kho");
            setButtonFocus(btnKho); // Đổi màu và in đậm
        });
        setButtonFocus(btnTrangChu);
    cardLayout.show(contentPanel, "TrangChu");


        // Thêm vào JFrame
        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Hiển thị giao diện
        setVisible(true);
    }

    public static void main(String[] args) {
        Login loginForm = new Login();
    }
}
