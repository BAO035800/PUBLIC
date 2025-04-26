package view;

import controller.MenuController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.MenuDAO;
import model.connectData;

public class banhangMenu extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaMon, txtTenMon, txtGia, txtTinhTrang, txtSoLuong;
    private JComboBox<String> cbTinhTrang;
    private JButton btnLuuThem, btnHuy, btnLuuSua;
    private int selectedRow = -1;

    public JTextField getTxtMaMon() { return txtMaMon; }
    public JTextField getTxtTenMon() { return txtTenMon; }
    public JTextField getTxtGia() { return txtGia; }
    public JTextField getTxtTinhTrang() { return txtTinhTrang; }
    public JTextField getTxtSoLuong() { return txtSoLuong; }
    public JComboBox<String> getCbTinhTrang() { return cbTinhTrang; }

    public banhangMenu() {
        String[] menuColumns = {"Mã món", "Tên món", "Giá", "Tình trạng", "Số lượng"};
        setLayout(new BorderLayout(10, 10));

        // BẢNG HIỂN THỊ
        tableModel = new DefaultTableModel(menuColumns, 0);
        table = new JTable(tableModel);
        TableStyler.styleTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // NÚT CHỨC NĂNG TRÊN ĐẦU
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

        // FORM NHẬP LIỆU ẨN
        formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin menu"));
        formPanel.setBackground(Color.WHITE);

        txtMaMon = new JTextField();
        txtTenMon = new JTextField();
        txtGia = new JTextField();
        txtTinhTrang = new JTextField();
        txtSoLuong = new JTextField();
        cbTinhTrang = new JComboBox<>(new String[]{"Còn", "Hết"});

        btnLuuThem = new JButton("Thêm");
        btnLuuSua = new JButton("Sửa");
        btnHuy = new JButton("Hủy");

        btnLuuThem.setBackground(new Color(76, 175, 80));
        btnLuuThem.setForeground(Color.WHITE);
        btnLuuSua.setBackground(new Color(255, 193, 7));
        btnLuuSua.setForeground(Color.BLACK);
        btnHuy.setBackground(new Color(108, 117, 125));
        btnHuy.setForeground(Color.WHITE);

        formPanel.add(new JLabel("Mã món:")); formPanel.add(txtMaMon);
        formPanel.add(new JLabel("Tên món:")); formPanel.add(txtTenMon);
        formPanel.add(new JLabel("Giá:")); formPanel.add(txtGia);
        formPanel.add(new JLabel("Tình trạng:")); formPanel.add(cbTinhTrang);
        formPanel.add(new JLabel("Số lượng:")); formPanel.add(txtSoLuong);

        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);
        formPanel.add(new JLabel()); // ô trống

        add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(false);

        // LOAD DỮ LIỆU TỪ DATABASE
        connectData.loadData(tableModel, "SELECT * FROM menu", menuColumns);

        // ======= SỰ KIỆN NÚT =======

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
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
            } else {
                String maMon = table.getValueAt(selectedRow, 0).toString();
                MenuController controller = new MenuController(this, new MenuDAO());
                controller.xoaMenu(maMon);
                connectData.loadData(tableModel, "SELECT * FROM menu", menuColumns);
                clearForm();
            }
        });

        btnRefresh.addActionListener(e -> {
            connectData.loadData(tableModel, "SELECT * FROM menu", menuColumns);
        });

        btnLuuThem.addActionListener(e -> {
            MenuController controller = new MenuController(this, new MenuDAO());
            controller.themMenu();
            connectData.loadData(tableModel, "SELECT * FROM menu", menuColumns);
        });

        btnLuuSua.addActionListener(e -> {
            MenuController controller = new MenuController(this, new MenuDAO());
            controller.suaMenu();
            connectData.loadData(tableModel, "SELECT * FROM menu", menuColumns);
        });

        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
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
