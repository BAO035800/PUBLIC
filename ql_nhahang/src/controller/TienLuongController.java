package controller;

import java.sql.*;
import javax.swing.*;
import model.Connect;
import model.TienLuong;
import model.TienLuongDAO;
import view.nhansuTienLuong;

public class TienLuongController {
    private nhansuTienLuong view;
    private TienLuongDAO model;

    public TienLuongController(nhansuTienLuong view, TienLuongDAO model) {
        this.view = view;
        this.model = model;
    }

    public void themTienLuong() {
        String maLuong = view.getTxtMaLuong().getText().trim();
        String maNhanVien = view.getTxtMaNV().getText().trim();
        
        if (maLuong.isEmpty() || maNhanVien.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Mã lương và mã nhân viên không được để trống!");
            return;
        }
        
        if (!kiemTraNhanVienTonTai(maNhanVien)) {
            JOptionPane.showMessageDialog(view, "Mã nhân viên không tồn tại!");
            return;
        }
        
        try {
            int gioLam = Integer.parseInt(view.getTxtGioLam().getText().trim());
            String tinhTrangLuong = view.getCbTinhTrangLuong().getSelectedItem().toString();
            String ghiChu = view.getTxtGhiChu().getText().trim();
            
            // Sử dụng setter thay vì constructor
            TienLuong tienLuong = new TienLuong();
            tienLuong.setMaLuong(maLuong);
            tienLuong.setMaNhanVien(maNhanVien);
            tienLuong.setTenNhanVien(view.getTxtTenNV().getText().trim());
            tienLuong.setTinhTrangLuong(tinhTrangLuong);
            tienLuong.setGioLamViec(gioLam);
            tienLuong.setGhiChu(ghiChu);
            
            model.themTienLuong(tienLuong);
            JOptionPane.showMessageDialog(view, "Thêm tiền lương thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Dữ liệu không hợp lệ! Vui lòng kiểm tra lại.");
        }
    }

    public void suaTienLuong() {
        String maLuong = view.getTxtMaLuong().getText().trim();
        if (!kiemTraTienLuongTonTai(maLuong)) {
            JOptionPane.showMessageDialog(view, "Không tìm thấy bản ghi tiền lương!");
            return;
        }
        
        try {
            int gioLam = Integer.parseInt(view.getTxtGioLam().getText().trim());
            String tinhTrangLuong = view.getCbTinhTrangLuong().getSelectedItem().toString();
            String ghiChu = view.getTxtGhiChu().getText().trim();
            
            // Sử dụng setter thay vì constructor
            TienLuong tienLuong = new TienLuong();
            tienLuong.setMaLuong(maLuong);
            tienLuong.setMaNhanVien(view.getTxtMaNV().getText().trim());
            tienLuong.setTenNhanVien(view.getTxtTenNV().getText().trim());
            tienLuong.setTinhTrangLuong(tinhTrangLuong);
            tienLuong.setGioLamViec(gioLam);
            tienLuong.setGhiChu(ghiChu);
            
            model.suaTienLuong(tienLuong);
            JOptionPane.showMessageDialog(view, "Cập nhật tiền lương thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Dữ liệu không hợp lệ! Vui lòng kiểm tra lại.");
        }
    }
    
    public void xoaTienLuong() {
        String maLuong = view.getTxtMaLuong().getText().trim();
        if (maLuong.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn tiền lương cần xóa!");
            return;
        }
        if (model.xoaTienLuong(maLuong)) {
            JOptionPane.showMessageDialog(view, "Xóa tiền lương thành công!");
        } else {
            JOptionPane.showMessageDialog(view, "Không tìm thấy bản ghi tiền lương!");
        }
    }
    
    public boolean kiemTraTienLuongTonTai(String maLuong) {
        String sql = "SELECT COUNT(*) FROM tienluong WHERE MaLuong = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maLuong);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi kiểm tra tiền lương: " + e.getMessage());
        }
        return false;
    }
    
    public boolean kiemTraNhanVienTonTai(String maNhanVien) {
        String sql = "SELECT COUNT(*) FROM nhanvien WHERE MaNhanVien = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maNhanVien);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi kiểm tra nhân viên: " + e.getMessage());
        }
        return false;
    }
}
