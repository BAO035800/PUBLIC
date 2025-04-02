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
    private JTextField txtID, txtMaMon, txtMaHoaDonBanHang, txtSoBan, txtGiaTien, txtSoLuongDat, txtTongTien;
    private JButton btnLuuThem, btnHuy, btnLuuSua, btnRefresh;
    private int selectedRow = -1;
    
    public JTextField getTxtID() { return txtID; }
    public JTextField getTxtMaMon() { return txtMaMon; }
    public JTextField getTxtMaHoaDonBanHang() { return txtMaHoaDonBanHang; }
    public JTextField getTxtSoBan() { return txtSoBan; }
    public JTextField getTxtGiaTien() { return txtGiaTien; }
    public JTextField getTxtSoLuongDat() { return txtSoLuongDat; }
    public JTextField getTxtTongTien() { return txtTongTien; }
    
    public banhangChiTiet() {
        String[] chitietColumns = {"STT", "Mã món", "Mã hóa đơn", "Số bàn", "Giá tiền", "Số lượng đặt", "Tổng tiền"};
        setLayout(new BorderLayout(10, 10));
        
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
        
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        connectData.loadData(tableModel, """
            SELECT cthd.ID, cthd.MaMon, cthd.MaHoaDonBanHang, cthd.SoBan, 
                   m.GiaTien, cthd.SoLuongDat, 
                   (m.GiaTien * cthd.SoLuongDat) AS TongTien
            FROM hoadonbanhangchitiet cthd
            JOIN menu m ON cthd.MaMon = m.MaMon
        """, chitietColumns);
        
        formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập chi tiết hóa đơn"));
        
        txtID = new JTextField();
        txtID.setEnabled(false); 
        txtMaMon = new JTextField();
        txtMaHoaDonBanHang = new JTextField();
        txtSoBan = new JTextField();
        txtGiaTien = new JTextField();
        txtSoLuongDat = new JTextField();
        txtTongTien = new JTextField();
        
        btnLuuThem = new JButton("Thêm");
        btnLuuSua = new JButton("Sửa");
        btnHuy = new JButton("Hủy");
        btnRefresh = new JButton("Refresh");
        
        formPanel.add(new JLabel("Mã món:"));
        formPanel.add(txtMaMon);
        formPanel.add(new JLabel("Mã hóa đơn:"));
        formPanel.add(txtMaHoaDonBanHang);
        formPanel.add(new JLabel("Số bàn:"));
        formPanel.add(txtSoBan);
        formPanel.add(new JLabel("Số lượng đặt:"));
        formPanel.add(txtSoLuongDat);
        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);
        formPanel.add(btnRefresh);
        // Màu xanh lá cho nút Thêm (Thành công)
        btnLuuThem.setBackground(new Color(76, 175, 80)); 
        btnLuuThem.setForeground(Color.WHITE);

        // Màu cam cho nút Sửa (Cảnh báo nhẹ)
        btnLuuSua.setBackground(new Color(255, 193, 7)); 
        btnLuuSua.setForeground(Color.BLACK);

        // Màu đỏ cho nút Hủy (Nguy hiểm)
        btnHuy.setBackground(new Color(108, 117, 125));
        btnHuy.setForeground(Color.WHITE);

        // Màu xanh dương cho nút Refresh (Hiện đại)
        btnRefresh.setBackground(new Color(0, 123, 255)); 
        btnRefresh.setForeground(Color.WHITE);
        
        add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(false);
        
        table.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                txtID.setText(tableModel.getValueAt(selectedRow, 0).toString());
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
            if (txtID.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn chi tiết cần xóa!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                HDChiTietController controller = new HDChiTietController(this, new HoaDonChiTietDAO(), new Menu());
                controller.xoaHDChiTiet();
                connectData.loadData(tableModel, """
                    SELECT cthd.ID, cthd.MaMon, cthd.MaHoaDonBanHang, cthd.SoBan, 
                    m.GiaTien, cthd.SoLuongDat, 
                   (m.GiaTien * cthd.SoLuongDat) AS TongTien
                    FROM hoadonbanhangchitiet cthd
                    JOIN menu m ON cthd.MaMon = m.MaMon
                    """, chitietColumns);
            }
        });
        
        btnLuuThem.addActionListener(e -> {
            HDChiTietController controller = new HDChiTietController(this, new HoaDonChiTietDAO(), new Menu());
            controller.themHDChiTiet();
            connectData.loadData(tableModel, """
                    SELECT cthd.ID, cthd.MaMon, cthd.MaHoaDonBanHang, cthd.SoBan, 
                    m.GiaTien, cthd.SoLuongDat, 
                   (m.GiaTien * cthd.SoLuongDat) AS TongTien
                    FROM hoadonbanhangchitiet cthd
                    JOIN menu m ON cthd.MaMon = m.MaMon
                    """, chitietColumns);
        });
        
        btnLuuSua.addActionListener(e -> {
            HDChiTietController controller = new HDChiTietController(this, new HoaDonChiTietDAO(), new Menu());
            controller.suaHDChiTiet();
            connectData.loadData(tableModel, """
                    SELECT cthd.ID, cthd.MaMon, cthd.MaHoaDonBanHang, cthd.SoBan, 
                    m.GiaTien, cthd.SoLuongDat, 
                   (m.GiaTien * cthd.SoLuongDat) AS TongTien
                    FROM hoadonbanhangchitiet cthd
                    JOIN menu m ON cthd.MaMon = m.MaMon
                    """, chitietColumns);
        });
        
        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });
        
        btnRefresh.addActionListener(e -> {
            connectData.loadData(tableModel, """
                    SELECT cthd.ID, cthd.MaMon, cthd.MaHoaDonBanHang, cthd.SoBan, 
                    m.GiaTien, cthd.SoLuongDat, 
                   (m.GiaTien * cthd.SoLuongDat) AS TongTien
                    FROM hoadonbanhangchitiet cthd
                    JOIN menu m ON cthd.MaMon = m.MaMon
                    """, chitietColumns);
        });
    }
    
    public void clearForm() {
        txtID.setText("");
        txtMaMon.setText("");
        txtMaHoaDonBanHang.setText("");
        txtSoBan.setText("");
        txtGiaTien.setText("");
        txtSoLuongDat.setText("");
        txtTongTien.setText("");
    }
    
    public void fillForm(int row) {
        txtID.setText(tableModel.getValueAt(row, 0).toString());
        txtMaMon.setText(tableModel.getValueAt(row, 1).toString());
        txtMaHoaDonBanHang.setText(tableModel.getValueAt(row, 2).toString());
        txtSoBan.setText(tableModel.getValueAt(row, 3).toString());
        txtGiaTien.setText(tableModel.getValueAt(row, 4).toString());
        txtSoLuongDat.setText(tableModel.getValueAt(row, 5).toString());
        txtTongTien.setText(tableModel.getValueAt(row, 6).toString());
    }
    
}
