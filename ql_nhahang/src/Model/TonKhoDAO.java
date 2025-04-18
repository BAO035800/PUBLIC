package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TonKhoDAO {
    // Thêm nguyên liệu vào tồn kho
    public void themTonKho(TonKho tonKho) {
        String sql = "INSERT INTO tonkho(MaNguyenLieu, SoLuongTon, NgayCapNhat) VALUES(?, ?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tonKho.getMaNguyenLieu());
            ps.setInt(2, tonKho.getSoLuongTon());
            ps.setDate(3, new java.sql.Date(tonKho.getNgayCapNhat().getTime()));
            ps.executeUpdate();
            System.out.println("✅ Thêm tồn kho thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Thêm tồn kho thất bại!");
            e.printStackTrace();
        }
    }

    // Sửa thông tin tồn kho
    public void suaTonKho(TonKho tonKho) {
        String sql = "UPDATE tonkho SET SoLuongTon=?, NgayCapNhat=? WHERE MaNguyenLieu=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tonKho.getSoLuongTon());
            ps.setDate(2, new java.sql.Date(tonKho.getNgayCapNhat().getTime()));
            ps.setString(3, tonKho.getMaNguyenLieu());
            ps.executeUpdate();
            System.out.println("✅ Cập nhật tồn kho thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Cập nhật tồn kho thất bại!");
            e.printStackTrace();
        }
    }

    // Xóa nguyên liệu khỏi tồn kho
    public void xoaTonKho(String maNguyenLieu) {
        String sql = "DELETE FROM tonkho WHERE MaNguyenLieu=?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNguyenLieu);
            ps.executeUpdate();
            System.out.println("✅ Xóa tồn kho thành công!");
        } catch (SQLException e) {
            System.out.println("❌ Xóa tồn kho thất bại!");
            e.printStackTrace();
        }
    }

    // Lấy danh sách tồn kho
    public List<TonKho> getDanhSachTonKho() {
        List<TonKho> list = new ArrayList<>();
        String sql = "SELECT * FROM tonkho";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TonKho tk = new TonKho();
                tk.setMaNguyenLieu(rs.getString("MaNguyenLieu"));
                tk.setSoLuongTon(rs.getInt("SoLuongTon"));
                tk.setNgayCapNhat(rs.getDate("NgayCapNhat"));
                list.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
