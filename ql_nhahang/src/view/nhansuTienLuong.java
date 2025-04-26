package view;

import controller.TienLuongController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.TienLuongDAO;
import model.connectData;

public class nhansuTienLuong extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaLuong, txtMaNV, txtTenNV, txtTinhTrangLuong, txtGioLam, txtGhiChu, txtTongTien;
    private JComboBox<String> cbTinhTrangLuong;
    private JButton btnLuuThem, btnHuy, btnLuuSua, btnRefresh;
    private int selectedRow = -1;

    // Getters và Setters
    public JTextField getTxtGioLam() {
        return txtGioLam;
    }

    public void setTxtGioLam(JTextField txtGioLam) {
        this.txtGioLam = txtGioLam;
    }

    public JTextField getTxtGhiChu() {
        return txtGhiChu;
    }

    public void setTxtGhiChu(JTextField txtGhiChu) {
        this.txtGhiChu = txtGhiChu;
    }

    public JTextField getTxtTongTien() {
        return txtTongTien;
    }

    public void setTxtTongTien(JTextField txtTongTien) {
        this.txtTongTien = txtTongTien;
    }

    public JTextField getTxtMaLuong() {
        return txtMaLuong;
    }

    public void setTxtMaLuong(JTextField txtMaLuong) {
        this.txtMaLuong = txtMaLuong;
    }

    public JTextField getTxtMaNV() {
        return txtMaNV;
    }

    public void setTxtMaNV(JTextField txtMaNV) {
        this.txtMaNV = txtMaNV;
    }

    public JTextField getTxtTenNV() {
        return txtTenNV;
    }

    public void setTxtTenNV(JTextField txtTenNV) {
        this.txtTenNV = txtTenNV;
    }

    public JTextField getTxtTinhTrangLuong() {
        return txtTinhTrangLuong;
    }

    public void setTxtTinhTrangLuong(JTextField txtTinhTrangLuong) {
        this.txtTinhTrangLuong = txtTinhTrangLuong;
    }

    public JComboBox<String> getCbTinhTrangLuong() {
        return cbTinhTrangLuong;
    }

    public void setCbTinhTrangLuong(JComboBox<String> cbTinhTrangLuong) {
        this.cbTinhTrangLuong = cbTinhTrangLuong;
    }

    public nhansuTienLuong() {
        String[] tienluongColumns = {"Mã lương", "Mã nhân viên", "Tên nhân viên", "Tình trạng", "Giờ làm", "Ghi chú", "Tổng tiền"};
        setLayout(new BorderLayout(10, 10));
        tableModel = new DefaultTableModel(tienluongColumns, 0);
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

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu từ database
        connectData.loadData(tableModel, """
                SELECT tl.MaLuong, tl.MaNhanVien, nv.TenNhanVien, tl.TinhTrangLuong, tl.GioLamViec, tl.GhiChu, 
                (nv.Luong1Gio * tl.GioLamViec) AS TongTien
                FROM tienluong tl
                JOIN nhanvien nv ON tl.MaNhanVien = nv.MaNhanVien
                """, tienluongColumns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin lương"));
        formPanel.setBackground(Color.WHITE);

        // Các input field
        txtMaLuong = new JTextField();
        txtMaNV = new JTextField();
        txtTenNV = new JTextField();
        txtGioLam = new JTextField();
        txtGhiChu = new JTextField();
        txtTongTien = new JTextField();
        txtTinhTrangLuong = new JTextField();

        // Combo box tình trạng lương
        cbTinhTrangLuong = new JComboBox<>(new String[]{"Đã trả", "Chưa trả"});
        cbTinhTrangLuong.setSelectedIndex(0);
        cbTinhTrangLuong.addActionListener(e -> {
            txtTinhTrangLuong.setText(cbTinhTrangLuong.getSelectedItem().toString());
        });

        // Tạo các button chức năng
        btnLuuThem = new JButton("Thêm");
        btnLuuSua = new JButton("Sửa");
        btnHuy = new JButton("Hủy");

        btnLuuThem.setBackground(new Color(76, 175, 80));
        btnLuuThem.setForeground(Color.WHITE);

        btnLuuSua.setBackground(new Color(255, 193, 7));
        btnLuuSua.setForeground(Color.BLACK);

        btnHuy.setBackground(new Color(108, 117, 125));
        btnHuy.setForeground(Color.WHITE);

        // Thêm các thành phần vào form
        formPanel.add(new JLabel("Mã lương:"));
        formPanel.add(txtMaLuong);
        formPanel.add(new JLabel("Mã nhân viên:"));
        formPanel.add(txtMaNV);
        formPanel.add(new JLabel("Tên nhân viên:"));
        formPanel.add(txtTenNV);
        formPanel.add(new JLabel("Tình trạng lương:"));
        formPanel.add(cbTinhTrangLuong);
        formPanel.add(new JLabel("Giờ làm:"));
        formPanel.add(txtGioLam);
        formPanel.add(new JLabel("Ghi chú:"));
        formPanel.add(txtGhiChu);
        formPanel.add(new JLabel("Tổng tiền:"));
        formPanel.add(txtTongTien);
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));
        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
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
                formPanel.setVisible(true);
                fillForm(selectedRow);
            }
        });

        // Sự kiện "Xóa"
        btnXoa.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn lương cần xóa!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa lương này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String maLuong = tableModel.getValueAt(selectedRow, 0).toString();
                TienLuongController controller = new TienLuongController(nhansuTienLuong.this, new TienLuongDAO());
                controller.xoaTienLuong();
                connectData.loadData(tableModel, """
                        SELECT tl.MaLuong, tl.MaNhanVien, nv.TenNhanVien, tl.TinhTrangLuong, tl.GioLamViec, tl.GhiChu, 
                        (nv.Luong1Gio * tl.GioLamViec) AS TongTien
                        FROM tienluong tl
                        JOIN nhanvien nv ON tl.MaNhanVien = nv.MaNhanVien
                        """, tienluongColumns);
            }
            clearForm();
        });

        // Sự kiện "Lưu Thêm"
        btnLuuThem.addActionListener(e -> {
            TienLuongController controller = new TienLuongController(nhansuTienLuong.this, new TienLuongDAO());
            controller.themTienLuong();
            connectData.loadData(tableModel, """
                    SELECT tl.MaLuong, tl.MaNhanVien, nv.TenNhanVien, tl.TinhTrangLuong, tl.GioLamViec, tl.GhiChu, 
                    (nv.Luong1Gio * tl.GioLamViec) AS TongTien
                    FROM tienluong tl
                    JOIN nhanvien nv ON tl.MaNhanVien = nv.MaNhanVien
                    """, tienluongColumns);
            formPanel.setVisible(false);
        });

        // Sự kiện "Lưu Sửa"
        btnLuuSua.addActionListener(e -> {
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!");
                return;
            }
            TienLuongController controller = new TienLuongController(nhansuTienLuong.this, new TienLuongDAO());
            controller.suaTienLuong();
            connectData.loadData(tableModel, """
                    SELECT tl.MaLuong, tl.MaNhanVien, nv.TenNhanVien, tl.TinhTrangLuong, tl.GioLamViec, tl.GhiChu, 
                    (nv.Luong1Gio * tl.GioLamViec) AS TongTien
                    FROM tienluong tl
                    JOIN nhanvien nv ON tl.MaNhanVien = nv.MaNhanVien
                    """, tienluongColumns);
            formPanel.setVisible(false);
        });

        // Sự kiện "Hủy"
        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });

        // Sự kiện "Refresh"
        btnRefresh.addActionListener(e -> {
            connectData.loadData(tableModel, """
                    SELECT tl.MaLuong, tl.MaNhanVien, nv.TenNhanVien, tl.TinhTrangLuong, tl.GioLamViec, tl.GhiChu, 
                    (nv.Luong1Gio * tl.GioLamViec) AS TongTien
                    FROM tienluong tl
                    JOIN nhanvien nv ON tl.MaNhanVien = nv.MaNhanVien
                    """, tienluongColumns);
        });
    }

    private void clearForm() {
        txtMaLuong.setText("");
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtTinhTrangLuong.setText("");
        txtGioLam.setText("");
        txtGhiChu.setText("");
        txtTongTien.setText("");
        cbTinhTrangLuong.setSelectedIndex(0);
    }

    private void fillForm(int row) {
        txtMaLuong.setText(tableModel.getValueAt(row, 0).toString());
        txtMaNV.setText(tableModel.getValueAt(row, 1).toString());
        txtTenNV.setText(tableModel.getValueAt(row, 2).toString());
        cbTinhTrangLuong.setSelectedItem(tableModel.getValueAt(row, 3).toString());
        txtGioLam.setText(tableModel.getValueAt(row, 4).toString());
        txtGhiChu.setText(tableModel.getValueAt(row, 5).toString());
        txtTongTien.setText(tableModel.getValueAt(row, 6).toString());
    }
}