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
    private JTextField txtMaHD, txtSoBan, txtTongTien,txtGhiChu;
    private JButton btnLuuThem, btnHuy, btnLuuSua, btnRefresh;
    private int selectedRow = -1;
    
    public JTextField getTxtMaHD() {
        return txtMaHD;
    }

    public void setTxtMaHD(JTextField txtMaHD) {
        this.txtMaHD = txtMaHD;
    }

    public JTextField getTxtSoBan() {
        return txtSoBan;
    }

    public void setTxtSoBan(JTextField txtSoBan) {
        this.txtSoBan = txtSoBan;
    }

    public JTextField getTxtTongTien() {
        return txtTongTien;
    }

    public void setTxtTongTien(JTextField txtTongTien) {
        this.txtTongTien = txtTongTien;
    }

    public JTextField getTxtGhiChu() {
        return txtGhiChu;
    }

    public void setTxtGhiChu(JTextField txtGhiChu) {
        this.txtGhiChu = txtGhiChu;
    }

    public banhangHoaDonBanHang() {
        String[] hoaDonColumns = {"Mã hóa đơn", "Số bàn", "Tổng tiền","Ghi chú"};
        setLayout(new BorderLayout(10, 10));

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

        // Tạo bảng
        tableModel = new DefaultTableModel(hoaDonColumns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Load dữ liệu từ database
        connectData.loadData(tableModel, """
            SELECT hd.MaHoaDonBanHang, hd.SoBan, 
            COALESCE(SUM(m.GiaTien * cthd.SoLuongDat), 0) AS TongTien, hd.GhiChu
            FROM hoadonbanhang hd
            LEFT JOIN hoadonbanhangchitiet cthd ON cthd.MaHoaDonBanHang = hd.MaHoaDonBanHang AND cthd.SoBan = hd.SoBan
            LEFT JOIN menu m ON cthd.MaMon = m.MaMon
            GROUP BY hd.MaHoaDonBanHang, hd.SoBan, hd.GhiChu
            """, hoaDonColumns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập hóa đơn"));
        formPanel.setBackground(Color.WHITE);

        txtMaHD = new JTextField();
        txtSoBan = new JTextField();
        txtTongTien = new JTextField();
        txtGhiChu = new JTextField();

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

        formPanel.add(new JLabel("Mã hóa đơn:"));
        formPanel.add(txtMaHD);
        formPanel.add(new JLabel("Số bàn:"));
        formPanel.add(txtSoBan);
        formPanel.add(new JLabel("Ghi chú"));
        formPanel.add(txtGhiChu);
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);
        formPanel.add(btnRefresh);

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

        btnRefresh.addActionListener(e -> {
            connectData.loadData(tableModel, """
                SELECT hd.MaHoaDonBanHang, hd.SoBan, 
                COALESCE(SUM(m.GiaTien * cthd.SoLuongDat), 0) AS TongTien, hd.GhiChu
                FROM hoadonbanhang hd
                LEFT JOIN hoadonbanhangchitiet cthd ON cthd.MaHoaDonBanHang = hd.MaHoaDonBanHang AND cthd.SoBan = hd.SoBan
                LEFT JOIN menu m ON cthd.MaMon = m.MaMon
                GROUP BY hd.MaHoaDonBanHang, hd.SoBan, hd.GhiChu
                """, hoaDonColumns);
        });
    }

    public void clearForm() {
        txtMaHD.setText("");
        txtSoBan.setText("");
        txtTongTien.setText("");
        txtGhiChu.setText("");
    }

    public void fillForm(int row) {
        txtMaHD.setText(tableModel.getValueAt(row, 0).toString());
        txtSoBan.setText(tableModel.getValueAt(row, 1).toString());
        txtTongTien.setText(tableModel.getValueAt(row, 2).toString());
        txtGhiChu.setText(tableModel.getValueAt(row,3).toString());
    }
}