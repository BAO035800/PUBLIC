package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    public void themNhanVien(NhanVien nhanvien){
        String sql = "INSERT INTO nhanvien(MaNhanVien,TenNhanVien,ChucVu,NgaySinh,Luong1Gio,GioiTinh,SoDienThoaiSoDienThoai) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nhanvien.getMaNhanVien());
            ps.setString(2, nhanvien.getTenNhanVien());
            ps.setString(3, nhanvien.getChucVu()); 
            ps.setDate(3, new java.sql.Date(nhanvien.getNgaySinh().getTime()));
            ps.setBigDecimal(5, nhanvien.getLuong1Gio());
            ps.setString(6, nhanvien.getGioiTinh());
            ps.setBigDecimal(7, nhanvien.getSoDienThoai());
            ps.executeUpdate();
            System.out.println("Thêm nhân viên thành công!");
        } catch (Exception e) {
            System.out.println("Thêm nhân viênviên thất bại!");
            e.printStackTrace();
        }
    }
    public void suaNhanVien(NhanVien nhanvien){
        String sql = "UPDATE nhanvien SET TenNhanVien = ?, ChucVu = ?, NgaySinh = ?, Luong1Gio = ?, GioiTinh = ?, SoDienThoai = ? WHERE MaNhanVien = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nhanvien.getTenNhanVien());
            ps.setString(2, nhanvien.getChucVu());
            ps.setDate(3, new java.sql.Date(nhanvien.getNgaySinh().getTime()));
            ps.setBigDecimal(4, nhanvien.getLuong1Gio());
            ps.setString(5, nhanvien.getGioiTinh());
            ps.setBigDecimal(6, nhanvien.getSoDienThoai());
            ps.setString(7, nhanvien.getMaNhanVien());
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
    public void xoaNhanVien(String maNhanVien){
        String sql = "DELETE FROM nhanvien WHERE MaNhanVien = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNhanVien);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Xóa nhân viên thành công!");
            } else {
                System.out.println("⚠️ Không tìm thấy nhân viên để xóa!");
            }
        } catch (Exception e) {
            System.out.println("❌ Xóa nhân viên thất bại!");
            e.printStackTrace();
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
                nhanvien.setNgaySinh(rs.getDate("NgaySinh"));
                nhanvien.setLuong1Gio(rs.getBigDecimal("Luong1Gio"));
                nhanvien.setGioiTinh(rs.getString("GioiTinh"));
                nhanvien.setSoDienThoai(rs.getBigDecimal("SoDienThoai"));
                list.add(nhanvien);
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy danh sách nhân viên!");
            e.printStackTrace();
        }
        return list;
    }
}
