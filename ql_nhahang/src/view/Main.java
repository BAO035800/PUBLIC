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
    private String userRole; // Vai trò của người dùng
    private Map<String, JButton> buttonMap; // Lưu các nút theo panel key

    public Main(String role) {
        this.userRole = role; // Lưu vai trò
        buttonMap = new HashMap<>(); // Khởi tạo map để lưu nút
        setTitle("Quản Lý Nhà Hàng");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        setJMenuBar(createMenuBar());

        JPanel menuPanel = createMenuPanel();

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Thêm các panel dựa trên vai trò
        if (userRole.equals("Admin")) {
            contentPanel.add(new QLbanhang(), "BanHang");
            contentPanel.add(new QLbep(), "Bep");
            contentPanel.add(new QLkho(), "Kho");
            contentPanel.add(new QLnhansu(), "NhanSu");
        } else if (userRole.equals("BanHang")) {
            contentPanel.add(new QLbanhang(), "BanHang");
        } else if (userRole.equals("Bep")) {
            contentPanel.add(new QLbep(), "Bep");
            contentPanel.add(new QLkho(), "Kho");
        }

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Mặc định hiển thị panel đầu tiên dựa trên vai trò
        if (userRole.equals("Admin") || userRole.equals("BanHang")) {
            cardLayout.show(contentPanel, "BanHang");
            setButtonFocus(buttonMap.get("BanHang")); // Sử dụng nút từ buttonMap
        } else if (userRole.equals("Bep")) {
            cardLayout.show(contentPanel, "Bep");
            setButtonFocus(buttonMap.get("Bep")); // Sử dụng nút từ buttonMap
        }

        setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridLayout(7, 1));
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));
        menuPanel.setBackground(Color.decode("#69B9EB"));

        JLabel adminLabel = new JLabel("XIN CHÀO " + userRole.toUpperCase(), SwingConstants.CENTER);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 14));
        menuPanel.add(adminLabel);

        // Danh sách nút và panel keys
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
            null // THOÁT
        };

        // Xác định các nút hiển thị dựa trên vai trò
        boolean[] showButton = new boolean[buttonNames.length];
        if (userRole.equals("Admin")) {
            showButton = new boolean[]{true, true, true, true, true}; // Admin thấy tất cả
        } else if (userRole.equals("BanHang")) {
            showButton = new boolean[]{true, false, false, false, true}; // Bán hàng chỉ thấy tab Bán hàng
        } else if (userRole.equals("Bep")) {
            showButton = new boolean[]{false, true, false, true, true}; // Bếp chỉ thấy tab Bếp và Kho
        }

        // Tạo các nút dựa trên vai trò
        for (int i = 0; i < buttonNames.length; i++) {
            if (!showButton[i]) {
                menuPanel.add(new JLabel()); // Thêm placeholder nếu nút không được hiển thị
                continue;
            }

            JButton button = createStyledButton(buttonNames[i]);

            if (buttonNames[i].equals("THOÁT")) {
                button.addActionListener(e -> System.exit(0));
            } else {
                final String panelKey = panelKeys[i];
                button.addActionListener(e -> {
                    cardLayout.show(contentPanel, panelKey);
                    setButtonFocus(button);
                });
                buttonMap.put(panelKey, button); // Lưu nút vào map
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
        if (selectedButton == null) return; // Tránh lỗi nếu nút không tồn tại
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
            
            String sql = "SELECT * FROM " + tableName;
            
            try (Connection conn = connectData.connect(); 
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        Object columnValue = rs.getObject(i);
                        row.put(columnName, columnValue);
                    }
                    data.add(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return data;
        }
    }

    private void updateTable(JTable table, List<Map<String, Object>> data) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    
        for (Map<String, Object> row : data) {
            model.addRow(row.values().toArray());
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}