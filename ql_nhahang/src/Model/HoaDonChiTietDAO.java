package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class HoaDonChiTietDAO {
    public void themHDChiTiet(HoaDonChiTiet hoadonchitiet){
        String sql="INSERT INTO hoadonbanhangchitiet (ID,MaMon,MaHoaDonBanHang,SoBan,GiaTien,SoLuongDat,TongTien) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,hoadonchitiet.getID());
            ps.setString(2,hoadonchitiet.getMaMon());
            ps.setString(3, hoadonchitiet.getMaHoaDonBanHang());
            ps.setInt(4,hoadonchitiet.getSoBan());
            ps.setBigDecimal(5,hoadonchitiet.getGiaTien());
            ps.setInt(6,hoadonchitiet.getSoLuongDat());
            ps.setBigDecimal(7,hoadonchitiet.getTongTien());
            ps.executeUpdate();
            System.out.println("Thêm hóa đơn chi tiết thành công!");
        } catch (SQLException e) {
            System.out.println("Thêm thất bại: " + e.getMessage());
        }
    }
    public void suaHDChiTiet(HoaDonChiTiet hoadonchitiet){
        String sql="UPDATE hoadonbanhangchitiet SET MaMon=?, MaHoaDonBanHang=?, SoBan=?, GiaTien=?, SoLuongDat=?, TongTien=? WHERE ID=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hoadonchitiet.getMaMon());
            ps.setString(2, hoadonchitiet.getMaHoaDonBanHang());
            ps.setInt(3, hoadonchitiet.getSoBan());
            ps.setBigDecimal(4, hoadonchitiet.getGiaTien());
            ps.setInt(5, hoadonchitiet.getSoLuongDat());
            ps.setBigDecimal(6, hoadonchitiet.getTongTien());
            ps.setInt(7, hoadonchitiet.getID());
            ps.executeUpdate();
            System.out.println("Sửa hóa đơn chi tiết thành công!");
        } catch (SQLException e) {
            System.out.println("Sửa thất bại: " + e.getMessage());
        }
    }
    public void xoaHDChiTiet(String ID){
        String sql = "DELETE FROM hoadonbanhangchitiet WHERE ID = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ID);
            ps.executeUpdate();
            System.out.println("Xóa hóa đơn chi tiết thành công!");
        } catch (SQLException e) {
            System.out.println("Xóa thất bại: " + e.getMessage());
        }
    }
    public List<HoaDonChiTiet> layDanhSachHoaDonChiTiet(){
        List<HoaDonChiTiet> danhSachHoaDonChiTiet = new ArrayList<>();
        String sql = "SELECT * FROM hoadonbanhangchitiet";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
            HoaDonChiTiet hoadonchitiet = new HoaDonChiTiet();
            hoadonchitiet.setID(rs.getInt("ID"));
            hoadonchitiet.setMaMon(rs.getString("MaMon"));
            hoadonchitiet.setMaHoaDonBanHang(rs.getString("MaHoaDonBanHang"));
            hoadonchitiet.setSoBan(rs.getInt("SoBan"));
            hoadonchitiet.setGiaTien(rs.getBigDecimal("GiaTien"));
            hoadonchitiet.setSoLuongDat(rs.getInt("SoLuongDat"));
            hoadonchitiet.setTongTien(rs.getBigDecimal("TongTien"));
            danhSachHoaDonChiTiet.add(hoadonchitiet);
            }
        } catch (SQLException e) {
            System.out.println("Lấy danh sách thất bại: " + e.getMessage());
        }
        return danhSachHoaDonChiTiet;
    }
}
