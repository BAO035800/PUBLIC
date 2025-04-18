package controller;

import javax.swing.JOptionPane;
import model.CheBienMon;
import model.CheBienMonDAO;
import view.bepCheBienMon;

public class CheBienMonController {
    private bepCheBienMon view;
    private CheBienMonDAO dao;
    public CheBienMonController(bepCheBienMon view, CheBienMonDAO dao) {
        this.view = view;
        this.dao = dao;
    }
    
    // Thêm chế biến món
    public void themCheBienMon() {
        try {
            // Lấy dữ liệu từ view
            String maCheBien = view.getTxtMaCheBien().getText().trim();
            String maMon = view.getTxtMaMon().getText().trim();
            String maHoaDon = view.getTxtMaHoaDon().getText().trim();
            String trangThai = view.getTxtTrangThai().getText().trim();
            
            // Kiểm tra dữ liệu bắt buộc
            if (maCheBien.isEmpty() || maMon.isEmpty() || maHoaDon.isEmpty() || trangThai.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            
            // Tạo đối tượng model CheBienMon
            CheBienMon cb = new CheBienMon();
            cb.setMaCheBien(maCheBien);
            cb.setMaMon(maMon);
            cb.setMaHoaDonBanHang(maHoaDon);
            cb.setTrangThai(trangThai);
            
            // Gọi phương thức thêm trong DAO
            dao.themCheBienMon(cb);
            JOptionPane.showMessageDialog(view, "Thêm chế biến món thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm chế biến món: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Sửa chế biến món
    public void suaCheBienMon() {
        try {
            // Lấy dữ liệu từ view
            String maCheBien = view.getTxtMaCheBien().getText().trim();
            String maMon = view.getTxtMaMon().getText().trim();
            String maHoaDon = view.getTxtMaHoaDon().getText().trim();
            String trangThai = view.getTxtTrangThai().getText().trim();
            
            if (maCheBien.isEmpty() || maMon.isEmpty() || maHoaDon.isEmpty() || trangThai.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            
            // Tạo đối tượng model CheBienMon
            CheBienMon cb = new CheBienMon();
            cb.setMaCheBien(maCheBien);
            cb.setMaMon(maMon);
            cb.setMaHoaDonBanHang(maHoaDon);
            cb.setTrangThai(trangThai);
            
            // Gọi phương thức cập nhật trong DAO
            dao.suaCheBienMon(cb);
            JOptionPane.showMessageDialog(view, "Cập nhật chế biến món thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật chế biến món: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Xóa chế biến món theo mã chế biến món
    public void xoaCheBienMon(String maCheBien) {
        try {
            if (maCheBien == null || maCheBien.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn chế biến món cần xóa!");
                return;
            }
    
            int confirm = JOptionPane.showConfirmDialog(view, 
                    "Bạn có chắc chắn muốn xóa chế biến món này?", 
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
    
            CheBienMonDAO dao = new CheBienMonDAO();
            dao.xoaCheBienMon(maCheBien); // gọi hàm xóa từ DAO
    
            JOptionPane.showMessageDialog(view, "✅ Xóa chế biến món thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "❌ Lỗi khi xóa chế biến món: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}