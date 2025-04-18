package view;

import controller.CheBienMonController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.CheBienMonDAO;
import model.connectData;

public class bepCheBienMon extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaCheBien, txtMaMon, TxtHoaDonBanHang, txtTrangThai;
    private JButton btnLuuThem, btnHuy, btnLuuSua, btnRefresh;
    private int selectedRow = -1;

    public JTextField getTxtMaCheBien() { return txtMaCheBien; }
    public JTextField getTxtMaMon() { return txtMaMon; }
    public JTextField getTxtMaHoaDon() { return TxtHoaDonBanHang; }
    public JTextField getTxtTrangThai() { return txtTrangThai; }

    public bepCheBienMon() {
        String[] columns = {"Mã chế biến", "Mã món", "Mã hóa đơn", "Trạng thái"};
        setLayout(new BorderLayout(10, 10));
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        TableStyler.styleTable(table);

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

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        connectData.loadData(tableModel, "SELECT * FROM chebienmon", columns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin chế biến"));
        formPanel.setBackground(Color.WHITE);

        txtMaCheBien = new JTextField();
        txtMaMon = new JTextField();
        TxtHoaDonBanHang = new JTextField();
        txtTrangThai = new JTextField();

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

        // Thêm vào formPanel
        formPanel.add(new JLabel("Mã chế biến"));
        formPanel.add(txtMaCheBien);
        formPanel.add(new JLabel("Mã món"));
        formPanel.add(txtMaMon);
        formPanel.add(new JLabel("Mã hóa đơn"));
        formPanel.add(TxtHoaDonBanHang);
        formPanel.add(new JLabel("Trạng thái"));
        formPanel.add(txtTrangThai);
        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(false);

        // Sự kiện Thêm
        // Sự kiện "Thêm"
        btnThem.addActionListener(e -> {
            selectedRow = -1;  // Đặt lại selectedRow khi bấm "Thêm"
            clearForm();  // Xóa thông tin trong form
            formPanel.setVisible(true);  // Hiển thị form nhập liệu
        });

        // Sự kiện "Sửa"
        btnSua.addActionListener(e -> {
            selectedRow = table.getSelectedRow();  // Lấy dòng được chọn từ bảng
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!");
            } else {
                formPanel.setVisible(true);  // Hiển thị form nhập liệu khi chọn dòng
                fillForm(selectedRow);  // Điền thông tin của dòng đã chọn vào form
            }
        });

        // Sự kiện "Xóa"
        btnXoa.addActionListener(e -> {
            selectedRow = table.getSelectedRow();  
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
            } else {
                    String maCheBien = table.getValueAt(selectedRow, 0).toString();
                    // Tạo đối tượng controller và gọi phương thức xóa chế biến
                    CheBienMonController controller = new CheBienMonController(this, new CheBienMonDAO());
                    controller.xoaCheBienMon(maCheBien);
                    // Làm mới bảng sau khi xóa
                    connectData.loadData(tableModel, "SELECT * FROM chebienmon", columns);
            }
            clearForm();
        });

        // Sự kiện "Lưu Thêm"
        btnLuuThem.addActionListener(e -> {
            String maCheBien = txtMaCheBien.getText();  // Lấy mã chế biến từ form
            // Tạo đối tượng controller và gọi phương thức thêm chế biến
            CheBienMonController controller = new CheBienMonController(this, new CheBienMonDAO());
            controller.themCheBienMon();
            // Làm mới bảng sau khi thêm
            connectData.loadData(tableModel, "SELECT * FROM chebienmon", columns);
            clearForm();  // Reset form sau khi thêm
        });

        // Sự kiện "Lưu Sửa"
        btnLuuSua.addActionListener(e -> {
            String maCheBien = txtMaCheBien.getText();  // Lấy mã chế biến từ form
            // Tạo đối tượng controller và gọi phương thức sửa chế biến
            CheBienMonController controller = new CheBienMonController(this, new CheBienMonDAO());
            controller.suaCheBienMon();

            // Làm mới bảng sau khi sửa
            connectData.loadData(tableModel, "SELECT * FROM chebienmon", columns);
            clearForm();  // Reset form sau khi sửa
        });

        // Sự kiện "Hủy"
        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);  // Ẩn form nhập liệu
            clearForm();  // Xóa thông tin trong form
        });

        // Sự kiện "Refresh"
        btnRefresh.addActionListener(e -> {
            connectData.loadData(tableModel, "SELECT * FROM chebienmon", columns);  // Làm mới bảng
        });
    }

    private void clearForm() {
        txtMaCheBien.setText("");
        txtMaMon.setText("");
        TxtHoaDonBanHang.setText("");
        txtTrangThai.setText("");
    }

    private void fillForm(int row) {
        txtMaCheBien.setText(tableModel.getValueAt(row, 0).toString());
        txtMaMon.setText(tableModel.getValueAt(row, 1).toString());
        TxtHoaDonBanHang.setText(tableModel.getValueAt(row, 2).toString());
        txtTrangThai.setText(tableModel.getValueAt(row, 3).toString());
    }
}
