package view;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class khoNguyenLieu extends JPanel implements connectData {
    private JTable table;
    private DefaultTableModel tableModel;
    private Connection conn;

    public khoNguyenLieu() {
        setLayout(new BorderLayout());

        // Panel chứa các thành phần
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel dataPanel = new JPanel(new BorderLayout());

        // Tạo panel chứa nút
        JPanel panelNut = new JPanel(new GridLayout(1, 3, 10, 10));
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

        // Kết nối database và load dữ liệu
        conn = connect();
        loadData(tableModel, "SELECT * FROM khonguyenlieu");

        // Chia giao diện thành 2 phần
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, contentPanel, dataPanel);
        splitPane.setResizeWeight(0.2);
        add(splitPane, BorderLayout.CENTER);
    }
}