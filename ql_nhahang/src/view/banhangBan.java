package view;
import java.awt.*;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class banhangBan extends JPanel implements connectData {
    private JTable table;
    private DefaultTableModel tableModel;
    private Connection conn;

    public banhangBan() {
        setLayout(new BorderLayout());

        // Panel chứa bàn ăn
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel dataPanel = new JPanel(new BorderLayout());

        // Tạo panel chứa bàn và nút
        JPanel panelBan = new JPanel(new GridLayout(3, 4, 30, 30));
        JPanel panelNut = new JPanel(new GridLayout(3, 1, 30, 30));
        JPanel panelNote = new JPanel(new GridLayout(1, 1, 30, 30));
        panelBan.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 20));
        panelNut.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tạo bàn
        for (int i = 1; i <= 10; i++) {
            JButton btnBan = new JButton("B" + i);
            btnBan.setFont(new Font("Arial", Font.BOLD, 14));
            btnBan.setPreferredSize(new Dimension(80, 60));
            btnBan.setMinimumSize(new Dimension(60, 50));
            btnBan.setFocusable(false);
            btnBan.setBackground(new Color(173, 172, 172));
            panelBan.add(btnBan);
        }
        contentPanel.add(panelBan, BorderLayout.CENTER);

        // Tạo nút
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        panelNut.add(btnThem);
        panelNut.add(btnSua);
        panelNut.add(btnXoa);
        contentPanel.add(panelNut, BorderLayout.EAST);

        // Tạo note
        JLabel note = new JLabel("Ghi chú: Bàn màu xanh là bàn trống, bàn màu đỏ là bàn đã có người");
        panelNote.add(note);
        contentPanel.add(panelNote, BorderLayout.SOUTH);

        // **Tạo JTable riêng**
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        dataPanel.add(scrollPane, BorderLayout.CENTER);

        // Kết nối database và load dữ liệu
        conn = connect();
        loadData(tableModel, "SELECT * FROM ban");  // Gửi tableModel vào phương thức loadData()

        // Chia giao diện thành 2 phần
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, contentPanel, dataPanel);
        splitPane.setResizeWeight(0.5);
        add(splitPane, BorderLayout.CENTER);
    }
}
