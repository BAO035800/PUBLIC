package view;

import controller.CheBienMonController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.CheBienMonDAO;
import model.connectData;

public class bepCheBienMon extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaCheBien, txtMaMon, txtSoBan, txtMaHoaDonBanHang;
    private JComboBox<String> cmbTinhTrang;
    private JButton btnLuuThem, btnLuuSua, btnHuy, btnRefresh;
    private int selectedRow = -1;

    public JTextField getTxtMaCheBien() { return txtMaCheBien; }
    public JTextField getTxtMaMon() { return txtMaMon; }
    public JTextField getTxtSoBan() { return txtSoBan; }
    public JTextField getTxtMaHoaDon() { return txtMaHoaDonBanHang; }
    public JComboBox<String> getCmbTinhTrang() { return cmbTinhTrang; }

    public bepCheBienMon() {
        setLayout(new BorderLayout(10, 10));
        String[] columns = {"Mã chế biến", "Mã món", "Số bàn", "Mã hóa đơn", "Tình trạng"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        TableStyler.styleTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel nút chức năng
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

        connectData.loadData(tableModel, "SELECT * FROM chebienmonan", columns);

        // Panel form nhập liệu
        formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin chế biến"));
        formPanel.setBackground(Color.WHITE);

        txtMaCheBien = new JTextField();
        txtMaMon = new JTextField();
        txtSoBan = new JTextField();
        txtMaHoaDonBanHang = new JTextField();
        cmbTinhTrang = new JComboBox<>(new String[]{"Đang làm", "Đã hoàn thành"});

        btnLuuThem = new JButton("Thêm");
        btnLuuSua = new JButton("Sửa");
        btnHuy = new JButton("Hủy");

        btnLuuThem.setBackground(new Color(76, 175, 80));
        btnLuuThem.setForeground(Color.WHITE);
        btnLuuSua.setBackground(new Color(255, 193, 7));
        btnLuuSua.setForeground(Color.BLACK);
        btnHuy.setBackground(new Color(108, 117, 125));
        btnHuy.setForeground(Color.WHITE);

        formPanel.add(new JLabel("Mã chế biến"));
        formPanel.add(txtMaCheBien);
        formPanel.add(new JLabel("Mã món"));
        formPanel.add(txtMaMon);
        formPanel.add(new JLabel("Số bàn"));
        formPanel.add(txtSoBan);
        formPanel.add(new JLabel("Mã hóa đơn"));
        formPanel.add(txtMaHoaDonBanHang);
        formPanel.add(new JLabel("Tình trạng"));
        formPanel.add(cmbTinhTrang);
        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);

        add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(false);

        // Sự kiện
        btnThem.addActionListener(e -> {
            selectedRow = -1;
            clearForm();
            formPanel.setVisible(true);
        });

        btnSua.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!");
            } else {
                fillForm(selectedRow);
                formPanel.setVisible(true);
            }
        });

        btnXoa.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
            } else {
                String maCheBien = table.getValueAt(selectedRow, 0).toString();
                CheBienMonController controller = new CheBienMonController(this, new CheBienMonDAO());
                controller.xoaCheBienMon(maCheBien);
                connectData.loadData(tableModel, "SELECT * FROM chebienmonan", columns);
                clearForm();
            }
        });

        btnLuuThem.addActionListener(e -> {
            CheBienMonController controller = new CheBienMonController(this, new CheBienMonDAO());
            controller.themCheBienMon();
            connectData.loadData(tableModel, "SELECT * FROM chebienmonan", columns);
            clearForm();
        });

        btnLuuSua.addActionListener(e -> {
            CheBienMonController controller = new CheBienMonController(this, new CheBienMonDAO());
            controller.suaCheBienMon();
            connectData.loadData(tableModel, "SELECT * FROM chebienmonan", columns);
            clearForm();
        });

        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });

        btnRefresh.addActionListener(e -> {
            CheBienMonController controller = new CheBienMonController(this, new CheBienMonDAO());
            controller.refreshTable();
            connectData.loadData(tableModel, "SELECT * FROM chebienmonan", new String[]{"Mã chế biến", "Mã món", "Số bàn", "Mã hóa đơn", "Tình trạng"});
            clearForm();
        });
    }

    private void clearForm() {
        txtMaCheBien.setText("");
        txtMaMon.setText("");
        txtSoBan.setText("");
        txtMaHoaDonBanHang.setText("");
        cmbTinhTrang.setSelectedIndex(0);
    }

    private void fillForm(int row) {
        txtMaCheBien.setText(tableModel.getValueAt(row, 0).toString());
        txtMaMon.setText(tableModel.getValueAt(row, 1).toString());
        txtSoBan.setText(tableModel.getValueAt(row, 2).toString());
        txtMaHoaDonBanHang.setText(tableModel.getValueAt(row, 3).toString());
        cmbTinhTrang.setSelectedItem(tableModel.getValueAt(row, 4).toString());
    }
}