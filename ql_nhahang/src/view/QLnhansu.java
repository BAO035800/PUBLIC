package view;
import java.awt.*;
import javax.swing.*;
public class QLnhansu extends JPanel {

    public QLnhansu() {
        setLayout(new BorderLayout());

        // Tạo thanh tab full màn hình
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000, 600)); // Điều chỉnh kích thước theo màn hình
        tabbedPane.addTab("  Nhân viên  ", new nhansuNhanVien());
        tabbedPane.addTab("  Tiền lương  ", new nhansuTienLuong());
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        tabbedPane.setForeground(Color.BLACK);
        UIManager.put("TabbedPane.background", new Color(107, 206, 242)); // Màu mặc định
        UIManager.put("TabbedPane.selected", new Color(63, 186, 231)); // Màu khi được chọn
        UIManager.put("TabbedPane.contentAreaColor", new Color(63, 186, 231)); // Màu nền nội dung tab
        UIManager.put("TabbedPane.borderHighlightColor", new Color(63, 186, 231)); // Màu viền tab
        UIManager.put("TabbedPane.darkShadow", new Color(63, 186, 231)); // Màu bóng
        UIManager.put("TabbedPane.focus", new Color(63, 186, 231)); // Màu khi focus
        UIManager.put("TabbedPane.tabsOverlapBorder", false); // Loại bỏ viền bo tròn giữa các tab
        UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 0, 0, 0)); // Loại bỏ khoảng trống trên tab
        UIManager.put("TabbedPane.tabInsets", new Insets(10, 20, 10, 20)); // Padding chữ trong tab
        add(tabbedPane, BorderLayout.CENTER);
    }
}
