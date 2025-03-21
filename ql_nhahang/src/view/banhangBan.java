package view;
import controller.BanController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.BanDAO;
import model.connectData;
public class banhangBan extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtBan,txtTinhTrang,txtGhiChu ;
    private JComboBox<String> cbTinhTrang;
    private JButton btnLuuThem, btnHuy,btnLuuSua,btnRefresh;
    private int selectedRow = -1;
    
    public JTextField getTxtBan() {
        return txtBan;
    }
    public void setTxtBan(JTextField txtBan) {
        this.txtBan = txtBan;
    }
    public JTextField getTxtTinhTrang() {
        return txtTinhTrang;
    }
    public void setTxtTinhTrang(JTextField txtTinhTrang) {
        this.txtTinhTrang = txtTinhTrang;
    }
    public JTextField getTxtGhiChu() {
        return txtGhiChu;
    }
    public void setTxtGhiChu(JTextField txtGhiChu) {
        this.txtGhiChu = txtGhiChu;
    }
    public JComboBox<String> getCbTinhTrang() {
        return cbTinhTrang;
    }
    public void setCbTinhTrang(JComboBox<String> cbTinhTrang) {
        this.cbTinhTrang = cbTinhTrang;
    }
    public banhangBan() {
        String[] nvColumns = {"Số bàn", "Tình trạng", "Ghi chú"};
        setLayout(new BorderLayout(10,10));

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

        // Bảng dữ liệu nhân viên
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu từ database
        connectData.loadData(tableModel, "SELECT * FROM ban", nvColumns);

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(5,4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin bàn"));
        formPanel.setBackground(Color.WHITE);

        // Các input field
        txtBan = new JTextField();
        txtTinhTrang = new JTextField();
        txtGhiChu = new JTextField();
        cbTinhTrang = new JComboBox<>(new String[] {"Trống", "Đã đặt", "Đang phục vụ"});
        cbTinhTrang.setSelectedIndex(0);
        cbTinhTrang.addActionListener(e -> {
            txtTinhTrang.setText(cbTinhTrang.getSelectedItem().toString());
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

        // Thêm các thành phần vào form
        formPanel.add(new JLabel("Số bàn"));
        formPanel.add(txtBan);
        formPanel.add(new JLabel("Tình trạng"));
        formPanel.add(cbTinhTrang);
        formPanel.add(new JLabel("Ghi chú"));
        formPanel.add(txtGhiChu);

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
            }
            fillForm(selectedRow);
        });

        // Sự kiện "Xóa"
        btnXoa.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn cần xóa!");
                return;
            }
        
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa bàn này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Lấy bàn từ bảng
                int soBan = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                // Xóa bàn
                BanController banController = new BanController(banhangBan.this,new BanDAO());
                if(banController.xoaBan(soBan)){
                    JOptionPane.showMessageDialog(this, "Xóa bàn thành công!");
                    connectData.loadData(tableModel, "SELECT * FROM ban", nvColumns);   
                }else{
                    JOptionPane.showMessageDialog(this, "Xóa bàn thất bại!");
                }
            }
            clearForm();
        });

        // Sự kiện "Lưu Thêm"
        btnLuuThem.addActionListener(e -> {
            BanController banController = new BanController(banhangBan.this,new BanDAO());
            banController.themBan();
            connectData.loadData(tableModel, "SELECT * FROM ban", nvColumns);
        });
        // Sự kiện "Lưu Sửa"
        btnLuuSua.addActionListener(e -> {
            BanController banController = new BanController(banhangBan.this,new BanDAO());
            banController.suaBan();
            connectData.loadData(tableModel, "SELECT * FROM ban", nvColumns);
        });
        
        // Sự kiện "Hủy"
        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
        });
        // Sự kiện "Refresh"
        btnRefresh.addActionListener(e -> {
            connectData.loadData(tableModel, "SELECT * FROM ban", nvColumns);
        });
    }
    private void clearForm() {
        txtBan.setText("");
        txtTinhTrang.setText("");
        txtGhiChu.setText("");
        cbTinhTrang.setSelectedIndex(0);
    }
    private void fillForm(int row) {
        txtBan.setText(table.getValueAt(row, 0).toString());
        txtTinhTrang.setText(table.getValueAt(row, 1).toString());
        txtGhiChu.setText(table.getValueAt(row, 2).toString());
    }
}
