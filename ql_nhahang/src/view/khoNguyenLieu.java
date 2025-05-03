package view;

import controller.NguyenLieuController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.KhoNguyenLieuDAO;
import model.connectData;

public class khoNguyenLieu extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaNguyenLieu, txtTenNguyenLieu, txtMaNCC, txtSoLuong, txtGiaNhap;
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

    public JTextField getTxtGiaNhap() {
        return txtGiaNhap;
    }

    public void setTxtGiaNhap(JTextField txtGiaNhap) {
        this.txtGiaNhap = txtGiaNhap;
    }

    public khoNguyenLieu() {
        // Thêm cột "Tổng tiền nhập" vào mảng cột
        String[] nlColumns = {"Mã nguyên liệu", "Tên nguyên liệu", "Mã nhà cung cấp", "Giá nhập", "Số lượng", "Tổng tiền nhập"};
        setLayout(new BorderLayout(10, 10));
        tableModel = new DefaultTableModel(nlColumns, 0);
        table = new JTable(tableModel);
        TableStyler.styleTable(table);

        // Panel chứa nút chức năng
        JPanel controlPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        JButton btnRefresh = new JButton("Refresh");

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

        // Tải dữ liệu ban đầu với cột Tổng tiền nhập
        loadDataWithTotal();

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin kho nguyên liệu"));
        formPanel.setBackground(Color.WHITE);

        txtMaNguyenLieu = new JTextField();
        txtTenNguyenLieu = new JTextField();
        txtMaNCC = new JTextField();
        txtSoLuong = new JTextField();
        txtGiaNhap = new JTextField();

        btnLuuThem = new JButton("Thêm");
        btnLuuSua = new JButton("Sửa");
        btnHuy = new JButton("Hủy");

        btnLuuThem.setBackground(new Color(76, 175, 80)); 
        btnLuuThem.setForeground(Color.WHITE);

        btnLuuSua.setBackground(new Color(255, 193, 7)); 
        btnLuuSua.setForeground(Color.BLACK);

        btnHuy.setBackground(new Color(108, 117, 125));
        btnHuy.setForeground(Color.WHITE);

        // Thêm các thành phần vào formPanel
        formPanel.add(new JLabel("Mã nguyên liệu"));
        formPanel.add(txtMaNguyenLieu);
        formPanel.add(new JLabel("Tên nguyên liệu"));
        formPanel.add(txtTenNguyenLieu);
        formPanel.add(new JLabel("Mã nhà cung cấp"));
        formPanel.add(txtMaNCC);
        formPanel.add(new JLabel("Giá nhập"));
        formPanel.add(txtGiaNhap);
        formPanel.add(new JLabel("Số lượng"));
        formPanel.add(txtSoLuong);
        
        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);

        add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(false);

        // Sự kiện Thêm
        btnThem.addActionListener(e -> {
            selectedRow = -1;
            clearForm();
            formPanel.setVisible(true);
        });

        // Sự kiện Sửa
        btnSua.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!");
            } else {
                formPanel.setVisible(true);
                fillForm(selectedRow);
            }
        });

        // Sự kiện Xóa
        btnXoa.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nguyên liệu cần xóa!");
                return;
            }
            String maNguyenLieu = tableModel.getValueAt(selectedRow, 0).toString();
            NguyenLieuController controller = new NguyenLieuController(this, new KhoNguyenLieuDAO());
            controller.xoaNguyenLieu(maNguyenLieu);
            loadDataWithTotal();
        });

        // Sự kiện Lưu Thêm
        btnLuuThem.addActionListener(e -> {
            NguyenLieuController controller = new NguyenLieuController(this, new KhoNguyenLieuDAO());
            controller.themNguyenLieu();
            loadDataWithTotal();
        });

        // Sự kiện Lưu Sửa
        btnLuuSua.addActionListener(e -> {
            NguyenLieuController controller = new NguyenLieuController(this, new KhoNguyenLieuDAO());
            controller.suaNguyenLieu();
            loadDataWithTotal();
        });

        // Sự kiện Hủy
        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });

        // Sự kiện Refresh
        btnRefresh.addActionListener(e -> {
            loadDataWithTotal();
        });
    }

    private void clearForm() {
        txtMaNguyenLieu.setText("");
        txtTenNguyenLieu.setText("");
        txtMaNCC.setText("");
        txtSoLuong.setText("");
        txtGiaNhap.setText("");
    }

    private void fillForm(int row) {
        txtMaNguyenLieu.setText(tableModel.getValueAt(row, 0).toString());
        txtTenNguyenLieu.setText(tableModel.getValueAt(row, 1).toString());
        txtMaNCC.setText(tableModel.getValueAt(row, 2).toString());
        txtGiaNhap.setText(tableModel.getValueAt(row, 3).toString());
        txtSoLuong.setText(tableModel.getValueAt(row, 4).toString());
    }

    // Phương thức tải dữ liệu và tính Tổng tiền nhập
    private void loadDataWithTotal() {
        // Định nghĩa lại các cột ban đầu mà không có Tổng tiền nhập để load từ cơ sở dữ liệu
        String[] dbColumns = {"Mã nguyên liệu", "Tên nguyên liệu", "Mã nhà cung cấp", "Giá nhập", "Số lượng"};
        DefaultTableModel tempModel = new DefaultTableModel(dbColumns, 0);
        
        // Tải dữ liệu từ cơ sở dữ liệu vào tempModel
        connectData.loadData(tempModel, "SELECT * FROM khonguyenlieu", dbColumns);

        // Xóa dữ liệu cũ trong tableModel
        tableModel.setRowCount(0);

        // Duyệt qua các dòng trong tempModel để tính Tổng tiền nhập và thêm vào tableModel
        for (int i = 0; i < tempModel.getRowCount(); i++) {
            String maNguyenLieu = tempModel.getValueAt(i, 0).toString();
            String tenNguyenLieu = tempModel.getValueAt(i, 1).toString();
            String maNCC = tempModel.getValueAt(i, 2).toString();
            double giaNhap = Double.parseDouble(tempModel.getValueAt(i, 3).toString());
            int soLuong = Integer.parseInt(tempModel.getValueAt(i, 4).toString());
            
            // Tính tổng tiền nhập
            double tongTienNhap = giaNhap * soLuong;

            // Thêm dòng mới vào tableModel với cột Tổng tiền nhập
            Object[] row = {maNguyenLieu, tenNguyenLieu, maNCC, giaNhap, soLuong, tongTienNhap};
            tableModel.addRow(row);
        }
    }
}