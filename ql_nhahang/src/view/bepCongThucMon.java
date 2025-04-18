package view;

import controller.CongThucController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.CongThucMonAnDAO;
import model.connectData;

public class bepCongThucMon extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtMaMon, txtMaNguyenLieu, txtSoLuong;
    private JButton btnLuuThem, btnLuuSua, btnHuy, btnRefresh;
    private int selectedRow = -1;

    public JTextField getTxtMaMon() { return txtMaMon; }
    public JTextField getTxtMaNguyenLieu() { return txtMaNguyenLieu; }
    public JTextField getTxtSoLuong() { return txtSoLuong; }

    public bepCongThucMon() {
        setLayout(new BorderLayout(10, 10));
        String[] columns = {"Mã món", "Mã nguyên liệu", "Số lượng"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        TableStyler.styleTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel nút chức năng
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

        connectData.loadData(tableModel, "SELECT * FROM congthucmonan", columns);

        // Panel form nhập liệu
        formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin công thức"));
        formPanel.setBackground(Color.WHITE);

        txtMaMon = new JTextField();
        txtMaNguyenLieu = new JTextField();
        txtSoLuong = new JTextField();

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

        formPanel.add(new JLabel("Mã món"));
        formPanel.add(txtMaMon);
        formPanel.add(new JLabel("Mã nguyên liệu"));
        formPanel.add(txtMaNguyenLieu);
        formPanel.add(new JLabel("Số lượng"));
        formPanel.add(txtSoLuong);
        formPanel.add(btnHuy);
        formPanel.add(btnLuuThem);
        formPanel.add(btnLuuSua);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.SOUTH);
        formPanel.setVisible(false);

        // Sự kiện
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
                fillForm(selectedRow);
                formPanel.setVisible(true);
            }
        });

        btnXoa.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!");
            } else {
                String maMon = table.getValueAt(selectedRow, 0).toString();
                String maNguyenLieu = table.getValueAt(selectedRow, 1).toString();
                CongThucController controller = new CongThucController(this, new CongThucMonAnDAO());
                controller.xoaCongThuc(maMon, maNguyenLieu);
                connectData.loadData(tableModel, "SELECT * FROM congthucmonan", columns);
                clearForm();
            }
        });

        btnLuuThem.addActionListener(e -> {
            CongThucController controller = new CongThucController(this, new CongThucMonAnDAO());
            controller.themCongThuc();
            connectData.loadData(tableModel, "SELECT * FROM congthucmonan", columns);
            clearForm();
        });

        btnLuuSua.addActionListener(e -> {
            CongThucController controller = new CongThucController(this, new CongThucMonAnDAO());
            controller.suaCongThuc();
            connectData.loadData(tableModel, "SELECT * FROM congthucmonan", columns);
            clearForm();
        });

        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
            clearForm();
        });

        btnRefresh.addActionListener(e -> {
            connectData.loadData(tableModel, "SELECT * FROM congthucmonan", columns);
        });
    }

    private void clearForm() {
        txtMaMon.setText("");
        txtMaNguyenLieu.setText("");
        txtSoLuong.setText("");
    }

    private void fillForm(int row) {
        txtMaMon.setText(tableModel.getValueAt(row, 0).toString());
        txtMaNguyenLieu.setText(tableModel.getValueAt(row, 1).toString());
        txtSoLuong.setText(tableModel.getValueAt(row, 2).toString());
    }

}
