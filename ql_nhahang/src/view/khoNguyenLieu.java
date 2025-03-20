package view;

import controller.NguyenLieuController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.KhoNguyenLieuDAO;
public class khoNguyenLieu extends JPanel implements connectData {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaNguyenLieu, txtTenNguyenLieu, txtMaNCC, txtSoLuong;
    private JButton btnLuuThem, btnHuy, btnLuuSua, btnRefresh;
    private int selectedRow = -1;
    

    public JTextField getTxtMaNguyenLieu() {
        return txtMaNguyenLieu;
    }

    public void setTxtMaNguyenLieu(JTextField txtMaNguyenLieu) {
        this.txtMaNguyenLieu = txtMaNguyenLieu;
    }

    public JTextField getTxtTenNguyenLieu() {
        return txtTenNguyenLieu;
    }

    public void setTxtTenNguyenLieu(JTextField txtTenNguyenLieu) {
        this.txtTenNguyenLieu = txtTenNguyenLieu;
    }

    public JTextField getTxtMaNCC() {
        return txtMaNCC;
    }

    public void setTxtMaNCC(JTextField txtMaNCC) {
        this.txtMaNCC = txtMaNCC;
    }

    public JTextField getTxtSoLuong() {
        return txtSoLuong;
    }

    public void setTxtSoLuong(JTextField txtSoLuong) {
        this.txtSoLuong = txtSoLuong;
    }

    public khoNguyenLieu() {
        String[] nlColumns = {"Mã nguyên liệu", "Tên nguyên liệu", "Mã nhà cung cấp","Số lượng",};
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
        loadData(tableModel, "SELECT * FROM khonguyenlieu", nlColumns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin kho nguyên liệu"));
        formPanel.setBackground(Color.WHITE);

        // Các input field
        txtMaNguyenLieu = new JTextField();
        txtTenNguyenLieu = new JTextField();
        txtMaNCC = new JTextField();
        txtSoLuong = new JTextField();
        // Tạo các button chức năng
        btnLuuThem = new JButton("Thêm");
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

        // Thêm các thành phần vào form
        formPanel.add(new JLabel("Mã nguyên liệu"));
        formPanel.add(txtMaNguyenLieu);
        formPanel.add(new JLabel("Tên nguyên liệu"));
        formPanel.add(txtTenNguyenLieu);
        formPanel.add(new JLabel("Mã nhà cung cấp"));
        formPanel.add(txtMaNCC);
        formPanel.add(new JLabel("Số lượng"));
        formPanel.add(txtSoLuong);
        formPanel.add(new JLabel("")); // Dòng trống
        formPanel.add(new JLabel("")); // Dòng trống

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
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nguyên liệu cần xóa!");
                return;
            }
            String MaNguyenLieu = tableModel.getValueAt(selectedRow, 0).toString();
            NguyenLieuController controller = new NguyenLieuController(khoNguyenLieu.this, new KhoNguyenLieuDAO());
            controller.xoaNguyenLieu(MaNguyenLieu); 
            loadData(tableModel, "SELECT * FROM khonguyenlieu", nlColumns);
            
        });
        
        

        // Sự kiện "Lưu Thêm"
        btnLuuThem.addActionListener(e -> {
            NguyenLieuController controller = new NguyenLieuController(khoNguyenLieu.this, new KhoNguyenLieuDAO());
            controller.themNguyenLieu();
            loadData(tableModel, "SELECT * FROM khonguyenlieu", nlColumns);
        });

        // Sự kiện "Lưu Sửa"
        btnLuuSua.addActionListener(e -> {
            NguyenLieuController controller = new NguyenLieuController(khoNguyenLieu.this, new KhoNguyenLieuDAO());
            controller.suaNguyenLieu();
            loadData(tableModel, "SELECT * FROM khonguyenlieu", nlColumns);
        });

        // Sự kiện "Hủy"
        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });

        // Sự kiện "Refresh"
        btnRefresh.addActionListener(e -> {
            loadData(tableModel, "SELECT * FROM khonguyenlieu", nlColumns);
        });
    }

    private void clearForm() {
        txtMaNguyenLieu.setText("");
        txtTenNguyenLieu.setText("");
        txtMaNCC.setText("");
        txtSoLuong.setText("");
    }

    private void fillForm(int row) {
        txtMaNguyenLieu.setText(tableModel.getValueAt(row, 0).toString());
        txtTenNguyenLieu.setText(tableModel.getValueAt(row, 1).toString());
        txtMaNCC.setText(tableModel.getValueAt(row, 2).toString());
        txtSoLuong.setText(tableModel.getValueAt(row, 3).toString());
    }
}