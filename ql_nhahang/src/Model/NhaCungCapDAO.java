package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {
    public void themNhaCungCap(NhaCungCap nhacungcap) {
        String sql = "INSERT INTO nhacungcap (MaNhaCungCap, TenNhaCungCap, LienHe) VALUES (?, ?, ?)";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nhacungcap.getMaNhaCungCap());
            ps.setString(2, nhacungcap.getTenNhaCungCap());
            ps.setString(3, nhacungcap.getLienHe());
            ps.executeUpdate();
            System.out.println("Thêm nhà cung cấp thành công!");
        } catch (SQLException e) {
            System.out.println("Thêm thất bại: " + e.getMessage());
        }
    }
    public void suaNhaCungCap(NhaCungCap nhacungcap) {
        String sql = "UPDATE nhacungcap SET TenNhaCungCap = ?, LienHe = ? WHERE MaNhaCungCap = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nhacungcap.getTenNhaCungCap());
            ps.setString(2, nhacungcap.getLienHe());
            ps.setString(3, nhacungcap.getMaNhaCungCap());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy nhà cung cấp!");
            }
        } catch (SQLException e) {
            System.out.println("Sửa thất bại: " + e.getMessage());
        }
    }
    public void xoaNhaCungCap(String maNhaCungCap) {
        String sql = "DELETE FROM nhacungcap WHERE MaNhaCungCap = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNhaCungCap);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Không tìm thấy nhà cung cấp!");
            }
        } catch (SQLException e) {
            System.out.println("Xóa thất bại: " + e.getMessage());
        }
    }
    public List<NhaCungCap> layDanhSachNhaCungCap() {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM nhacungcap";
        try (Connection conn = Connect.getConnect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                NhaCungCap nhacungcap = new NhaCungCap();
                nhacungcap.setMaNhaCungCap(rs.getString("MaNhaCungCap"));
                nhacungcap.setTenNhaCungCap(rs.getString("TenNhaCungCap"));
                nhacungcap.setLienHe(rs.getString("LienHe"));
                list.add(nhacungcap);
            }
        } catch (SQLException e) {
            System.out.println("Lấy danh sách thất bại: " + e.getMessage());
        }
        return list;
    }
}
