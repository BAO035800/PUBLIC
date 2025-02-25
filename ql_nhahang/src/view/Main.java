package ql_nhahang.src.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản lý nhà hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Tạo các panel khác nhau
        JPanel homePanel = new JPanel();
        homePanel.add(new JLabel("Trang chủ"));

        QLbanhang qlbanhangPanel = new QLbanhang();
        QLnhansu qlnhansuPanel = new QLnhansu();
        // QLkho qlkhoPanel = new QLkho(); // Tạo thêm lớp QLkho nếu cần

        // Thêm các tab vào JTabbedPane
        tabbedPane.addTab("Trang chủ", homePanel);
        tabbedPane.addTab("Quản lý bán hàng", qlbanhangPanel);
        tabbedPane.addTab("Quản lý nhân sự", qlnhansuPanel);
        // tabbedPane.addTab("Quản lý kho", qlkhoPanel);

        // Tạo panel với GridLayout cho các nút bấm
        JPanel ql = new JPanel();
        ql.setLayout(new GridLayout(4, 1));
        JButton btn1 = new JButton("Trang chủ");
        JButton btn2 = new JButton("Quản lý bán hàng");
        JButton btn3 = new JButton("Quản lý nhân sự");
        JButton btn4 = new JButton("Quản lý kho");

        // Thêm các nút vào panel
        ql.add(btn1);
        ql.add(btn2);
        ql.add(btn3);
        ql.add(btn4);

        // Thêm hành động cho các nút bấm để hiển thị các tab tương ứng
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(0);
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(2);
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(3);
            }
        });

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.add(ql, BorderLayout.WEST);
        frame.setVisible(true);
    }
}
