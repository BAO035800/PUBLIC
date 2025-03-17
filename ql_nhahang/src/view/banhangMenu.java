package view;

import controller.MenuController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.MenuDAO;
public class banhangMenu extends JPanel implements connectData {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaMon, txtTenMon, txtGia,txtTinhTrang,txtSoLuong;
    private JComboBox<String> cbTinhTrang;
    private JButton btnLuuThem, btnHuy, btnLuuSua, btnRefresh;
    private int selectedRow = -1;

    public JTextField getTxtMaMon() {
        return txtMaMon;
    }

    public void setTxtMaMon(JTextField txtMaMon) {
        this.txtMaMon = txtMaMon;
    }

    public JTextField getTxtTenMon() {
        return txtTenMon;
    }

    public void setTxtTenMon(JTextField txtTenMon) {
        this.txtTenMon = txtTenMon;
    }

    public JTextField getTxtGia() {
        return txtGia;
    }

    public void setTxtGia(JTextField txtGia) {
        this.txtGia = txtGia;
    }

    public JTextField getTxtTinhTrang() {
        return txtTinhTrang;
    }

    public void setTxtTinhTrang(JTextField txtTinhTrang) {
        this.txtTinhTrang = txtTinhTrang;
    }

    public JTextField getTxtSoLuong() {
        return txtSoLuong;
    }

    public void setTxtSoLuong(JTextField txtSoLuong) {
        this.txtSoLuong = txtSoLuong;
    }

    public JComboBox<String> getCbTinhTrang() {
        return cbTinhTrang;
    }

    public void setCbTinhTrang(JComboBox<String> cbTinhTrang) {
        this.cbTinhTrang = cbTinhTrang;
    }

    public banhangMenu() {
        String[] menuColumns = {"Mã món", "Tên món", "Giá","Tình trạng", "Số lượng",};
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
        loadData(tableModel, "SELECT * FROM menu", menuColumns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin món ăn"));
        formPanel.setBackground(Color.WHITE);

        // Các input field
        txtMaMon = new JTextField();
        txtTenMon = new JTextField();
        txtGia = new JTextField();
        txtTinhTrang = new JTextField();
        txtSoLuong = new JTextField();
        cbTinhTrang = new JComboBox<>(new String[]{"Còn", "Hết"});
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
        formPanel.add(new JLabel("Mã món:"));
        formPanel.add(txtMaMon);
        formPanel.add(new JLabel("Tên món:"));
        formPanel.add(txtTenMon);
        formPanel.add(new JLabel("Giá:"));
        formPanel.add(txtGia);
        formPanel.add(new JLabel("Tình trạng:"));
        formPanel.add(cbTinhTrang);
        formPanel.add(new JLabel("Số lượng:"));
        formPanel.add(txtSoLuong);

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
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
            } else {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa món này?");
                if (confirm == JOptionPane.YES_OPTION) {
                    String maMon = table.getValueAt(selectedRow, 0).toString();
                    MenuController controller = new MenuController(banhangMenu.this, new MenuDAO());
                    controller.xoaMenu();
                    loadData(tableModel, "SELECT * FROM menu", menuColumns);
                }
            }
            clearForm();
        });

        // Sự kiện "Lưu Thêm"
        btnLuuThem.addActionListener(e -> {
            MenuController controller = new MenuController(banhangMenu.this, new MenuDAO());
            controller.themMenu();
            loadData(tableModel, "SELECT * FROM menu", menuColumns);
        });

        // Sự kiện "Lưu Sửa"
        btnLuuSua.addActionListener(e -> {
            MenuController controller = new MenuController(banhangMenu.this, new MenuDAO());
            controller.suaMenu();
            loadData(tableModel, "SELECT * FROM menu", menuColumns);
        });

        // Sự kiện "Hủy"
        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });

        // Sự kiện "Refresh"
        btnRefresh.addActionListener(e -> {
            loadData(tableModel, "SELECT * FROM menu", menuColumns);
        });
    }

    private void clearForm() {
        txtMaMon.setText("");
        txtTenMon.setText("");
        txtGia.setText("");
        txtTinhTrang.setText("");
        txtSoLuong.setText("");
        cbTinhTrang.setSelectedIndex(0);
    }

    private void fillForm(int row) {
        txtMaMon.setText(table.getValueAt(row, 0).toString());
        txtTenMon.setText(table.getValueAt(row, 1).toString());
        txtGia.setText(table.getValueAt(row, 2).toString());
        txtTinhTrang.setText(table.getValueAt(row, 3).toString());
        txtSoLuong.setText(table.getValueAt(row, 4).toString());
    }
}
