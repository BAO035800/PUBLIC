package view;

import controller.HDNhapController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.HoaDonNhapDAO;
import model.connectData;
public class khoHoaDonNhap extends JPanel{
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaHoaDon, txtMaNguyenLieu, txtMaNCC, txtSoLuong, txtTongTien;
    private JButton btnLuuThem, btnHuy, btnLuuSua, btnRefresh;
    private int selectedRow = -1;
    

    public JTextField getTxtMaHoaDon() {
        return txtMaHoaDon;
    }

    public JTextField getTxtMaNguyenLieu() {
        return txtMaNguyenLieu;
    }

    public JTextField getTxtMaNCC() {
        return txtMaNCC;
    }

    public JTextField getTxtSoLuong() {
        return txtSoLuong;
    }

    public JTextField getTxtTongTien() {
        return txtTongTien;
    }

    public khoHoaDonNhap() {
        String[] hdnColumns = {"Mã hóa đơn", "Mã nguyên liệu", "Mã nhà cung cấp","Số lượng", "Tổng tiền"};
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

        // Bảng dữ liệu menu
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu từ database
        connectData.loadData(tableModel, "SELECT h.MaHoaDonKho, h.MaNguyenLieu, h.MaNhaCungCap, k.SoLuong, (k.GiaTien  * k.SoLuong) AS TongTien FROM hoadonnhap h JOIN khoNguyenLieu k ON h.MaNguyenLieu = k.MaNguyenLieu", hdnColumns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin hóa đơn nhập"));
        formPanel.setBackground(Color.WHITE);

        // Các input field
        txtMaHoaDon = new JTextField();
        txtMaNguyenLieu = new JTextField();
        txtMaNCC = new JTextField();
        txtSoLuong = new JTextField();
        txtTongTien = new JTextField();
        // Tạo các button chức năng
        btnLuuThem = new JButton("Thêm");
        btnLuuSua = new JButton("Sửa");
        btnHuy = new JButton("Hủy");
        btnRefresh = new JButton("Refresh");
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

        // Thêm các thành phần vào form
        formPanel.add(new JLabel("Mã hóa đơn"));
        formPanel.add(txtMaHoaDon);
        formPanel.add(new JLabel("Mã nguyên liệu"));
        formPanel.add(txtMaNguyenLieu);
        formPanel.add(new JLabel("Mã nhà cung cấp"));
        formPanel.add(txtMaNCC);

        // Thêm nút Lưu và Hủy
        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(false);


        // Thêm nút Lưu và Hủy
        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);
        formPanel.add(btnRefresh);

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
            // Lấy dòng đã chọn trong bảng
            selectedRow = table.getSelectedRow();
        
            // Kiểm tra nếu không có dòng nào được chọn
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
                return;  // Dừng lại nếu không có dòng nào được chọn
            }
        
            // Lấy mã nhà cung cấp từ cột 0 trong bảng (giả sử là MaNCC)
            String maNCC = tableModel.getValueAt(selectedRow, 0).toString();
        
            // Thực hiện xóa hóa đơn nhập từ controller
            HDNhapController controller = new HDNhapController(khoHoaDonNhap.this, new HoaDonNhapDAO());
        
            try {
                // Truyền maNCC hoặc mã hóa đơn cần xóa vào controller (nếu cần)
                controller.xoaHDNhap(maNCC);
        
                // Làm sạch form sau khi xóa
                clearForm();
        
                // Tải lại dữ liệu cho bảng
                connectData.loadData(tableModel, """
                    SELECT h.MaHoaDonKho, h.MaNguyenLieu, h.MaNhaCungCap, k.SoLuong, 
                    (k.GiaTien * k.SoLuong) AS TongTien 
                    FROM hoadonnhap h 
                    JOIN khoNguyenLieu k ON h.MaNguyenLieu = k.MaNguyenLieu
                    """, hdnColumns);
        
                JOptionPane.showMessageDialog(this, "Xóa hóa đơn nhập thành công!");
            } catch (Exception ex) {
                // Xử lý lỗi khi xóa không thành công
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa hóa đơn nhập: " + ex.getMessage(), 
                                              "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        

        // Sự kiện "Lưu Thêm"
        btnLuuThem.addActionListener(e -> {
           HDNhapController controller = new HDNhapController(khoHoaDonNhap.this, new HoaDonNhapDAO());
           controller.themHDNhap();
           connectData.loadData(tableModel, "SELECT h.MaHoaDonKho, h.MaNguyenLieu, h.MaNhaCungCap, k.SoLuong, (k.GiaTien * k.SoLuong) AS TongTien FROM hoadonnhap h JOIN khoNguyenLieu k ON h.MaNguyenLieu = k.MaNguyenLieu", hdnColumns);
        });

        // Sự kiện "Lưu Sửa"
        btnLuuSua.addActionListener(e -> {
            HDNhapController controller = new HDNhapController(khoHoaDonNhap.this, new HoaDonNhapDAO());
            controller.suaHDNhap();
            clearForm();
            connectData.loadData(tableModel, "SELECT h.MaHoaDonKho, h.MaNguyenLieu, h.MaNhaCungCap, k.SoLuong, (k.GiaTien * k.SoLuong) AS TongTien FROM hoadonnhap h JOIN khoNguyenLieu k ON h.MaNguyenLieu = k.MaNguyenLieu", hdnColumns);
        });

        // Sự kiện "Hủy"
        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });

        // Sự kiện "Refresh"
        btnRefresh.addActionListener(e -> {
            connectData.loadData(tableModel, "SELECT h.MaHoaDonKho, h.MaNguyenLieu, h.MaNhaCungCap, k.SoLuong, (k.GiaNhap * k.SoLuong) AS TongTien FROM hoadonnhap h JOIN khoNguyenLieu k ON h.MaNguyenLieu = k.MaNguyenLieu", hdnColumns);
        });
    }

    private void clearForm() {
        txtMaHoaDon.setText("");
        txtMaNguyenLieu.setText("");
        txtMaNCC.setText("");
        txtSoLuong.setText("");
        txtTongTien.setText("");
    }

    private void fillForm(int row) {
        txtMaHoaDon.setText(tableModel.getValueAt(row, 0).toString());
        txtMaNguyenLieu.setText(tableModel.getValueAt(row, 1).toString());
        txtMaNCC.setText(tableModel.getValueAt(row, 2).toString());
        txtSoLuong.setText(tableModel.getValueAt(row, 3).toString());
        txtTongTien.setText(tableModel.getValueAt(row, 4).toString());
    }
}