package view;
import java.awt.*;
import javax.swing.*;


public class QLkho extends JPanel {
    

    public QLkho() {
        setLayout(new BorderLayout());

        // Tạo thanh tab full màn hình
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000, 600)); // Điều chỉnh kích thước theo màn hình
        tabbedPane.addTab("Nhà cung cấp", new khoNhaCungCap());
        tabbedPane.addTab("Kho nguyên liệu", new khoNguyenLieu());
        tabbedPane.addTab("Hóa đơn nhập", new khoHoaDonNhap());
        
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 12));
        tabbedPane.setForeground(Color.BLACK);
        tabbedPane.setBackground(new Color(173, 216, 230));
        
        UIManager.put("TabbedPane.selected", new Color(30, 144, 255)); // Màu tab được chọn
        UIManager.put("TabbedPane.contentAreaColor", new Color(63, 186, 231)); // Màu nền nội dung
        UIManager.put("TabbedPane.borderHightlightColor", new Color(30, 144, 255)); // Màu viền
        UIManager.put("TabbedPane.darkShadow", new Color(30, 144, 255)); // Màu bóng
        UIManager.put("TabbedPane.focus", new Color(30, 144, 255)); // Màu focus
        // Thêm vào giao diện chính, đặt ở CENTER để chiếm full màn hình
        add(tabbedPane, BorderLayout.CENTER);
    }
}
