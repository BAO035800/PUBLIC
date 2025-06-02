package view;

import controller.BanController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.BanDAO;
import model.connectData;

public class banhangBan extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtBan, txtGhiChu;
    private JComboBox<String> cbTinhTrang;
    private JButton btnLuuThem, btnHuy, btnLuuSua, btnRefresh;
    private int selectedRow = -1;

    public JTextField getTxtBan() {
        return txtBan;
    }

    public void setTxtBan(JTextField txtBan) {
        this.txtBan = txtBan;
    }

    public JTextField getTxtGhiChu() {
        return txtGhiChu;
    }

    public void setTxtGhiChu(JTextField txtGhiChu) {
        this.txtGhiChu = txtGhiChu;
    }

    public JComboBox<String> getCbTinhTrang() {
        return cbTinhTrang;
    }

    public void setCbTinhTrang(JComboBox<String> cbTinhTrang) {
        this.cbTinhTrang = cbTinhTrang;
    }

    public banhangBan() {
        String[] nvColumns = {"Số bàn", "Tình trạng", "Ghi chú"};
        setLayout(new BorderLayout(10, 10));

        // Khởi tạo tableModel
        tableModel = new DefaultTableModel(nvColumns, 0);
        table = new JTable(tableModel);
        TableStyler.styleTable(table);

        // Panel chứa nút chức năng
        JPanel controlPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        btnRefresh = new JButton("Refresh");

        btnThem.setBackground(new Color(72, 201, 176));
        btnSua.setBackground(new Color(255, 193, 7));
        btnXoa.setBackground(new Color(220, 53, 69));
        btnRefresh.setBackground(new Color(0, 123, 255));

        btnThem.setForeground(Color.WHITE);
        btnSua.setForeground(Color.WHITE);
        btnXoa.setForeground(Color.WHITE);
        btnRefresh.setForeground(Color.WHITE);

        controlPanel.add(btnThem);
        controlPanel.add(btnSua);
        controlPanel.add(btnXoa);
        controlPanel.add(btnRefresh);
        add(controlPanel, BorderLayout.NORTH);

        // Bảng dữ liệu
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu từ database
        connectData.loadData(tableModel, "SELECT * FROM ban", nvColumns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin bàn"));
        formPanel.setBackground(Color.WHITE);

        // Các input field
        txtBan = new JTextField();
        txtGhiChu = new JTextField();
        cbTinhTrang = new JComboBox<>(new String[] {"Trống", "Đang phục vụ"});
        cbTinhTrang.setSelectedIndex(0);

        // Tạo các button chức năng trong form
        btnLuuThem = new JButton("Thêm");
        btnLuuSua = new JButton("Sửa");
        btnHuy = new JButton("Hủy");

        btnLuuThem.setBackground(new Color(76, 175, 80));
        btnLuuSua.setBackground(new Color(255, 193, 7));
        btnHuy.setBackground(new Color(108, 117, 125));

        btnLuuThem.setForeground(Color.WHITE);
        btnLuuSua.setForeground(Color.BLACK);
        btnHuy.setForeground(Color.WHITE);

        // Thêm các thành phần vào form
        formPanel.add(new JLabel("Số bàn"));
        formPanel.add(txtBan);
        formPanel.add(new JLabel("Tình trạng"));
        formPanel.add(cbTinhTrang);
        formPanel.add(new JLabel("Ghi chú"));
        formPanel.add(txtGhiChu);
        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(new JLabel());
        formPanel.add(btnLuuSua);

        add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(false);

        // Sự kiện "Thêm"
        btnThem.addActionListener(e -> {
            selectedRow = -1;
            clearForm();
            formPanel.setVisible(true);
        });

        // Sự kiện "Sửa"
        btnSua.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!");
            } else {
                fillForm(selectedRow);
                formPanel.setVisible(true);
            }
        });

        // Sự kiện "Xóa"
        btnXoa.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn cần xóa!");
                return;
            }
            int soBan = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
            BanController banController = new BanController(banhangBan.this, new BanDAO());
            banController.xoaBan(soBan);
            connectData.loadData(tableModel, "SELECT * FROM ban", nvColumns);
            clearForm();
        });

        // Sự kiện "Lưu Thêm"
        btnLuuThem.addActionListener(e -> {
            BanController banController = new BanController(banhangBan.this, new BanDAO());
            banController.themBan();
            connectData.loadData(tableModel, "SELECT * FROM ban", nvColumns);
        });

        // Sự kiện "Lưu Sửa"
        btnLuuSua.addActionListener(e -> {
            BanController banController = new BanController(banhangBan.this, new BanDAO());
            banController.suaBan();
            connectData.loadData(tableModel, "SELECT * FROM ban", nvColumns);
        });

        // Sự kiện "Hủy"
        btnHuy.addActionListener(e -> formPanel.setVisible(false));

        // Sự kiện "Refresh"
        btnRefresh.addActionListener(e -> connectData.loadData(tableModel, "SELECT * FROM ban", nvColumns));
    }

    private void clearForm() {
        txtBan.setText("");
        txtGhiChu.setText("");
        cbTinhTrang.setSelectedIndex(0);
    }

    private void fillForm(int row) {
        txtBan.setText(table.getValueAt(row, 0).toString());
        cbTinhTrang.setSelectedItem(table.getValueAt(row, 1).toString());
        txtGhiChu.setText(table.getValueAt(row, 2).toString());
    }
}
