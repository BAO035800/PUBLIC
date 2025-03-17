package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    public void themNhanVien(NhanVien nhanvien){
        String sql = "INSERT INTO nhanvien(MaNhanVien,TenNhanVien,ChucVu,Luong1Gio,GioiTinh,SoDienThoai,Tuoi) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nhanvien.getMaNhanVien());
            ps.setString(2, nhanvien.getTenNhanVien());
            ps.setString(3, nhanvien.getChucVu()); 
            ps.setBigDecimal(4, nhanvien.getLuong1Gio());
            ps.setInt(7, nhanvien.getTuoi());
            ps.setString(5, nhanvien.getGioiTinh());
            ps.setString( 6, nhanvien.getSoDienThoai());
            ps.executeUpdate();
            System.out.println("Thêm nhân viên thành công!");
        } catch (Exception e) {
            System.out.println("Thêm nhân viên thất bại!");
            e.printStackTrace();
        }
    }
    public void suaNhanVien(NhanVien nhanvien) {
        String sql = "UPDATE nhanvien SET TenNhanVien = ?, ChucVu = ?, Luong1Gio = ?, GioiTinh = ?, SoDienThoai = ?, Tuoi = ? WHERE MaNhanVien = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
    
            ps.setString(1, nhanvien.getTenNhanVien());  // TenNhanVien
            ps.setString(2, nhanvien.getChucVu());       // ChucVu
            ps.setBigDecimal(3, nhanvien.getLuong1Gio());// Luong1Gio
            ps.setString(4, nhanvien.getGioiTinh());     // GioiTinh
            ps.setString(5, nhanvien.getSoDienThoai());  // SoDienThoai
            ps.setInt(6, nhanvien.getTuoi());            // Tuoi
            ps.setString(7, nhanvien.getMaNhanVien());   // WHERE MaNhanVien = ?
    
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Cập nhật nhân viên thành công!");
            } else {
                System.out.println("⚠️ Không tìm thấy nhân viên để cập nhật!");
            }
        } catch (Exception e) {
            System.out.println("❌ Cập nhật nhân viên thất bại!");
            e.printStackTrace();
        }
    }
    public boolean xoaNhanVien(String maNhanVien) {
        String sql = "DELETE FROM nhanvien WHERE MaNhanVien = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
    
            ps.setString(1, maNhanVien);
            int rowsDeleted = ps.executeUpdate();
    
            return rowsDeleted > 0; // Trả về true nếu có dòng bị xóa, false nếu không tìm thấy nhân viên
    
        } catch (Exception e) {
            System.out.println("❌ Xóa nhân viên thất bại!");
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }
    
    public List<NhanVien> getNhanVien(){
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                NhanVien nhanvien = new NhanVien();
                nhanvien.setMaNhanVien(rs.getString("MaNhanVien"));
                nhanvien.setTenNhanVien(rs.getString("TenNhanVien"));
                nhanvien.setChucVu(rs.getString("ChucVu"));
                nhanvien.setTuoi(rs.getInt("Tuoi"));
                nhanvien.setLuong1Gio(rs.getBigDecimal("Luong1Gio"));
                nhanvien.setGioiTinh(rs.getString("GioiTinh"));
                nhanvien.setSoDienThoai(rs.getString("SoDienThoai"));
                list.add(nhanvien);
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy danh sách nhân viên!");
            e.printStackTrace();
        }
        return list;
    }
}
