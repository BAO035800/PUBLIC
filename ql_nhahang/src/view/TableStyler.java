package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableStyler {
    public static void styleTable(JTable table) {
        // Cài đặt font chữ và chiều cao dòng
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(30);

        // Định dạng header của bảng
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(59, 165, 232)); // Màu xanh dương
        header.setForeground(Color.WHITE); // Chữ trắng
        header.setPreferredSize(new Dimension(header.getWidth(), 30)); // Chiều cao header

        // Định dạng màu xen kẽ + căn giữa dữ liệu trong bảng
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Căn giữa dữ liệu
                setHorizontalAlignment(SwingConstants.CENTER);

                // Màu xen kẽ từng dòng
                if (!isSelected) {
                    cell.setBackground(row % 2 == 0 ? new Color(240, 240, 240) : Color.WHITE);
                }
                return cell;
            }
        };

        // Áp dụng renderer cho tất cả các cột
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
}
