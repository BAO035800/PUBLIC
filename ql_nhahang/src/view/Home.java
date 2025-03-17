package view;
import java.awt.*;
import javax.swing.*;
public class Home extends JPanel {
    public Home() {
        setLayout(new BorderLayout());
        // Tạo JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Thiết lập các tab
        tabbedPane.addTab("Nhân viên", new JLabel("Quản lý nhân viên"));
        tabbedPane.addTab("Bán hàng", new JLabel("Quản lý bán hàng"));
        tabbedPane.addTab("Kho", new JLabel("Quản lý kho"));
        tabbedPane.addTab("Thống kê", new JLabel("Thống kê số liệu"));
        tabbedPane.addTab("Tài khoản", new JLabel("Quản lý tài khoản"));
        tabbedPane.addTab("Cài đặt", new JLabel("Cài đặt hệ thống"));

        // Tùy chỉnh giao diện của JTabbedPane
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 12));
        tabbedPane.setForeground(Color.BLACK);
        tabbedPane.setBackground(new Color(173, 216, 230));

        UIManager.put("TabbedPane.selected", new Color(30, 144, 255)); // Màu khi chọn tab
        UIManager.put("TabbedPane.contentAreaColor", new Color(63, 186, 231)); // Màu nền vùng nội dung
        UIManager.put("TabbedPane.borderHightlightColor", new Color(30, 144, 255)); // Màu viền tab
        UIManager.put("TabbedPane.darkShadow", new Color(30, 144, 255)); // Màu shadow của tab
        UIManager.put("TabbedPane.focus", new Color(30, 144, 255)); // Màu focus

        // Thêm JTabbedPane vào JPanel
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
    }
}
