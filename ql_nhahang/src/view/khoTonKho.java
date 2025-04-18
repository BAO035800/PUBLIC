package view;

import controller.TonKhoController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.TonKhoDAO;
import model.connectData;

public class khoTonKho extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaNguyenLieu, txtSoLuongTon;
    private JButton btnLuuThem, btnLuuSua, btnHuy, btnRefresh;
    private JComboBox<String> cbNgay, cbThang, cbNam;
    private JPanel datePanel;
    private int selectedRow = -1;

    public JTextField getTxtMaNguyenLieu() { return txtMaNguyenLieu; }
    public JTextField getTxtSoLuongTon() { return txtSoLuongTon; }

    public JComboBox<String> getCbNgay() { return cbNgay; }
    public JComboBox<String> getCbThang() { return cbThang; }
    public JComboBox<String> getCbNam() { return cbNam; }

    public khoTonKho() {
        setLayout(new BorderLayout(10, 10));
        String[] columns = {"Mã nguyên liệu", "Số lượng tồn", "Ngày cập nhật"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        TableStyler.styleTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel chức năng
        JPanel controlPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");

        btnThem.setBackground(new Color(72, 201, 176));
        btnSua.setBackground(new Color(255, 193, 7));
        btnXoa.setBackground(new Color(220, 53, 69));
        btnThem.setForeground(Color.WHITE);
        btnSua.setForeground(Color.WHITE);
        btnXoa.setForeground(Color.WHITE);

        controlPanel.add(btnThem);
        controlPanel.add(btnSua);
        controlPanel.add(btnXoa);
        add(controlPanel, BorderLayout.NORTH);

        connectData.loadData(tableModel, "SELECT * FROM tonkho", columns);

        // Panel form nhập liệu
        formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin tồn kho"));
        formPanel.setBackground(Color.WHITE);

        txtMaNguyenLieu = new JTextField();
        txtSoLuongTon = new JTextField();

        btnLuuThem = new JButton("Thêm");
        btnLuuSua = new JButton("Sửa");
        btnHuy = new JButton("Hủy");
        btnRefresh = new JButton("Refresh");

        btnLuuThem.setBackground(new Color(76, 175, 80));
        btnLuuThem.setForeground(Color.WHITE);
        btnLuuSua.setBackground(new Color(255, 193, 7));
        btnLuuSua.setForeground(Color.BLACK);
        btnHuy.setBackground(new Color(108, 117, 125));
        btnHuy.setForeground(Color.WHITE);
        btnRefresh.setBackground(new Color(0, 123, 255));
        btnRefresh.setForeground(Color.WHITE);

        // Ngày, tháng, năm JComboBox
        cbNgay = new JComboBox<>();
        cbThang = new JComboBox<>();
        cbNam = new JComboBox<>();
        for (int i = 1; i <= 31; i++) cbNgay.addItem(String.format("%02d", i));
        for (int i = 1; i <= 12; i++) cbThang.addItem(String.format("%02d", i));
        for (int i = 2000; i <= 2030; i++) cbNam.addItem(Integer.toString(i));
        cbNgay.setSelectedIndex(0);
        cbThang.setSelectedIndex(0);
        cbNam.setSelectedIndex(0);

        // Tạo datePanel riêng để điều khiển
        datePanel = new JPanel(new GridLayout(1, 3, 5, 0));
        datePanel.add(cbNgay);
        datePanel.add(cbThang);
        datePanel.add(cbNam);

        // Thêm thành phần vào formPanel
        formPanel.add(new JLabel("Mã nguyên liệu"));
        formPanel.add(txtMaNguyenLieu);
        formPanel.add(new JLabel("Số lượng tồn"));
        formPanel.add(txtSoLuongTon);
        formPanel.add(new JLabel("Ngày cập nhật"));
        formPanel.add(datePanel);
        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(false);
        datePanel.setVisible(false); // Ẩn ngày cập nhật ban đầu

        btnThem.addActionListener(e -> {
            formPanel.setVisible(true);
            datePanel.setVisible(false); // Ẩn ngày khi thêm
            selectedRow = -1;
            clearForm();
        });

        btnSua.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!");
            } else {
                fillForm(selectedRow);
                formPanel.setVisible(true);
                datePanel.setVisible(true); // Hiện ngày khi sửa
            }
        });

        btnXoa.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
            } else {
                String maNguyenLieu = table.getValueAt(selectedRow, 0).toString(); 
                TonKhoController controller = new TonKhoController(this, new TonKhoDAO());
                controller.xoaTonKho(maNguyenLieu);
                JOptionPane.showMessageDialog(this, "✅ Đã xóa nguyên liệu khỏi tồn kho!");
                connectData.loadData(tableModel, "SELECT * FROM tonkho", columns);
                clearForm();
            }
        });

        btnLuuThem.addActionListener(e -> {
            String maNguyenLieu = txtMaNguyenLieu.getText().trim();
            String soLuong = txtSoLuongTon.getText().trim();
            TonKhoController controller = new TonKhoController(this, new TonKhoDAO());
            controller.themTonKho(maNguyenLieu, soLuong);
            connectData.loadData(tableModel, "SELECT * FROM tonkho", columns);
            clearForm();
        });

        btnLuuSua.addActionListener(e -> {
            TonKhoController controller = new TonKhoController(this, new TonKhoDAO());
            controller.suaTonKho();
            connectData.loadData(tableModel, "SELECT * FROM tonkho", columns);
            System.out.println("Ngày"+ cbNam.getSelectedItem() + "-" + cbThang.getSelectedItem() + "-" + cbNgay.getSelectedItem());
            clearForm();
        });

        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });

        btnRefresh.addActionListener(e -> {
            connectData.loadData(tableModel, "SELECT * FROM tonkho", columns);
        });
    }

    private void clearForm() {
        txtMaNguyenLieu.setText("");
        txtSoLuongTon.setText("");
        cbNgay.setSelectedIndex(0);
        cbThang.setSelectedIndex(0);
        cbNam.setSelectedIndex(0);
    }

    private void fillForm(int row) {
        txtMaNguyenLieu.setText(tableModel.getValueAt(row, 0).toString());
        txtSoLuongTon.setText(tableModel.getValueAt(row, 1).toString());
        String dateString = tableModel.getValueAt(row, 2).toString();
        String[] dateParts = dateString.split("-");
        cbNam.setSelectedItem(dateParts[0]);
        cbThang.setSelectedItem(dateParts[1]);
        cbNgay.setSelectedItem(dateParts[2]);
    }
}
