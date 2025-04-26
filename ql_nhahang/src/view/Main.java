package view;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.connectData;

public class Main extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JButton currentButton = null;

    public Main() {
        setTitle("Quản Lý Nhà Hàng");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        setJMenuBar(createMenuBar());

        JPanel menuPanel = createMenuPanel();

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(new QLbanhang(), "BanHang");
        contentPanel.add(new QLbep(), "Bep");
        contentPanel.add(new QLkho(), "Kho");
        contentPanel.add(new QLnhansu(), "NhanSu");

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Mặc định hiển thị panel Quản Lý Bán Hàng
        setButtonFocus((JButton) menuPanel.getComponent(1));
        cardLayout.show(contentPanel, "BanHang");

        setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridLayout(7, 1));
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));
        menuPanel.setBackground(Color.decode("#69B9EB"));

        JLabel adminLabel = new JLabel("XIN CHÀO ADMIN", SwingConstants.CENTER);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 14));
        menuPanel.add(adminLabel);

        String[] buttonNames = {
            "QUẢN LÝ BÁN HÀNG",
            "QUẢN LÝ BẾP",
            "QUẢN LÝ NHÂN SỰ",
            "QUẢN LÝ KHO",
            "THOÁT"
        };

        String[] panelKeys = {
            "BanHang",
            "Bep",
            "NhanSu",
            "Kho",
            "REFRESH",
            null // THOÁT
        };

        for (int i = 0; i < buttonNames.length; i++) {
            JButton button = createStyledButton(buttonNames[i]);

            if (buttonNames[i].equals("THOÁT")) {
                button.addActionListener(e -> System.exit(0));
            } else {
                final String panelKey = panelKeys[i];
                button.addActionListener(e -> {
                    cardLayout.show(contentPanel, panelKey);
                    setButtonFocus(button);
                });
            }

            menuPanel.add(button);
        }
        return menuPanel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(new Color(194, 239, 255));
        button.setFont(new Font("Arial", Font.BOLD, 12));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button != currentButton) {
                    button.setBackground(new Color(111, 205, 240));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button != currentButton) {
                    button.setBackground(new Color(194, 239, 255));
                }
            }
        });
        return button;
    }

    private void setButtonFocus(JButton selectedButton) {
        selectedButton.setFont(new Font("Arial", Font.BOLD, 14));
        selectedButton.setBackground(new Color(111, 205, 240));

        if (currentButton != null && currentButton != selectedButton) {
            currentButton.setFont(new Font("Arial", Font.PLAIN, 12));
            currentButton.setBackground(new Color(194, 239, 255));
        }
        currentButton = selectedButton;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        return menuBar;
    }

    public static class DatabaseHelper {
        public static List<Map<String, Object>> loadDataFromTable(String tableName) {
            List<Map<String, Object>> data = new ArrayList<>();
            
            // Câu lệnh SQL để lấy dữ liệu
            String sql = "SELECT * FROM " + tableName;
            
            // Giả sử bạn có phương thức kết nối và thực thi câu lệnh SQL để lấy dữ liệu vào list
            try (Connection conn = connectData.connect(); 
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

                // Lấy thông tin về các cột từ ResultSetMetaData
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount(); // Số lượng cột trong bảng

                // Lấy dữ liệu từ ResultSet
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();

                    // Duyệt qua tất cả các cột của bảng
                    for (int i = 1; i <= columnCount; i++) {
                        // Lấy tên cột từ metaData và giá trị từ ResultSet
                        String columnName = metaData.getColumnName(i);
                        Object columnValue = rs.getObject(i);
                        
                        // Thêm cột vào map
                        row.put(columnName, columnValue);
                    }
                    
                    // Thêm dòng vào danh sách
                    data.add(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return data;
        }
    }

    // Phương thức cập nhật bảng chung
    private void updateTable(JTable table, List<Map<String, Object>> data) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);  // Xóa các dòng cũ
    
        // Thêm dữ liệu mới vào bảng
        for (Map<String, Object> row : data) {
            model.addRow(row.values().toArray());
        }
    }

    public static void main(String[] args) {
        Login loginForm = new Login();
    }
}

