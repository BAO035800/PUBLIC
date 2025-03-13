package view;
import controller.NhanVienController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.NhanVienDAO;
public class nhansuNhanVien extends JPanel implements connectData {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel formPanel;
    private JTextField txtTen, txtTuoi, txtChucVu, txtLuong, txtSoDienThoai, txtMaNhanVien;
    private JRadioButton radNam, radNu;
    private JButton btnLuuThem, btnHuy,btnLuuXoa,btnLuuSua;
    private int selectedRow = -1;

    public JTextField getTxtTen() {
        return txtTen;
    }
    public void setTxtTen(JTextField txtTen) {
        this.txtTen = txtTen;
    }
    public JTextField getTxtTuoi() {
        return txtTuoi;
    }
    public void setTxtTuoi(JTextField txtTuoi) {
        this.txtTuoi = txtTuoi;
    }
    public JTextField getTxtChucVu() {
        return txtChucVu;
    }
    public void setTxtChucVu(JTextField txtChucVu) {
        this.txtChucVu = txtChucVu;
    }
    public JTextField getTxtLuong() {
        return txtLuong;
    }
    public void setTxtLuong(JTextField txtLuong) {
        this.txtLuong = txtLuong;
    }
    public JTextField getTxtSoDienThoai() {
        return txtSoDienThoai;
    }
    public void setTxtSoDienThoai(JTextField txtSoDienThoai) {
        this.txtSoDienThoai = txtSoDienThoai;
    }
    public JTextField getTxtMaNhanVien() {
        return txtMaNhanVien;
    }
    public void setTxtMaNhanVien(JTextField txtMaNhanVien) {
        this.txtMaNhanVien = txtMaNhanVien;
    }
    public JRadioButton getRadNam() {
        return radNam;
    }
    public void setRadNam(JRadioButton radNam) {
        this.radNam = radNam;
    }
    public JRadioButton getRadNu() {
        return radNu;
    }
    public void setRadNu(JRadioButton radNu) {
        this.radNu = radNu;
    }
    public nhansuNhanVien() {
        setLayout(new BorderLayout(10,10));

        // Panel chứa nút chức năng
        JPanel controlPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        controlPanel.add(btnThem);
        controlPanel.add(btnSua);
        controlPanel.add(btnXoa);
        add(controlPanel, BorderLayout.NORTH);

        // Bảng dữ liệu nhân viên
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu từ database
        loadData(tableModel, "SELECT * FROM nhanvien");

        // Panel nhập liệu
        formPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nhập thông tin nhân viên"));

        // Các input field
        txtMaNhanVien = new JTextField();
        txtTen = new JTextField();
        txtTuoi = new JTextField();
        txtChucVu = new JTextField();
        txtLuong = new JTextField();
        txtSoDienThoai = new JTextField();

        // Nhóm radio button cho giới tính
        radNam = new JRadioButton("Nam");
        radNu = new JRadioButton("Nữ");
        ButtonGroup group = new ButtonGroup();
        group.add(radNam);
        group.add(radNu);

        // Panel chứa radio button để bố trí gọn hơn
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(radNam);
        genderPanel.add(radNu);

        // Các nút
        btnLuuThem = new JButton("Lưu");
        btnHuy = new JButton("Hủy");
        btnLuuXoa=new JButton("Lưu");
        btnLuuSua=new JButton("Lưu");

        // Thêm các thành phần vào form
        formPanel.add(new JLabel("Mã nhân viên:"));
        formPanel.add(txtMaNhanVien);
        formPanel.add(new JLabel("Tên:"));
        formPanel.add(txtTen);
        formPanel.add(new JLabel("Tuổi:"));
        formPanel.add(txtTuoi);
        formPanel.add(new JLabel("Chức vụ:"));
        formPanel.add(txtChucVu);
        formPanel.add(new JLabel("Lương 1 giờ:"));
        formPanel.add(txtLuong);
        formPanel.add(new JLabel("Số điện thoại:"));
        formPanel.add(txtSoDienThoai);
        formPanel.add(new JLabel("Giới tính:"));
        formPanel.add(genderPanel); // Chỉ add panel chứa radio button, không có JTextField dư thừa

        // Thêm nút Lưu và Hủy
        formPanel.add(btnLuuThem);
        formPanel.add(btnHuy);
        formPanel.add(btnLuuXoa);
        formPanel.add(btnLuuSua);

        formPanel.setVisible(false);
        add(formPanel, BorderLayout.SOUTH);

        // Sự kiện "Thêm"
        btnThem.addActionListener(e -> {
            selectedRow = -1;
            clearForm();
            formPanel.setVisible(true);
            btnLuuThem.setVisible(true);
            btnLuuSua.setVisible(false);
            btnLuuXoa.setVisible(false);
        });
        // Sự kiện "Sửa"
        btnSua.addActionListener(e -> {
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa!");
            } else {
                formPanel.setVisible(true);
                btnLuuThem.setVisible(false);
                btnLuuSua.setVisible(true);
                btnLuuXoa.setVisible(false);
            }
        });
        // Sự kiện "Xóa"
        // btnXoa.addActionListener(e -> {
        //     int row = table.getSelectedRow();
        //     if (row == -1) {
        //         JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
        //         return;
        //     }
        
        //     // Xác nhận trước khi xóa
        //     int confirm = JOptionPane.showConfirmDialog(this, 
        //         "Bạn có chắc chắn muốn xóa nhân viên này không?", 
        //         "Xác nhận xóa", 
        //         JOptionPane.YES_NO_OPTION);
        
        //     if (confirm == JOptionPane.YES_OPTION) {
        //         // Lấy mã nhân viên từ JTable
        //         String maNV = table.getValueAt(row, 0).toString(); // Giả sử cột 0 là mã nhân viên
                
        //         // Gọi controller để xóa
        //         NhanVienController controller = new NhanVienController(this, new NhanVienDAO());
        //         boolean success = controller.xoaNhanVien(maNV);
        
        //         if (success) {
        //             JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
        //             loadData(tableModel, "SELECT * FROM nhanvien"); // Load lại bảng
        //         } else {
        //             JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại! Kiểm tra ràng buộc dữ liệu.");
        //         }
        //     }
        // });
        

        // Sự kiện "Lưu Thêm"
        btnLuuThem.addActionListener(e -> {
            NhanVienController controller = new NhanVienController(nhansuNhanVien.this , new NhanVienDAO());
            controller.themNhanVien();
            loadData(tableModel, "SELECT * FROM nhanvien");
        });
        // Sự kiện "Lưu Sửa"
        btnLuuSua.addActionListener(e -> {
            NhanVienController controller = new NhanVienController(nhansuNhanVien.this , new NhanVienDAO());
            controller.suaNhanVien();
            loadData(tableModel, "SELECT * FROM nhanvien");
        });
        // Sự kiện "Lưu Xóa"
        btnLuuXoa.addActionListener(e -> {
            
        });
        // Sự kiện "Hủy"
        btnHuy.addActionListener(e -> {
            formPanel.setVisible(false);
        });
    }
    private void clearForm() {
        txtMaNhanVien.setText("");
        txtTen.setText("");
        txtTuoi.setText("");
        txtChucVu.setText("");
        txtLuong.setText("");
        txtSoDienThoai.setText("");
        radNam.setSelected(true);
    }
}
