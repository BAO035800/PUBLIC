package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Home extends JPanel {
    public Home() {
        setLayout(new BorderLayout());

        // Tạo tiêu đề
        JLabel titleLabel = new JLabel("HỆ THỐNG QUẢN LÝ NHÀ HÀNG", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(30, 144, 255));
        add(titleLabel, BorderLayout.NORTH);

        // Panel chứa các nút chức năng
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton btnBaoCaoNhanVien = new JButton("Báo cáo nhân viên");
        JButton btnBaoCaoBanHang = new JButton("Báo cáo bán hàng");
        JButton btnBaoCaoNhapHang = new JButton("Báo cáo nhập hàng");
        JButton btnThongKeDoanhThu = new JButton("Thống kê doanh thu");

        styleButton(btnBaoCaoNhanVien);
        styleButton(btnBaoCaoBanHang);
        styleButton(btnBaoCaoNhapHang);
        styleButton(btnThongKeDoanhThu);

        buttonPanel.add(btnBaoCaoNhanVien);
        buttonPanel.add(btnBaoCaoBanHang);
        buttonPanel.add(btnBaoCaoNhapHang);
        buttonPanel.add(btnThongKeDoanhThu);

        add(buttonPanel, BorderLayout.CENTER);

        // Thêm sự kiện cho các nút báo cáo
        btnBaoCaoNhanVien.addActionListener(new ReportActionListener());
        btnBaoCaoBanHang.addActionListener(new ReportActionListener());
        btnBaoCaoNhapHang.addActionListener(new ReportActionListener());
        btnThongKeDoanhThu.addActionListener(new ReportActionListener());
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(111, 205, 240));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(200, 50));
    }

    private class ReportActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] options = {"Excel", "PDF"};
            int choice = JOptionPane.showOptionDialog(
                Home.this,
                "Chọn định dạng báo cáo:",
                "Xuất báo cáo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );

            if (choice == 0) {
                JOptionPane.showMessageDialog(Home.this, "Xuất báo cáo dạng Excel");
                // Gọi hàm xuất Excel ở đây
            } else if (choice == 1) {
                JOptionPane.showMessageDialog(Home.this, "Xuất báo cáo dạng PDF");
                // Gọi hàm xuất PDF ở đây
            }
        }
    }
}
