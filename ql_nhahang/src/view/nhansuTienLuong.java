package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class nhansuTienLuong extends JPanel implements connectData {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaNV, txtThang, txtLuongCoBan, txtThuong, txtPhuCap;
    private JButton btnLuu, btnHuy;
    private int selectedRow = -1;

    public nhansuTienLuong() {
        setLayout(new BorderLayout(10, 10));

        // Panel chứa nút chức năng
        JPanel controlPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        controlPanel.add(btnThem);
        controlPanel.add(btnSua);
        controlPanel.add(btnXoa);
        add(controlPanel, BorderLayout.NORTH);

        // Bảng dữ liệu tiền lương
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu từ database
        loadData(tableModel, "SELECT * FROM tienluong");

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin lương"));
        txtMaNV = new JTextField();
        txtThang = new JTextField();
        txtLuongCoBan = new JTextField();
        txtThuong = new JTextField();
        txtPhuCap = new JTextField();
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");

        formPanel.add(new JLabel("Mã nhân viên:"));
        formPanel.add(txtMaNV);
        formPanel.add(new JLabel("Tháng:"));
        formPanel.add(txtThang);
        formPanel.add(new JLabel("Lương cơ bản:"));
        formPanel.add(txtLuongCoBan);
        formPanel.add(new JLabel("Thưởng:"));
        formPanel.add(txtThuong);
        formPanel.add(new JLabel("Phụ cấp:"));
        formPanel.add(txtPhuCap);
        formPanel.add(btnLuu);
        formPanel.add(btnHuy);
        formPanel.setVisible(false);
        add(formPanel, BorderLayout.SOUTH);

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
                JOptionPane.showMessageDialog(this, "Vui lòng chọn mục cần sửa!");
            } else {
                formPanel.setVisible(true);
            }
        });

        // Sự kiện "Xóa"
        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn mục cần xóa!");
            }
        });

        // Sự kiện "Lưu"
        btnLuu.addActionListener(e -> {});
        
        // Sự kiện "Hủy"
        btnHuy.addActionListener(e -> formPanel.setVisible(false));
    }

    private void clearForm() {
        txtMaNV.setText("");
        txtThang.setText("");
        txtLuongCoBan.setText("");
        txtThuong.setText("");
        txtPhuCap.setText("");
    }
}
