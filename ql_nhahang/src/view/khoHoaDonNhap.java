package view;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class khoHoaDonNhap extends JPanel implements connectData {
    private JTable table;
    private DefaultTableModel tableModel;
    private Connection conn;

    public khoHoaDonNhap() {
        setLayout(new BorderLayout());

        // Panel chứa nội dung và dữ liệu
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel dataPanel = new JPanel(new BorderLayout());

        // Tạo panel chứa nút chức năng
        JPanel panelNut = new JPanel(new GridLayout(1, 3, 30, 30));
        panelNut.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tạo các nút chức năng
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        panelNut.add(btnThem);
        panelNut.add(btnSua);
        panelNut.add(btnXoa);
        contentPanel.add(panelNut, BorderLayout.NORTH);

        // Tạo bảng dữ liệu
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        dataPanel.add(scrollPane, BorderLayout.CENTER);

        // Kết nối database và tải dữ liệu
        conn = connect();
        loadData(tableModel, "SELECT * FROM hoadonnhap");

        // Chia giao diện thành 2 phần
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, contentPanel, dataPanel);
        splitPane.setResizeWeight(0.3);
        add(splitPane, BorderLayout.CENTER);
    }
}