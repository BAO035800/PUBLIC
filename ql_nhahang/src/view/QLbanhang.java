package view;
import java.awt.*;
import javax.swing.*;

public class QLbanhang extends JPanel {
    private JTable table;
    public QLbanhang() {
        table = new JTable();
        setLayout(new BorderLayout());

        // 🌟 Thiết lập UIManager trước khi tạo JTabbedPane
        UIManager.put("TabbedPane.selected", new Color(30, 144, 255)); // Màu tab được chọn
        UIManager.put("TabbedPane.borderHightlightColor", new Color(30, 144, 255)); // Màu viền
        UIManager.put("TabbedPane.darkShadow", new Color(30, 144, 255)); // Màu bóng
        UIManager.put("TabbedPane.focus", new Color(30, 144, 255)); // Màu focus

        // 🌟 Tạo thanh tab full màn hình
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000, 600)); // Điều chỉnh kích thước
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        tabbedPane.setForeground(Color.BLACK);
        tabbedPane.setBackground(new Color(173, 216, 230)); // Màu nền tổng thể

        // 🌟 Thêm các tab vào JTabbedPane
        tabbedPane.addTab("Bàn", new banhangBan());
        tabbedPane.addTab("Menu", new banhangMenu());
        tabbedPane.addTab("Hóa đơn chi tiết", new banhangChiTiet());
        tabbedPane.addTab("Hóa đơn bán hàng", new banhangHoaDonBanHang());

        // 🌟 Thêm vào giao diện chính
        add(tabbedPane, BorderLayout.CENTER);
    }
    public JTable getTable() {
        return table;
    }
}
