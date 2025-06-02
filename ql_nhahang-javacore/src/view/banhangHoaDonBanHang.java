package view;

import controller.HDBanHangController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.HoaDonBanHangDAO;
import model.connectData;

public class banhangHoaDonBanHang extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaHD, txtTongTien;
    private JComboBox<String> cbTrangThai; // JComboBox cho TrangThai
    private JButton btnLuuThem, btnHuy, btnLuuSua, btnRefresh;
    private int selectedRow = -1;

    public JTextField getTxtMaHD() {
        return txtMaHD;
    }

    public JTextField getTxtTongTien() {
        return txtTongTien;
    }

    public JComboBox<String> getCbTrangThai() { // Getter cho TrangThai
        return cbTrangThai;
    }

    public banhangHoaDonBanHang() {
        // Cập nhật cột bảng, bỏ GhiChu
        String[] hoaDonColumns = {"Mã hóa đơn", "Tổng tiền", "Trạng thái"};
        setLayout(new BorderLayout(10, 10));
        tableModel = new DefaultTableModel(hoaDonColumns, 0);
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
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Load dữ liệu từ database
        btnRefresh.addActionListener(e -> loadHoaDon(hoaDonColumns));
        loadHoaDon(hoaDonColumns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập hóa đơn"));
        formPanel.setBackground(Color.WHITE);

        txtMaHD = new JTextField();
        txtTongTien = new JTextField();
        txtTongTien.setEditable(false); // Không cho sửa

        // JComboBox cho TrangThai
        cbTrangThai = new JComboBox<>(new String[]{"Đã thanh toán", "Chưa thanh toán"});
        cbTrangThai.setSelectedIndex(1); // Mặc định là "Chưa thanh toán"

        btnLuuThem = new JButton("Thêm");
        btnLuuSua = new JButton("Sửa");
        btnHuy = new JButton("Hủy");

        btnLuuThem.setBackground(new Color(76, 175, 80));
        btnLuuThem.setForeground(Color.WHITE);
        btnLuuSua.setBackground(new Color(255, 193, 7));
        btnLuuSua.setForeground(Color.BLACK);
        btnHuy.setBackground(new Color(108, 117, 125));
        btnHuy.setForeground(Color.WHITE);

        formPanel.add(new JLabel("Mã hóa đơn:"));
        formPanel.add(txtMaHD);
        formPanel.add(new JLabel("Tổng tiền:"));
        formPanel.add(txtTongTien);
        formPanel.add(new JLabel("Trạng thái:"));
        formPanel.add(cbTrangThai);
        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);
        formPanel.add(new JLabel()); // Chỗ trống

        add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(false);

        btnThem.addActionListener(e -> {
            selectedRow = -1;
            clearForm();
            formPanel.setVisible(true);
        });

        btnSua.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần sửa!");
            } else {
                formPanel.setVisible(true);
                fillForm(selectedRow);
            }
        });

        btnXoa.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa hóa đơn này?");
            if (confirm == JOptionPane.YES_OPTION) {
                String maHD = table.getValueAt(selectedRow, 0).toString();
                HDBanHangController controller = new HDBanHangController(this, new HoaDonBanHangDAO());
                controller.xoaHDBanHang(maHD);
                btnRefresh.doClick();
            }
        });

        btnLuuThem.addActionListener(e -> {
            HDBanHangController controller = new HDBanHangController(this, new HoaDonBanHangDAO());
            controller.themHDBanHang();
            btnRefresh.doClick();
        });

        btnLuuSua.addActionListener(e -> {
            HDBanHangController controller = new HDBanHangController(this, new HoaDonBanHangDAO());
            controller.suaHDBanHang();
            btnRefresh.doClick();
        });

        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });
    }

    public void clearForm() {
        txtMaHD.setText("");
        txtTongTien.setText("");
        cbTrangThai.setSelectedIndex(1); // Mặc định "Chưa thanh toán"
    }

    public void fillForm(int row) {
        txtMaHD.setText(tableModel.getValueAt(row, 0).toString());
        txtTongTien.setText(tableModel.getValueAt(row, 1).toString());
        cbTrangThai.setSelectedItem(tableModel.getValueAt(row, 2).toString()); // Điền trạng thái (cột thứ 4)
    }

    private void loadHoaDon(String[] columns) {
        connectData.loadData(tableModel, """
            SELECT hd.MaHoaDonBanHang, 
                   COALESCE(SUM(m.GiaTien * cthd.SoLuongDat), 0) AS TongTien, 
                   COALESCE(hd.TrangThai, 'Chưa thanh toán') AS TrangThai
            FROM hoadonbanhang hd
            LEFT JOIN hoadonbanhangchitiet cthd ON cthd.MaHoaDonBanHang = hd.MaHoaDonBanHang
            LEFT JOIN menu m ON cthd.MaMon = m.MaMon
            GROUP BY hd.MaHoaDonBanHang, hd.TrangThai
        """, columns);
    }
    
}