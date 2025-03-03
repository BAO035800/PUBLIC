package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class HoaDonNhapDAO {
    public void themHoaDonNhap(HoaDonNhap hoadonnhap) {
        String sql = "INSERT INTO hoadonnhap (MaHoaDonKho, MaNguyenLieu, MaNhaCungCap, SoLuong, NgayNhap, TongTienNhap) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoadonnhap.getMaHoaDonKho());
            ps.setString(2, hoadonnhap.getMaNguyenLieu());
            ps.setString(3, hoadonnhap.getMaNhaCungCap());
            ps.setInt(4, hoadonnhap.getSoLuong());
            ps.setDate(5, new java.sql.Date(hoadonnhap.getNgayNhap().getTime()));
            ps.setBigDecimal(6, hoadonnhap.getTongTienNhap());
            ps.executeUpdate();
            System.out.println("Thêm hóa đơn nhập thành công!");
        } catch (SQLException e) {
            System.out.println("Thêm thất bại: " + e.getMessage());
        }
    }
    public void suaHoaDonNhap(HoaDonNhap hoadonnhap) {
        String sql = "UPDATE hoadonnhap SET MaNguyenLieu = ?, MaNhaCungCap = ?, SoLuong = ?, NgayNhap = ?, TongTienNhap = ? WHERE MaHoaDonKho = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoadonnhap.getMaNguyenLieu());
            ps.setString(2, hoadonnhap.getMaNhaCungCap());
            ps.setInt(3, hoadonnhap.getSoLuong());
            ps.setDate(4, new java.sql.Date(hoadonnhap.getNgayNhap().getTime()));
            ps.setBigDecimal(5, hoadonnhap.getTongTienNhap());
            ps.setString(6, hoadonnhap.getMaHoaDonKho());
            ps.executeUpdate();
            System.out.println("Sửa hóa đơn nhập thành công!");
        } catch (SQLException e) {
            System.out.println("Sửa thất bại: " + e.getMessage());
        }
    }
    public void xoaHoaDonNhap(String MaHoaDonKho) {
        String sql = "DELETE FROM hoadonnhap WHERE MaHoaDonKho = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, MaHoaDonKho);
            ps.executeUpdate();
            System.out.println("Xóa hóa đơn nhập thành công!");
        } catch (SQLException e) {
            System.out.println("Xóa thất bại: " + e.getMessage());
        }
    }
    public List<HoaDonNhap> layDanhSachHoaDonNhap() {
        List<HoaDonNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM hoadonnhap";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonNhap hoadonnhap = new HoaDonNhap();
                hoadonnhap.setMaHoaDonKho(rs.getString("MaHoaDonKho"));
                hoadonnhap.setMaNguyenLieu(rs.getString("MaNguyenLieu"));
                hoadonnhap.setMaNhaCungCap(rs.getString("MaNhaCungCap"));
                hoadonnhap.setSoLuong(rs.getInt("SoLuong"));
                hoadonnhap.setNgayNhap(rs.getDate("NgayNhap"));
                hoadonnhap.setTongTienNhap(rs.getBigDecimal("TongTienNhap"));
                list.add(hoadonnhap);
            }
        } catch (SQLException e) {
            System.out.println("Lấy danh sách thất bại: " + e.getMessage());
        }
        return list;
    }
}
