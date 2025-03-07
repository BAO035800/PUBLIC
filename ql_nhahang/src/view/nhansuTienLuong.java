package view;
import java.awt.*;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class nhansuTienLuong extends JPanel implements connectData {
    private JTable table;
    private DefaultTableModel tableModel;
    private Connection conn;

    public nhansuTienLuong() {
        setLayout(new BorderLayout());

        // Panel chứa nút chức năng
        JPanel controlPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        controlPanel.add(btnThem);
        controlPanel.add(btnSua);
        controlPanel.add(btnXoa);

        // Tạo bảng dữ liệu nhân viên
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Kết nối database và load dữ liệu
        conn = connect();
        loadData(tableModel, "SELECT * FROM tienluong");

        // Chia giao diện thành 2 phần
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, controlPanel, scrollPane);
        splitPane.setResizeWeight(0.1);
        add(splitPane, BorderLayout.CENTER);
    }
}
