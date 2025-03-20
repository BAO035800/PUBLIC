package view;

import controller.NCCController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.NhaCungCapDAO;
public class khoNhaCungCap extends JPanel implements connectData {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaNCC, txtTenNCC, txtLienHe;
    private JButton btnLuuThem, btnHuy, btnLuuSua, btnRefresh;
    private int selectedRow = -1;
    

    public JTextField getTxtMaNCC() {
        return txtMaNCC;
    }

    public void setTxtMaNCC(JTextField txtMaNCC) {
        this.txtMaNCC = txtMaNCC;
    }

    public JTextField getTxtTenNCC() {
        return txtTenNCC;
    }

    public void setTxtTenNCC(JTextField txtTenNCC) {
        this.txtTenNCC = txtTenNCC;
    }

    public JTextField getTxtLienHe() {
        return txtLienHe;
    }

    public void setTxtLienHe(JTextField txtLienHe) {
        this.txtLienHe = txtLienHe;
    }

    public khoNhaCungCap() {
        String[] nccColumns = {"Mã nhà cung cấp","Tên nhà cung cấp","Liên hệ"};
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
        loadData(tableModel, "SELECT * FROM nhacungcap", nccColumns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin nhà cung cấp"));
        formPanel.setBackground(Color.WHITE);

        // Các input field
        txtMaNCC = new JTextField();
        txtTenNCC = new JTextField();
        txtLienHe = new JTextField();
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
        formPanel.add(new JLabel("Mã nhà cung cấp:"));
        formPanel.add(txtMaNCC);
        formPanel.add(new JLabel("Tên nhà cung cấp:"));
        formPanel.add(txtTenNCC);
        formPanel.add(new JLabel("Liên hệ:"));
        formPanel.add(txtLienHe);

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
                JOptionPane.showMessageDialog(khoNhaCungCap.this, "Vui lòng chọn dòng cần sửa!");
            } else {
                formPanel.setVisible(true);
                fillForm(selectedRow);
            }
        });

        // Sự kiện "Xóa"
        btnXoa.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
            } else {
                String maNCC=tableModel.getValueAt(selectedRow, 0).toString();
                NCCController controller = new NCCController(khoNhaCungCap.this, new NhaCungCapDAO());
                controller.xoaNhaCungCap(maNCC);
                clearForm();
            }
            loadData(tableModel, "SELECT * FROM nhacungcap", nccColumns);
        });

        // Sự kiện "Lưu Thêm"
        btnLuuThem.addActionListener(e -> {
            NCCController controller = new NCCController(khoNhaCungCap.this, new NhaCungCapDAO());
            controller.themNhaCungCap();
            clearForm();
            loadData(tableModel, "SELECT * FROM nhacungcap", nccColumns);
        });

        // Sự kiện "Lưu Sửa"
        btnLuuSua.addActionListener(e -> {
            NCCController controller = new NCCController(khoNhaCungCap.this, new NhaCungCapDAO());
            controller.suaNhaCungCap();
            clearForm();
            loadData(tableModel, "SELECT * FROM nhacungcap", nccColumns);
        });

        // Sự kiện "Hủy"
        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });

        // Sự kiện "Refresh"
        btnRefresh.addActionListener(e -> {
            loadData(tableModel, "SELECT * FROM nhacungcap", nccColumns);
        });
    }

    private void clearForm() {
        txtMaNCC.setText("");
        txtTenNCC.setText("");
        txtLienHe.setText("");
    }

    private void fillForm(int row) {
        txtMaNCC.setText(table.getValueAt(row, 0).toString());
        txtTenNCC.setText(table.getValueAt(row, 1).toString());
        txtLienHe.setText(table.getValueAt(row, 2).toString());
    }
}