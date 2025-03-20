package view;
import controller.TienLuongController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.TienLuongDAO;

public class nhansuTienLuong extends JPanel implements connectData {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaLuong, txtMaNV, txtTenNV, txtTongTienLuong, txtTinhTrangLuong, txtNgayCong, txtSoTienThanhToan;
    private JComboBox<String> cbTinhTrangLuong;
    private JButton btnLuuThem, btnHuy,btnLuuSua,btnRefresh;
    private int selectedRow = -1;
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

    public JTextField getTxtTongTienLuong() {
        return txtTongTienLuong;
    }

    public void setTxtTongTienLuong(JTextField txtTongTienLuong) {
        this.txtTongTienLuong = txtTongTienLuong;
    }

    public JTextField getTxtTinhTrangLuong() {
        return txtTinhTrangLuong;
    }

    public void setTxtTinhTrangLuong(JTextField txtTinhTrangLuong) {
        this.txtTinhTrangLuong = txtTinhTrangLuong;
    }

    public JTextField getTxtNgayCong() {
        return txtNgayCong;
    }

    public void setTxtNgayCong(JTextField txtNgayCong) {
        this.txtNgayCong = txtNgayCong;
    }

    public JTextField getTxtSoTienThanhToan() {
        return txtSoTienThanhToan;
    }

    public void setTxtSoTienThanhToan(JTextField txtSoTienThanhToan) {
        this.txtSoTienThanhToan = txtSoTienThanhToan;
    }

    public JComboBox<String> getCbTinhTrangLuong() {
        return cbTinhTrangLuong;
    }

    public void setCbTinhTrangLuong(JComboBox<String> cbTinhTrangLuong) {
        this.cbTinhTrangLuong = cbTinhTrangLuong;
    }

    public nhansuTienLuong() {
        String[] nvColumns = {"Mã lương", "Mã nhân viên", "Tên nhân viên", "Tổng tiền", "Tình trạng", "Ngày công", "Số tiền đã thanh toán"};
        tableModel = new DefaultTableModel(nvColumns, 0);
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

        // Bảng dữ liệu tiền lương
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu từ database
        loadData(tableModel, "SELECT * FROM tienluong",nvColumns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin lương"));
        formPanel.setBackground(Color.WHITE);
        // Tạo các control nhập liệu
        txtMaLuong = new JTextField();
        txtMaNV = new JTextField();
        txtTenNV = new JTextField();
        txtTongTienLuong = new JTextField();
        txtNgayCong = new JTextField();
        txtSoTienThanhToan = new JTextField();
        //Combo box tình trạng lương
        txtTinhTrangLuong = new JTextField();
        cbTinhTrangLuong = new JComboBox<>(new String[]{"Đã trả", "Chưa trả"});
        cbTinhTrangLuong.setSelectedIndex(0);
        cbTinhTrangLuong.addActionListener(e -> {
            txtTinhTrangLuong.setText(cbTinhTrangLuong.getSelectedItem().toString());
        });
        // Tạo các button chức năng
        btnLuuThem = new JButton("Lưu");
        btnLuuSua = new JButton("Sửa");
        btnHuy = new JButton("Hủy");
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(new Color(23, 162, 184));
        btnRefresh.setForeground(Color.WHITE);
        btnLuuThem.setBackground(new Color(40, 167, 69));
        btnLuuThem.setForeground(Color.WHITE);
        btnLuuSua.setBackground(new Color(23, 162, 184));
        btnLuuSua.setForeground(Color.WHITE);
        btnHuy.setBackground(new Color(108, 117, 125));
        btnHuy.setForeground(Color.WHITE);
        // Thêm các control vào form
        formPanel.add(new JLabel("Mã lương:"));
        formPanel.add(txtMaLuong);
        formPanel.add(new JLabel("Mã nhân viên:"));
        formPanel.add(txtMaNV);
        formPanel.add(new JLabel("Tên nhân viên:"));
        formPanel.add(txtTenNV);
        formPanel.add(new JLabel("Tổng tiền lương:"));
        formPanel.add(txtTongTienLuong);
        formPanel.add(new JLabel("Tình trạng lương:"));
        formPanel.add(cbTinhTrangLuong);
        formPanel.add(new JLabel("Ngày công:"));
        formPanel.add(txtNgayCong);
        formPanel.add(new JLabel("Số tiền đã thanh toán:"));
        formPanel.add(txtSoTienThanhToan);
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);
        formPanel.add(btnHuy);
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
            }
            fillForm(selectedRow);
        });

        // Sự kiện "Xóa"
        btnXoa.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn lương cần xóa!");
                return;
            }
        
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa lương này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Lấy mã lương từ bản
                String maLuong = table.getValueAt(selectedRow, 0).toString();
                // Gọi controller để xóa (Dùng controller có sẵn, không khởi tạo lại)
                TienLuongController controller = new TienLuongController(this, new TienLuongDAO());
                if (controller.xoaTienLuong(maLuong)) {
                    loadData(tableModel, "SELECT * FROM tienluong", nvColumns); // Load lại bảng nếu xóa thành công
                }
            }
            clearForm();
        });

        // Sự kiện "Lưu Thêm"
        btnLuuThem.addActionListener(e -> {
            TienLuongController controller = new TienLuongController(this, new TienLuongDAO());
            controller.themTienLuong();
            loadData(tableModel, "SELECT * FROM tienluong", nvColumns); 
        });
        // Sự kiện "Lưu Sửa"
        btnLuuSua.addActionListener(e -> {
            TienLuongController controller = new TienLuongController(this, new TienLuongDAO());
            controller.suaTienLuong();
            loadData(tableModel, "SELECT * FROM tienluong", nvColumns);
        });
        
        // Sự kiện "Hủy"
        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });
        // Sự kiện "Refresh"
        btnRefresh.addActionListener(e -> {
            loadData(tableModel, "SELECT * FROM tienluong", nvColumns);
        });
    }

    private void clearForm() {
        txtMaLuong.setText("");
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtTongTienLuong.setText("");
        txtTinhTrangLuong.setText("");
        txtNgayCong.setText("");
        txtSoTienThanhToan.setText("");
    }

    private void fillForm(int row) {
        txtMaLuong.setText(tableModel.getValueAt(row, 0).toString());
        txtMaNV.setText(tableModel.getValueAt(row, 1).toString());
        txtTenNV.setText(tableModel.getValueAt(row, 2).toString());
        txtTongTienLuong.setText(tableModel.getValueAt(row, 3).toString());
        txtTinhTrangLuong.setText(tableModel.getValueAt(row, 4).toString());
        txtNgayCong.setText(tableModel.getValueAt(row, 5).toString());
        txtSoTienThanhToan.setText(tableModel.getValueAt(row, 6).toString());
    }
}
