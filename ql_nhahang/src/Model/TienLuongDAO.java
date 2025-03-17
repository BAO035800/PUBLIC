package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class TienLuongDAO {
    public void themTienLuong(TienLuong tienluong){
        String sql = "INSERT INTO tienluong(MaLuong,MaNhanVien,TenNhanVien,TongTienLuong,TinhTrangLuong,NgayCong,SoTienThanhToan) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tienluong.getMaLuong());
            ps.setString(2, tienluong.getMaNhanVien());
            ps.setString(3, tienluong.getTenNhanVien()); 
            ps.setBigDecimal(4,tienluong.getTongTienLuong()); 
            ps.setString(5, tienluong.getTinhTrangLuong());
            ps.setInt(6, tienluong.getNgayCong());
            ps.setBigDecimal(7, tienluong.getSoTienThanhToan());
            ps.executeUpdate();
            System.out.println("Thêm nhân viên thành công!");
        } catch (Exception e) {
            System.out.println("Thêm nhân viênviên thất bại!");
            e.printStackTrace();
        }
    }
    public void suaTienLuong(TienLuong tienluong) {
        String sql = "UPDATE tienluong SET MaNhanVien = ?, TenNhanVien = ?, TongTienLuong = ?, TinhTrangLuong = ?, NgayCong = ?, SoTienThanhToan = ? WHERE MaLuong = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tienluong.getMaNhanVien());
            ps.setString(2, tienluong.getTenNhanVien());
            ps.setBigDecimal(3, tienluong.getTongTienLuong());
            ps.setString(4, tienluong.getTinhTrangLuong());
            ps.setInt(5, tienluong.getNgayCong());
            ps.setBigDecimal(6, tienluong.getSoTienThanhToan());
            ps.setString(7, tienluong.getMaLuong());
            
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thông tin lương thành công!");
            } else {
                System.out.println("Không tìm thấy bản ghi để cập nhật!");
            }
        } catch (Exception e) {
            System.out.println("Cập nhật thông tin lương thất bại!");
            e.printStackTrace();
        }
    }
    
    public boolean xoaTienLuong(String maLuong) {    
    String sql = "DELETE FROM tienluong WHERE MaLuong = ?";
    try (Connection conn = Connect.getConnect();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, maLuong);

        // Thực hiện câu lệnh xóa
        int rowsDeleted = ps.executeUpdate();
        if (rowsDeleted > 0) {
            JOptionPane.showMessageDialog(null, "Xóa thông tin tiền lương thành công!");
            return true;
        } else {
            System.out.println("Không tìm thấy bản ghi để xóa!");
            return false;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Xóa thông tin tiền lương thất bại! Lỗi: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

    public List<TienLuong> getTienLuong() {
        List<TienLuong> list = new ArrayList<>();
        String sql = "SELECT * FROM tienluong";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TienLuong tienluong = new TienLuong();
                tienluong.setMaLuong(rs.getString("MaLuong"));
                tienluong.setMaNhanVien(rs.getString("MaNhanVien"));
                tienluong.setTenNhanVien(rs.getString("TenNhanVien"));
                tienluong.setTongTienLuong(rs.getBigDecimal("TongTienLuong"));
                tienluong.setTinhTrangLuong(rs.getString("TinhTrangLuong"));
                tienluong.setNgayCong(rs.getInt("NgayCong"));
                tienluong.setSoTienThanhToan(rs.getBigDecimal("SoTienThanhToan"));
                list.add(tienluong);
            }
        } catch (Exception e) {
            System.out.println("Lấy danh sách lương thất bại!");
            e.printStackTrace();
        }
        return list;
    }    
}
