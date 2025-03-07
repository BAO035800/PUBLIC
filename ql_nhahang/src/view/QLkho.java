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

        // Thêm vào giao diện chính, đặt ở CENTER để chiếm full màn hình
        add(tabbedPane, BorderLayout.CENTER);
    }
}
