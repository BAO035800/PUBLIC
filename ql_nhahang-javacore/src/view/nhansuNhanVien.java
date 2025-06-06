package view;

import controller.NhanVienController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.NhanVienDAO;
import model.connectData;

public class nhansuNhanVien extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtTen, txtTuoi, txtChucVu, txtLuong, txtSoDienThoai, txtMaNhanVien;
    private JComboBox<String> cbChucVu;
    private JRadioButton radNam, radNu;
    private JButton btnLuuThem, btnHuy, btnLuuSua, btnRefresh;
    private int selectedRow = -1;

    public JComboBox<String> getCbChucVu() {
        return cbChucVu;
    }

    public void setCbChucVu(JComboBox<String> cbChucVu) {
        this.cbChucVu = cbChucVu;
    }

    public JTextField getTxtTen() {
        return txtTen;
    }

    public void setTxtTen(JTextField txtTen) {
        this.txtTen = txtTen;
    }

    public JTextField getTxtTuoi() {
        return txtTuoi;
    }

    public void setTxtTuoi(JTextField txtTuoi) {
        this.txtTuoi = txtTuoi;
    }

    public JTextField getTxtChucVu() {
        return txtChucVu;
    }

    public void setTxtChucVu(JTextField txtChucVu) {
        this.txtChucVu = txtChucVu;
    }

    public JTextField getTxtLuong() {
        return txtLuong;
    }

    public void setTxtLuong(JTextField txtLuong) {
        this.txtLuong = txtLuong;
    }

    public JTextField getTxtSoDienThoai() {
        return txtSoDienThoai;
    }

    public void setTxtSoDienThoai(JTextField txtSoDienThoai) {
        this.txtSoDienThoai = txtSoDienThoai;
    }

    public JTextField getTxtMaNhanVien() {
        return txtMaNhanVien;
    }

    public void setTxtMaNhanVien(JTextField txtMaNhanVien) {
        this.txtMaNhanVien = txtMaNhanVien;
    }

    public JRadioButton getRadNam() {
        return radNam;
    }

    public void setRadNam(JRadioButton radNam) {
        this.radNam = radNam;
    }

    public JRadioButton getRadNu() {
        return radNu;
    }

    public void setRadNu(JRadioButton radNu) {
        this.radNu = radNu;
    }

    public nhansuNhanVien() {
        String[] nvColumns = {"Mã NV", "Tên Nhân Viên", "Chức Vụ", "Lương 1 giờ", "Giới Tính", "SĐT", "Tuổi"};
        setLayout(new BorderLayout(10, 10));
        tableModel = new DefaultTableModel(nvColumns, 0);
        table = new JTable(tableModel);
        TableStyler.styleTable(table);

        // Panel chứa nút chức năng
        JPanel controlPanel = new JPanel(new GridLayout(1, 4, 10, 10)); // Cập nhật layout với 4 cột
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        btnRefresh = new JButton("Refresh"); // Đưa Refresh lên đầu cùng

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
        controlPanel.add(btnRefresh); // Thêm nút Refresh vào controlPanel
        add(controlPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu từ database
        connectData.loadData(tableModel, "SELECT * FROM nhanvien", nvColumns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin nhân viên"));
        formPanel.setBackground(Color.WHITE);

        // Các input field
        txtMaNhanVien = new JTextField();
        txtTen = new JTextField();
        txtTuoi = new JTextField();
        txtLuong = new JTextField();
        txtSoDienThoai = new JTextField();
        // Combo box chức vụ
        txtChucVu = new JTextField();
        cbChucVu = new JComboBox<>(new String[]{"Đầu bếp", "Phục vụ", "Thu ngân", "Quản lý", "Bảo vệ"});
        cbChucVu.setSelectedIndex(0);
        cbChucVu.addActionListener(e -> {
            txtChucVu.setText(cbChucVu.getSelectedItem().toString());
        });

        // Nhóm radio button cho giới tính
        radNam = new JRadioButton("Nam");
        radNu = new JRadioButton("Nữ");
        ButtonGroup group = new ButtonGroup();
        group.add(radNam);
        group.add(radNu);

        // Panel chứa radio button giới tính
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(radNam);
        genderPanel.add(radNu);

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
        formPanel.add(new JLabel("Mã nhân viên:"));
        formPanel.add(txtMaNhanVien);
        formPanel.add(new JLabel("Tên:"));
        formPanel.add(txtTen);
        formPanel.add(new JLabel("Tuổi:"));
        formPanel.add(txtTuoi);
        formPanel.add(new JLabel("Chức vụ:"));
        formPanel.add(cbChucVu);
        formPanel.add(new JLabel("Lương 1 giờ:"));
        formPanel.add(txtLuong);
        formPanel.add(new JLabel("Số điện thoại:"));
        formPanel.add(txtSoDienThoai);
        formPanel.add(new JLabel("Giới tính:"));
        formPanel.add(genderPanel);
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));
        // Thêm nút Lưu và Hủy
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
            }
            txtMaNhanVien.setText(table.getValueAt(selectedRow, 0).toString());
            txtTen.setText(table.getValueAt(selectedRow, 1).toString());
            cbChucVu.setSelectedItem(table.getValueAt(selectedRow, 2).toString());
            txtTuoi.setText(table.getValueAt(selectedRow, 6).toString());
            txtLuong.setText(table.getValueAt(selectedRow, 3).toString());
            txtSoDienThoai.setText(table.getValueAt(selectedRow, 5).toString());

            String gioiTinh = table.getValueAt(selectedRow, 4).toString();
            if (gioiTinh.equals("Nam")) {
                radNam.setSelected(true);
            } else {
                radNu.setSelected(true);
            }
        });

        // Sự kiện "Xóa"
        btnXoa.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa nhân viên này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Lấy mã nhân viên từ bảng
                String maNV = tableModel.getValueAt(selectedRow, 0).toString();
                // Gọi controller để xóa (Dùng controller có sẵn, không khởi tạo lại)
                NhanVienController controller = new NhanVienController(nhansuNhanVien.this, new NhanVienDAO());
                if (controller.xoaNhanVien(maNV)) {
                    connectData.loadData(tableModel, "SELECT * FROM nhanvien", nvColumns); // Load lại bảng nếu xóa thành công
                }
            }
            clearForm();
        });

        // Sự kiện "Lưu Thêm"
        btnLuuThem.addActionListener(e -> {
            NhanVienController controller = new NhanVienController(nhansuNhanVien.this, new NhanVienDAO());
            controller.themNhanVien();
            connectData.loadData(tableModel, "SELECT * FROM nhanvien", nvColumns);
        });

        // Sự kiện "Lưu Sửa"
        btnLuuSua.addActionListener(e -> {
            NhanVienController controller = new NhanVienController(nhansuNhanVien.this, new NhanVienDAO());
            controller.suaNhanVien();
            connectData.loadData(tableModel, "SELECT * FROM nhanvien", nvColumns);
        });

        // Sự kiện "Hủy"
        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
        });

        // Sự kiện "Refresh"
        btnRefresh.addActionListener(e -> {
            connectData.loadData(tableModel, "SELECT * FROM nhanvien", nvColumns);
        });
    }

    private void clearForm() {
        txtMaNhanVien.setText("");
        txtTen.setText("");
        txtTuoi.setText("");
        txtChucVu.setText("");
        txtLuong.setText("");
        txtSoDienThoai.setText("");
        radNam.setSelected(true);
    }
}
