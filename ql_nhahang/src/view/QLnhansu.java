package view;
import java.awt.*;
import javax.swing.*;
public class QLnhansu extends JPanel {

    public QLnhansu() {
        setLayout(new BorderLayout());

        // Tạo thanh tab full màn hình
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000, 600)); // Điều chỉnh kích thước theo màn hình
        tabbedPane.addTab("Nhân viên", new nhansuNhanVien());
        tabbedPane.addTab("Tiền lương", new nhansuTienLuong());

        // Thêm vào giao diện chính, đặt ở CENTER để chiếm full màn hình
        add(tabbedPane, BorderLayout.CENTER);
    }
}
