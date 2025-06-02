package view;

import java.awt.*;
import javax.swing.*;
import model.UsersDAO;

public class Login extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JCheckBox chkShowPassword;

    public Login() {
        setTitle("Đăng nhập");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel chính
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(panel);

        // Tiêu đề
        JLabel lblTitle = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lblTitle, BorderLayout.NORTH);

        // Panel input
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        inputPanel.add(new JLabel("Tên đăng nhập:"));
        txtUsername = new JTextField();
        inputPanel.add(txtUsername);

        inputPanel.add(new JLabel("Mật khẩu:"));
        txtPassword = new JPasswordField();
        inputPanel.add(txtPassword);

        chkShowPassword = new JCheckBox("Hiển thị mật khẩu");
        chkShowPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        inputPanel.add(chkShowPassword);
        
        panel.add(inputPanel, BorderLayout.CENTER);

        // Nút đăng nhập
        btnLogin = new JButton("Đăng nhập");
        styleButton(btnLogin);
        panel.add(btnLogin, BorderLayout.SOUTH);

        // Sự kiện ẩn/hiện mật khẩu
        chkShowPassword.addActionListener(e -> {
            if (chkShowPassword.isSelected()) {
                txtPassword.setEchoChar((char) 0);
            } else {
                txtPassword.setEchoChar('•');
            }
        });

        // Xử lý sự kiện đăng nhập
        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Gọi UsersDAO để đăng nhập và lấy vai trò
        String role = UsersDAO.loginUser(username, password);

        if (role != null) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công! Vai trò: " + role);
            new Main(role).setVisible(true); // Truyền vai trò vào Main
            dispose(); // Đóng form đăng nhập
        } else {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 144, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}