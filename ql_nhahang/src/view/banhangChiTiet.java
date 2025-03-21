package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.connectData;

public class banhangChiTiet extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtID,txtMaMon,txtMaHoaDonBanHang,txtSoBan,txtGiaTien,txtSoLuongDat,txtTongTien;
    private JButton btnLuuThem, btnHuy,btnLuuSua,btnRefresh;
    private int selectedRow = -1;
    public JTextField getTxtID() {
        return txtID;
    }
    public void setTxtID(JTextField txtID) {
        this.txtID = txtID;
    }
    public JTextField getTxtMaMon() {
        return txtMaMon;
    }
    public void setTxtMaMon(JTextField txtMaMon) {
        this.txtMaMon = txtMaMon;
    }
    public JTextField getTxtMaHoaDonBanHang() {
        return txtMaHoaDonBanHang;
    }
    public void setTxtMaHoaDonBanHang(JTextField txtMaHoaDonBanHang) {
        this.txtMaHoaDonBanHang = txtMaHoaDonBanHang;
    }
    public JTextField getTxtSoBan() {
        return txtSoBan;
    }
    public void setTxtSoBan(JTextField txtSoBan) {
        this.txtSoBan = txtSoBan;
    }
    public JTextField getTxtGiaTien() {
        return txtGiaTien;
    }
    public void setTxtGiaTien(JTextField txtGiaTien) {
        this.txtGiaTien = txtGiaTien;
    }
    public JTextField getTxtSoLuongDat() {
        return txtSoLuongDat;
    }
    public void setTxtSoLuongDat(JTextField txtSoLuongDat) {
        this.txtSoLuongDat = txtSoLuongDat;
    }
    public JTextField getTxtTongTien() {
        return txtTongTien;
    }
    public void setTxtTongTien(JTextField txtTongTien) {
        this.txtTongTien = txtTongTien;
    }
    public banhangChiTiet(){
        String[] chitietColumns={"ID","Mã món","Mã hóa đơn","Số bàn","Giá tiền","Số lượng đặt","Tổng tiền"};
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
        //Tạo bảng
        tableModel = new DefaultTableModel(chitietColumns, 0); 
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER); 
        // Load dữ liệu từ database
        connectData.loadData(tableModel, "SELECT * FROM hoadonbanhangchitiet", chitietColumns);
         // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(5,4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập chi tiết hóa đơn"));
        formPanel.setBackground(Color.WHITE);
        // Các input field
        txtID=new JTextField();
        txtMaMon=new JTextField();
        txtMaHoaDonBanHang=new JTextField();
        txtSoBan=new JTextField();
        txtGiaTien=new JTextField();
        txtSoLuongDat=new JTextField();
        txtTongTien=new JTextField();
        btnLuuThem = new JButton("Lưu thêm");
        btnHuy = new JButton("Hủy");
        btnLuuSua = new JButton("Lưu sửa");
        btnRefresh = new JButton("Refresh");
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
        formPanel.add(new JLabel("ID:"));
        formPanel.add(txtID);
        formPanel.add(new JLabel("Mã món:"));
        formPanel.add(txtMaMon);
        formPanel.add(new JLabel("Mã hóa đơn:"));
        formPanel.add(txtMaHoaDonBanHang);
        formPanel.add(new JLabel("Số bàn:"));
        formPanel.add(txtSoBan);
        formPanel.add(new JLabel("Giá tiền:"));
        formPanel.add(txtGiaTien);
        formPanel.add(new JLabel("Số lượng đặt:"));
        formPanel.add(txtSoLuongDat);
        formPanel.add(new JLabel("Tổng tiền:"));
        formPanel.add(txtTongTien);
        formPanel.add(btnLuuThem);
        formPanel.add(btnHuy);
        formPanel.add(btnLuuSua);
        formPanel.add(btnRefresh);

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
        btnXoa.addActionListener(e->{

        });
        btnLuuThem.addActionListener(e->{

        });
        btnHuy.addActionListener(e->{
            formPanel.setVisible(false);
            clearForm();
        });
        btnRefresh.addActionListener(e->{
            connectData.loadData(tableModel, "SELECT * FROM hoadonbanhangchitiet", chitietColumns);
        });
    }
    public void clearForm(){
        txtID.setText("");
        txtMaMon.setText("");
        txtMaHoaDonBanHang.setText("");
        txtSoBan.setText("");
        txtGiaTien.setText("");
        txtSoLuongDat.setText("");
        txtTongTien.setText("");
    }
    public void fillForm(int row){
        txtID.setText(tableModel.getValueAt(row, 0).toString());
        txtMaMon.setText(tableModel.getValueAt(row, 1).toString());
        txtMaHoaDonBanHang.setText(tableModel.getValueAt(row, 2).toString());
        txtSoBan.setText(tableModel.getValueAt(row, 3).toString());
        txtGiaTien.setText(tableModel.getValueAt(row, 4).toString());
        txtSoLuongDat.setText(tableModel.getValueAt(row, 5).toString());
        txtTongTien.setText(tableModel.getValueAt(row, 6).toString());
    }
}
