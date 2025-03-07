package view;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class khoNhaCungCap extends JPanel implements connectData {
    private JTable table;
    private DefaultTableModel tableModel;
    private Connection conn;

    public khoNhaCungCap() {
        setLayout(new BorderLayout());

        // Panel chứa chức năng
        JPanel functionPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        functionPanel.add(btnThem);
        functionPanel.add(btnSua);
        functionPanel.add(btnXoa);

        // Tạo bảng dữ liệu
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Kết nối database và tải dữ liệu
        conn = connect();
        loadData(tableModel, "SELECT * FROM nhacungcap");

        // Chia giao diện
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, functionPanel, scrollPane);
        splitPane.setResizeWeight(0.1);
        add(splitPane, BorderLayout.CENTER);
    }
}