package view;

import java.awt.*;
import javax.swing.*;

public class QLbanhang extends JPanel {
    public QLbanhang() {
        setLayout(new BorderLayout());

        // 🌟 Tạo thanh tab full màn hình
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000, 600)); // Điều chỉnh kích thước

        // 🌟 Các panel cho từng tab
        tabbedPane.addTab(" Bàn", new banhangBan());
        tabbedPane.addTab("Menu", new banhangMenu());
        tabbedPane.addTab("Hóa đơn chi tiết", new banhangChiTiet());
        tabbedPane.addTab("Hóa đơn bán hàng", new banhangHoaDonBanHang());

        // 🌟 Cài đặt font chữ và màu sắc
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        tabbedPane.setForeground(Color.BLACK);
        tabbedPane.setBackground(new Color(0, 153, 255)); // Xanh dương đậm

        // 🌟 Hiệu ứng UI đẹp hơn
        UIManager.put("TabbedPane.selected", new Color(30, 144, 255)); // Màu tab đang chọn
        UIManager.put("TabbedPane.contentAreaColor", new Color(200, 230, 255)); // Nền nội dung
        UIManager.put("TabbedPane.borderHighlightColor", new Color(0, 102, 204)); // Viền tab
        UIManager.put("TabbedPane.darkShadow", new Color(0, 102, 204)); // Bóng đổ
        UIManager.put("TabbedPane.focus", new Color(0, 153, 255)); // Màu khi focus

        // 🌟 Thêm hiệu ứng hover cho tab
        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                if (i == selectedIndex) {
                    tabbedPane.setBackgroundAt(i, new Color(111, 205, 240)); // Màu tab chọn
                } else {
                    tabbedPane.setBackgroundAt(i, new Color(173, 216, 230)); // Màu tab thường
                }
            }
        });

        // 🌟 Thêm vào giao diện chính
        add(tabbedPane, BorderLayout.CENTER);
    }
}
