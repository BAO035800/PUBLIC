package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class HoaDonChiTietDAO {
    public void themHDChiTiet(HoaDonChiTiet hoadonchitiet){
        String sql="INSERT INTO hoadonbanhangchitiet (MaMon, MaHoaDonBanHang, SoBan, SoLuongDat) VALUES (?, ?, ?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoadonchitiet.getMaMon());
            ps.setString(2, hoadonchitiet.getMaHoaDonBanHang());
            ps.setInt(3, hoadonchitiet.getSoBan());
            ps.setInt(4, hoadonchitiet.getSoLuongDat());
            ps.executeUpdate();
            System.out.println("Thêm hóa đơn chi tiết thành công!");
        } catch (SQLException e) {
            System.out.println("Thêm thất bại: " + e.getMessage());
        }
    }

    public void suaHDChiTiet(HoaDonChiTiet hoadonchitiet){
        String sql="UPDATE hoadonbanhangchitiet SET MaMon=?, MaHoaDonBanHang=?, SoBan=?, SoLuongDat=? WHERE MaMon=? AND MaHoaDonBanHang=? AND SoBan=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoadonchitiet.getMaMon());
            ps.setString(2, hoadonchitiet.getMaHoaDonBanHang());
            ps.setInt(3, hoadonchitiet.getSoBan());
            ps.setInt(4, hoadonchitiet.getSoLuongDat());
            ps.setString(5, hoadonchitiet.getMaMon());
            ps.setString(6, hoadonchitiet.getMaHoaDonBanHang());
            ps.setInt(7, hoadonchitiet.getSoBan());
            ps.executeUpdate();
            System.out.println("Sửa hóa đơn chi tiết thành công!");
        } catch (SQLException e) {
            System.out.println("Sửa thất bại: " + e.getMessage());
        }
    }

    public void xoaHDChiTiet(String maMon, String maHoaDonBanHang, int soBan){
        String sql = "DELETE FROM hoadonbanhangchitiet WHERE MaMon = ? AND MaHoaDonBanHang = ? AND SoBan = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maMon);
            ps.setString(2, maHoaDonBanHang);
            ps.setInt(3, soBan);
            ps.executeUpdate();
            System.out.println("Xóa hóa đơn chi tiết thành công!");
        } catch (SQLException e) {
            System.out.println("Xóa thất bại: " + e.getMessage());
        }
    }

    public List<HoaDonChiTiet> layDanhSachHoaDonChiTiet(){
        List<HoaDonChiTiet> danhSachHoaDonChiTiet = new ArrayList<>();
        String sql = """
            SELECT cthd.MaMon, cthd.MaHoaDonBanHang, cthd.SoBan, cthd.SoLuongDat, 
                   m.GiaTien AS GiaTien, (m.GiaTien * cthd.SoLuongDat) AS TongTien
            FROM hoadonbanhangchitiet cthd
            JOIN menu m ON cthd.MaMon = m.MaMon
        """;
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonChiTiet hoadonchitiet = new HoaDonChiTiet();
                hoadonchitiet.setMaMon(rs.getString("MaMon"));
                hoadonchitiet.setMaHoaDonBanHang(rs.getString("MaHoaDonBanHang"));
                hoadonchitiet.setSoBan(rs.getInt("SoBan"));
                hoadonchitiet.setSoLuongDat(rs.getInt("SoLuongDat"));
                hoadonchitiet.setGiaTien(rs.getBigDecimal("GiaTien"));
                hoadonchitiet.setTongTien(rs.getBigDecimal("TongTien"));
                danhSachHoaDonChiTiet.add(hoadonchitiet);
            }
        } catch (SQLException e) {
            System.out.println("Lấy danh sách thất bại: " + e.getMessage());
        }
        return danhSachHoaDonChiTiet;
    }
}
