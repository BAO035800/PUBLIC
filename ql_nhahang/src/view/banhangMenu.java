package view;
import java.awt.*;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class banhangMenu extends JPanel implements connectData {
    private JTable table;
    private DefaultTableModel tableModel;
    private Connection conn;
    public banhangMenu() {
        setLayout(new BorderLayout());
        // Panel chứa menu
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel dataPanel = new JPanel(new BorderLayout());
        // Tạo panel chứa menu và nút
        JPanel panelMenu = new JPanel(new GridLayout(3, 4, 30, 30)); // 3 hàng, 4 cột, khoảng cách 30px
        JPanel panelNut = new JPanel(new GridLayout(3,1,30,30)); // 3 hàng, 1 cột, khoảng cách 
        JPanel panelNote = new JPanel(new GridLayout(1,1,30,30)); // 1 hàng, 1 cột, khoảng cách
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 20));
        panelNut.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Tạo menu 
        // cần làm lại
        for (int i = 1; i <= 10; i++) {
            JButton btnMenu = new JButton("M" + i);
            btnMenu.setFont(new Font("Arial", Font.BOLD, 14)); // Chữ đậm hơn
            btnMenu.setPreferredSize(new Dimension(80, 60)); // Kích thước
            btnMenu.setMinimumSize(new Dimension(60, 50)); // Kích thước tối thiểu
            // btnMenu.setEnabled(false); // Ngăn chặn bấm
            btnMenu.setFocusable(false); // Ngăn bị focus
            btnMenu.setBackground(new Color(173, 172, 172)); // Màu nền xám
            panelMenu.add(btnMenu);
        }
        contentPanel.add(panelMenu, BorderLayout.CENTER); 
        // Tạo nút
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        panelNut.add(btnThem);
        panelNut.add(btnSua);
        panelNut.add(btnXoa);
        contentPanel.add(panelNut, BorderLayout.EAST);
        //Tạo note
        JLabel note = new JLabel("Ghi chú: Màu xám là món chưa được chọn, màu đỏ là món đã được chọn");
        panelNote.add(note);
        contentPanel.add(panelNote, BorderLayout.SOUTH);
        // Tạo bảng
        
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        dataPanel.add(scrollPane, BorderLayout.CENTER);

        // Kết nối database và load dữ liệu
        conn = connect();
        loadData(tableModel, "SELECT * FROM menu");  // Gửi tableModel vào phương thức loadData()
        // Chia giao diện thành 2 phần có thể co giãn
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, contentPanel, dataPanel);
        splitPane.setResizeWeight(0.5);
        add(splitPane, BorderLayout.CENTER);
    }
}
