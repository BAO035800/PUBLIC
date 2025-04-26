package view;

import controller.HDChiTietController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.HoaDonChiTietDAO;
import model.Menu;
import model.connectData;

public class banhangChiTiet extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaMon, txtMaHoaDonBanHang, txtSoBan, txtGiaTien, txtSoLuongDat, txtTongTien;
    private JButton btnLuuThem, btnHuy, btnLuuSua;
    private int selectedRow = -1;

    // Getter methods
    public JTextField getTxtMaMon() { return txtMaMon; }
    public JTextField getTxtMaHoaDonBanHang() { return txtMaHoaDonBanHang; }
    public JTextField getTxtSoBan() { return txtSoBan; }
    public JTextField getTxtGiaTien() { return txtGiaTien; }
    public JTextField getTxtSoLuongDat() { return txtSoLuongDat; }
    public JTextField getTxtTongTien() { return txtTongTien; }

    public banhangChiTiet() {
        String[] chitietColumns = { "Mã món", "Mã hóa đơn", "Số bàn", "Giá tiền", "Số lượng đặt", "Tổng tiền"};
        setLayout(new BorderLayout(10, 10));

        // Table + scroll
        tableModel = new DefaultTableModel(chitietColumns, 0);
        table = new JTable(tableModel);
        TableStyler.styleTable(table);  // Ensure TableStyler is correctly applied
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel nút chức năng phía trên
        JPanel controlPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        JButton btnRefresh = new JButton("Refresh");

        // Set màu sắc cho các nút
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

        // Load dữ liệu
        connectData.loadData(tableModel, """
            SELECT cthd.MaMon, cthd.MaHoaDonBanHang, cthd.SoBan, 
                   m.GiaTien, cthd.SoLuongDat, 
                   (m.GiaTien * cthd.SoLuongDat) AS TongTien
            FROM hoadonbanhangchitiet cthd
            JOIN menu m ON cthd.MaMon = m.MaMon
        """, chitietColumns);

        // Form nhập liệu
        formPanel = new JPanel(new GridLayout(3, 5, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập chi tiết hóa đơn"));

        txtMaMon = new JTextField();
        txtMaHoaDonBanHang = new JTextField();
        txtSoBan = new JTextField();
        txtGiaTien = new JTextField(); txtGiaTien.setEnabled(false);
        txtSoLuongDat = new JTextField();
        txtTongTien = new JTextField(); txtTongTien.setEnabled(false);

        btnLuuThem = new JButton("Lưu thêm");
        btnLuuSua = new JButton("Lưu sửa");
        btnHuy = new JButton("Hủy");

        formPanel.add(new JLabel("Mã món:")); formPanel.add(txtMaMon);
        formPanel.add(new JLabel("Mã hóa đơn:")); formPanel.add(txtMaHoaDonBanHang);
        formPanel.add(new JLabel("Số bàn:")); formPanel.add(txtSoBan);
        formPanel.add(new JLabel("Số lượng đặt:")); formPanel.add(txtSoLuongDat);
        formPanel.add(btnHuy); formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua); // Thêm dòng nếu cần
        add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(false);

        // ======= SỰ KIỆN =======
        table.getSelectionModel().addListSelectionListener(event -> {
            int row = table.getSelectedRow();            
            if (row >= 0) {
                txtMaMon.setText(tableModel.getValueAt(row, 0).toString());
                txtMaHoaDonBanHang.setText(tableModel.getValueAt(row, 1).toString());
                txtSoBan.setText(tableModel.getValueAt(row, 2).toString());
                txtGiaTien.setText(tableModel.getValueAt(row, 3).toString());
                txtSoLuongDat.setText(tableModel.getValueAt(row, 4).toString());
                txtTongTien.setText(tableModel.getValueAt(row, 5).toString());
            }
        });

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
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new HDChiTietController(this, new HoaDonChiTietDAO(), new Menu()).xoaHDChiTiet();
                reloadData(chitietColumns);
            }
        });

        btnRefresh.addActionListener(e -> reloadData(chitietColumns));

        btnLuuThem.addActionListener(e -> {
            new HDChiTietController(this, new HoaDonChiTietDAO(), new Menu()).themHDChiTiet();
            reloadData(chitietColumns);
        });

        btnLuuSua.addActionListener(e -> {
            new HDChiTietController(this, new HoaDonChiTietDAO(), new Menu()).suaHDChiTiet();
            reloadData(chitietColumns);
        });

        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });

        // Màu sắc các nút trong form
        btnLuuThem.setBackground(new Color(76, 175, 80)); btnLuuThem.setForeground(Color.WHITE);
        btnLuuSua.setBackground(new Color(255, 193, 7)); btnLuuSua.setForeground(Color.BLACK);
        btnHuy.setBackground(new Color(108, 117, 125)); btnHuy.setForeground(Color.WHITE);
    }

    public void clearForm() {
        txtMaMon.setText("");
        txtMaHoaDonBanHang.setText("");
        txtSoBan.setText("");
        txtGiaTien.setText("");
        txtSoLuongDat.setText("");
        txtTongTien.setText("");
    }

    public void fillForm(int row) {
        txtMaMon.setText(tableModel.getValueAt(row, 0).toString());
        txtMaHoaDonBanHang.setText(tableModel.getValueAt(row, 1).toString());
        txtSoBan.setText(tableModel.getValueAt(row, 2).toString());
        txtGiaTien.setText(tableModel.getValueAt(row, 3).toString());
        txtSoLuongDat.setText(tableModel.getValueAt(row, 4).toString());
        txtTongTien.setText(tableModel.getValueAt(row, 5).toString());
    }

    private void reloadData(String[] columns) {
        connectData.loadData(tableModel, """
            SELECT  cthd.MaMon, cthd.MaHoaDonBanHang, cthd.SoBan, 
                   m.GiaTien, cthd.SoLuongDat, 
                   (m.GiaTien * cthd.SoLuongDat) AS TongTien
            FROM hoadonbanhangchitiet cthd
            JOIN menu m ON cthd.MaMon = m.MaMon
        """, columns);
    }
}
