package view;
import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    private JPanel contentPanel; // Khu vực hiển thị nội dung
    private CardLayout cardLayout; // Điều khiển chuyển đổi nội dung
    // Hàm tùy chỉnh button
private void styleButton(JButton button) {
    button.setFocusPainted(false); // Xóa viền focus
    button.setBorderPainted(false); // Xóa viền button
    button.setContentAreaFilled(false); // Xóa nền mặc định
    button.setOpaque(true); // Để đổi màu nền có hiệu lực
    button.setBackground(new Color(194, 239, 255)); // Màu nền mặc định (xanh nhạt)
    button.setFont(new Font("Arial", Font.BOLD, 12));

    // Xử lý hover: đổi màu khi di chuột vào
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(new Color(111, 205, 240)); // Xanh dương sáng khi hover
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(new Color(194, 239, 255)); // Trả lại màu gốc
        }
    });
}

    public Main() {
        // Cấu hình JFrame
        setTitle("Quản Lý Nhà Hàng");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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
        
        JButton btnBanHang = new JButton("QUẢN LÝ BÁN HÀNG");
        
        JButton btnNhanSu = new JButton("QUẢN LÝ NHÂN SỰ");
        
        JButton btnKho = new JButton("QUẢN LÝ KHO NGUYÊN LIỆU");
        styleButton(btnBanHang);
        styleButton(btnNhanSu);
        styleButton(btnKho);

        // Thêm nút vào menu
        menuPanel.add(btnBanHang);
        menuPanel.add(btnNhanSu);
        menuPanel.add(btnKho);

        // Panel hiển thị nội dung chính
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Thêm các panel từ file khác
        contentPanel.add(new QLbanhang(), "BanHang");
        contentPanel.add(new QLkho(), "NhanSu");
        contentPanel.add(new QLnhansu(), "Kho");

        // Xử lý sự kiện khi bấm nút
        btnBanHang.addActionListener(e -> cardLayout.show(contentPanel, "BanHang"));
        btnNhanSu.addActionListener(e -> cardLayout.show(contentPanel, "NhanSu"));
        btnKho.addActionListener(e -> cardLayout.show(contentPanel, "Kho"));

        // Thêm vào JFrame
        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Hiển thị giao diện
        setVisible(true);
    }

    public static void main(String[] args) {
        Main mainFrame = new Main();
    }
}
