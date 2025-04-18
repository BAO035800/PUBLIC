package view;

import controller.PhanCongBepController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.PhanCongBepDAO;
import model.connectData;

public class bepPhanCongBep extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaCheBien, txtMaNhanVien,txtVaiTro;
    private JButton btnLuuThem, btnHuy, btnLuuSua, btnRefresh;
    private int selectedRow = -1;

    public bepPhanCongBep() {
        String[] columns = {"Mã chế biến", "Mã nhân viên", "Chức vụ"};
        setLayout(new BorderLayout(10, 10));

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        TableStyler.styleTable(table);

        // Panel chứa nút chức năng
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
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        connectData.loadData(tableModel, "SELECT * FROM phancongbep", columns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin phân công bếp"));
        formPanel.setBackground(Color.WHITE);

        txtMaCheBien = new JTextField();
        txtMaNhanVien = new JTextField();
        txtVaiTro = new JTextField();

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

        formPanel.add(new JLabel("Mã chế biến:"));
        formPanel.add(txtMaCheBien);
        formPanel.add(new JLabel("Mã nhân viên:"));
        formPanel.add(txtMaNhanVien);
        formPanel.add(new JLabel("Chức vụ:"));
        formPanel.add(txtVaiTro);

        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);
        formPanel.add(btnRefresh);

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
                formPanel.setVisible(true);
                fillForm(selectedRow);
            }
        });

        btnXoa.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
            } else {
                String maCB = tableModel.getValueAt(selectedRow, 0).toString();
                PhanCongBepController controller = new PhanCongBepController(this, new PhanCongBepDAO());
                controller.xoaPhanCong(maCB, maCB);
                clearForm();
                connectData.loadData(tableModel, "SELECT * FROM phancongbep", columns);
            }
        });

        btnLuuThem.addActionListener(e -> {
            PhanCongBepController controller = new PhanCongBepController(this, new PhanCongBepDAO());
            controller.themPhanCong();
            clearForm();
            connectData.loadData(tableModel, "SELECT * FROM phancongbep", columns);
        });

        btnLuuSua.addActionListener(e -> {
            PhanCongBepController controller = new PhanCongBepController(this, new PhanCongBepDAO());
            controller.suaPhanCong();
            clearForm();
            connectData.loadData(tableModel, "SELECT * FROM phancongbep", columns);
        });

        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });

        btnRefresh.addActionListener(e -> {
            connectData.loadData(tableModel, "SELECT * FROM phancongbep", columns);
        });
    }

    private void clearForm() {
        txtMaCheBien.setText("");
        txtMaNhanVien.setText("");
        txtVaiTro.setText("");
    }

    private void fillForm(int row) {
        txtMaCheBien.setText(table.getValueAt(row, 0).toString());
        txtMaNhanVien.setText(table.getValueAt(row, 1).toString());
        txtVaiTro.setText(table.getValueAt(row, 2).toString());
    }

    public JTextField getTxtMaCheBien() {
        return txtMaCheBien;
    }

    public JTextField getTxtMaNhanVien() {
        return txtMaNhanVien;
    }

    public JTextField getTxtVaiTro() {
        return txtVaiTro;
    }
}