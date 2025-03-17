package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BanDAO {
    public void themBan(Ban ban){
        String sql="insert into ban(SoBan, TinhTrangBan, GhiChu) values(?,?,?)";
        try(Connection conn=Connect.getConnect();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1, ban.getSoBan());
            ps.setString(2, ban.getTrangThaiBan());
            ps.setString(3, ban.getGhiChu());
            System.out.println("Thêm bàn thành công");
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Thêm bàn thất bại");
            e.printStackTrace();
        }   
    }
    public void suaBan(Ban ban){
        String sql="update ban set TinhTrangBan=?, GhiChu=? where SoBan=?";
        try(Connection conn=Connect.getConnect();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, ban.getTrangThaiBan());
            ps.setString(2, ban.getGhiChu());
            ps.setInt(3, ban.getSoBan());
            System.out.println("Sửa bàn thành công");
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Sửa bàn thất bại");
            e.printStackTrace();
        }
    }
    public boolean xoaBan(int SoBan) {
        String sql = "DELETE FROM ban WHERE SoBan = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, SoBan);
            int rowsAffected = ps.executeUpdate(); // Kiểm tra số dòng bị ảnh hưởng
            
            if (rowsAffected > 0) {
                System.out.println("Xóa bàn thành công");
                return true; // Trả về true nếu có dòng bị xóa
            } else {
                System.out.println("Không tìm thấy bàn để xóa");
                return false; // Trả về false nếu không có dòng nào bị xóa
            }
            
        } catch (Exception e) {
            System.out.println("Xóa bàn thất bại");
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }
    
    public List<Ban> getBan(){
        List<Ban> list=new ArrayList<>();
        String sql="select * from ban";
        try(Connection conn=Connect.getConnect();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery()){
            while(rs.next()){
                Ban ban=new Ban();
                ban.setSoBan(rs.getInt("SoBan"));
                ban.setTrangThaiBan(rs.getString("TrangThaiBan"));
                ban.setGhiChu(rs.getString("GhiChu"));
                list.add(ban);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
