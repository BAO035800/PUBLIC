package test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test { // Đổi tên class từ `test` thành `Test`
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtRoomNumber, txtRoomType, txtRoomPrice;

    public Test() { // Hàm tạo phải có cùng tên với class
        // Tạo JFrame chính
        frame = new JFrame("Hotel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Bảng hiển thị danh sách phòng
        String[] columnNames = {"Room Number", "Room Type", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Panel thêm thông tin phòng
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Room Details"));

        JLabel lblRoomNumber = new JLabel("Room Number:");
        txtRoomNumber = new JTextField();

        JLabel lblRoomType = new JLabel("Room Type:");
        txtRoomType = new JTextField();

        JLabel lblRoomPrice = new JLabel("Price:");
        txtRoomPrice = new JTextField();

        JButton btnAdd = new JButton("Add Room");
        JButton btnDelete = new JButton("Delete Room");

        inputPanel.add(lblRoomNumber);
        inputPanel.add(txtRoomNumber);
        inputPanel.add(lblRoomType);
        inputPanel.add(txtRoomType);
        inputPanel.add(lblRoomPrice);
        inputPanel.add(txtRoomPrice);
        inputPanel.add(btnAdd);
        inputPanel.add(btnDelete);

        // Action cho nút "Add Room"
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRoom();
            }
        });

        // Action cho nút "Delete Room"
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRoom();
            }
        });

        // Thêm các thành phần vào JFrame
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Thêm phòng vào bảng
    private void addRoom() {
        String roomNumber = txtRoomNumber.getText();
        String roomType = txtRoomType.getText();
        String roomPrice = txtRoomPrice.getText();

        if (roomNumber.isEmpty() || roomType.isEmpty() || roomPrice.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Object[] rowData = { roomNumber, roomType, roomPrice };
            tableModel.addRow(rowData);

            // Reset input fields
            txtRoomNumber.setText("");
            txtRoomType.setText("");
            txtRoomPrice.setText("");
        }
    }

    // Xóa phòng khỏi bảng
    private void deleteRoom() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a room to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Tạo frame
        SwingUtilities.invokeLater(() -> new Test()); // Gọi hàm tạo Test
    }
}
