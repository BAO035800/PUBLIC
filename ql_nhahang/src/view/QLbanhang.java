package view;

import java.awt.*;
import javax.swing.*;
public class QLbanhang extends JPanel {
    public QLbanhang() {
        setLayout(new BorderLayout());

        // Tạo thanh tab full màn hình
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000, 600)); // Điều chỉnh kích thước theo màn hình

        // Các panel cho từng tab
        tabbedPane.addTab("Bàn", new banhangBan());
        tabbedPane.addTab("Menu", new banhangMenu());
        tabbedPane.addTab("Hóa đơn bán hàng", new banhangHoaDonBanHang());

        // Thêm vào giao diện chính, đặt ở CENTER để chiếm full màn hình
        add(tabbedPane, BorderLayout.CENTER);
    }
}