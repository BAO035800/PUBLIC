package view;
import java.awt.*;
import javax.swing.*;
public class QLnhansu extends JPanel {
    private JTable table;
    public QLnhansu() {
        table = new JTable();
        setLayout(new BorderLayout());

        // Tạo thanh tab full màn hình
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000, 600)); // Điều chỉnh kích thước theo màn hình
        tabbedPane.addTab("  Nhân viên  ", new nhansuNhanVien());
        tabbedPane.addTab("  Tiền lương  ", new nhansuTienLuong());
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        tabbedPane.setForeground(Color.BLACK);
        tabbedPane.setBackground(new Color(173, 216, 230));
        
        UIManager.put("TabbedPane.selected", new Color(30, 144, 255)); // Màu tab được chọn
        UIManager.put("TabbedPane.contentAreaColor", new Color(63, 186, 231)); // Màu nền nội dung
        UIManager.put("TabbedPane.borderHightlightColor", new Color(30, 144, 255)); // Màu viền
        UIManager.put("TabbedPane.darkShadow", new Color(30, 144, 255)); // Màu bóng
        UIManager.put("TabbedPane.focus", new Color(30, 144, 255)); // Màu focus
        UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 0, 0, 0)); // Loại bỏ khoảng trống trên tab
        UIManager.put("TabbedPane.tabInsets", new Insets(10, 20, 10, 20)); // Padding chữ trong tab
        add(tabbedPane, BorderLayout.CENTER);
    }
    public JTable getTable() {
        return table;
    }
}
