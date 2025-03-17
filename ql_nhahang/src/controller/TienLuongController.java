package controller;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.*;
import model.Connect;
import model.TienLuong;
import model.TienLuongDAO;
import view.nhansuTienLuong;
public class TienLuongController implements KTNhanSu {
    private nhansuTienLuong view;
    private TienLuongDAO model;

    public TienLuongController(nhansuTienLuong view, TienLuongDAO model) {
        this.view = view;
        this.model = model;
    }
    public void themTienLuong() {
        String maLuong = view.getTxtMaLuong().getText();
        String maNhanVien = view.getTxtMaNV().getText();
        if (!kiemTraNhanVienTonTai(maNhanVien)) {
            JOptionPane.showMessageDialog(view, "Mã nhân viên không tồn tại!");
            return;
        }
        String tenNhanVien = view.getTxtTenNV().getText();
        String ngayCongStr = view.getTxtNgayCong().getText();
        String tongTienStr = view.getTxtTongTienLuong().getText();
        String soTienThanhToanStr = view.getTxtSoTienThanhToan().getText();
        String tinhTrangLuong = view.getCbTinhTrangLuong().getSelectedItem().toString();
    
        // Kiểm tra nhập đủ thông tin
        if (maLuong.isEmpty() || maNhanVien.isEmpty() || tenNhanVien.isEmpty() || ngayCongStr.isEmpty() || tongTienStr.isEmpty() || soTienThanhToanStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
    
        try {
            int ngayCong = Integer.parseInt(ngayCongStr);
            BigDecimal tongTienLuong = new BigDecimal(tongTienStr);
            BigDecimal soTienThanhToan = new BigDecimal(soTienThanhToanStr);
    
            // Tạo đối tượng tiền lương và thêm vào
            TienLuong tienLuong = new TienLuong();
            tienLuong.setMaLuong(maLuong);
            tienLuong.setMaNhanVien(maNhanVien);
            tienLuong.setTenNhanVien(tenNhanVien);
            tienLuong.setNgayCong(ngayCong);
            tienLuong.setTongTienLuong(tongTienLuong);
            tienLuong.setSoTienThanhToan(soTienThanhToan);
            tienLuong.setTinhTrangLuong(tinhTrangLuong);
    
            model.themTienLuong(tienLuong); // Gọi phương thức thêm tiền lương
            JOptionPane.showMessageDialog(view, "Thêm tiền lương thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Định dạng không hợp lệ! Vui lòng kiểm tra lại.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm tiền lương: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public void suaTienLuong() {
        String maLuong = view.getTxtMaLuong().getText();
        
        // Kiểm tra nếu mã lương không tồn tại
        if (!kiemTraTienLuongTonTai(maLuong)) {
            JOptionPane.showMessageDialog(view, "Không tìm thấy bản ghi tiền lương!");
            return;
        }
    
        String maNhanVien = view.getTxtMaNV().getText();
        if (!kiemTraNhanVienTonTai(maNhanVien)) {
            JOptionPane.showMessageDialog(view, "Mã nhân viên không tồn tại!");
            return;
        }
        
        String tenNhanVien = view.getTxtTenNV().getText();
        String ngayCongStr = view.getTxtNgayCong().getText();
        String tongTienStr = view.getTxtTongTienLuong().getText();
        String soTienThanhToanStr = view.getTxtSoTienThanhToan().getText();
        String tinhTrangLuong = view.getCbTinhTrangLuong().getSelectedItem().toString();
        
        // Kiểm tra nhập đủ thông tin
        if (maLuong.isEmpty() || maNhanVien.isEmpty() || tenNhanVien.isEmpty() || ngayCongStr.isEmpty() || tongTienStr.isEmpty() || soTienThanhToanStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
    
        try {
            // Chuyển đổi dữ liệu nhập vào
            int ngayCong = Integer.parseInt(ngayCongStr);
            BigDecimal tongTienLuong = new BigDecimal(tongTienStr);
            BigDecimal soTienThanhToan = new BigDecimal(soTienThanhToanStr);
    
            // Tạo đối tượng tiền lương
            TienLuong tienLuong = new TienLuong();
            tienLuong.setMaLuong(maLuong);
            tienLuong.setMaNhanVien(maNhanVien);
            tienLuong.setTenNhanVien(tenNhanVien);
            tienLuong.setNgayCong(ngayCong);
            tienLuong.setTongTienLuong(tongTienLuong);
            tienLuong.setSoTienThanhToan(soTienThanhToan);
            tienLuong.setTinhTrangLuong(tinhTrangLuong);
            // Gọi phương thức sửa tiền lương trong DAO
            model.suaTienLuong(tienLuong);
            JOptionPane.showMessageDialog(view, "Cập nhật tiền lương thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Định dạng không hợp lệ! Vui lòng kiểm tra lại.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật tiền lương: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public boolean xoaTienLuong(String maLuong) {
        if (maLuong == null || maLuong.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn tiền lương cần xóa!");
            return false;
        }
        boolean isDeleted = model.xoaTienLuong(maLuong);
        if (isDeleted) {
            JOptionPane.showMessageDialog(view, "Xóa tiền lương thành công!");
            return true;
        } else {
            JOptionPane.showMessageDialog(view, "Không tìm thấy bản ghi tiền lương!");
            return false;
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
            JOptionPane.showMessageDialog(null, "Lỗi kiểm tra tiền lương: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
