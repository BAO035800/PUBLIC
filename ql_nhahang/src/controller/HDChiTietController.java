package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.Connect;
import model.HoaDonChiTiet;
import model.HoaDonChiTietDAO;
import model.Menu;
import model.MenuDAO;
import view.banhangChiTiet;
public class HDChiTietController {
    private banhangChiTiet view;
    private HoaDonChiTietDAO model;
    private MenuDAO menuModel;
    private Menu menu;
    private KTBanHang ktBanHang;
    public HDChiTietController(banhangChiTiet view, HoaDonChiTietDAO model, Menu menu) {
        this.view = view;
        this.model = model;
        this.menu = menu;
        this.ktBanHang = new KTBanHang();
    }

    public void themHDChiTiet() {
        try {
            String maMon = view.getTxtMaMon().getText().trim();
            String maHD = view.getTxtMaHoaDonBanHang().getText().trim();
            String soBanStr = view.getTxtSoBan().getText().trim();
            String soLuongDatStr = view.getTxtSoLuongDat().getText().trim();
    
            if (maMon.isEmpty() || maHD.isEmpty() || soBanStr.isEmpty() || soLuongDatStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Kiểm tra mã món
            if (!ktBanHang.kiemTraMonTonTai(maMon)) {
                JOptionPane.showMessageDialog(view, "❌ Mã món không tồn tại trong thực đơn!");
                return;
            }
    
            // Kiểm tra số bàn
            if (!ktBanHang.kiemTraBanTonTai(soBanStr)) {
                JOptionPane.showMessageDialog(view, "❌ Số bàn không tồn tại trong danh sách bàn!");
                return;
            }
    
            // Kiểm tra mã hóa đơn
            if (!ktBanHang.kiemTraMaHoaDon(maHD)) {
                JOptionPane.showMessageDialog(view, "❌ Mã hóa đơn không tồn tại!");
                return;
            }
    
            int soBan = Integer.parseInt(soBanStr);
            int soLuongDat = Integer.parseInt(soLuongDatStr);
    
            // ✅ Cập nhật lại ID trước khi thêm
            capNhatLaiID();
            int idMoi = demSoLuongHDChiTiet() + 1;
    
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setID(idMoi);
            hdct.setMaMon(maMon);
            hdct.setMaHoaDonBanHang(maHD);
            hdct.setSoBan(soBan);
            hdct.setSoLuongDat(soLuongDat);
    
            model.themHDChiTiet(hdct);
            JOptionPane.showMessageDialog(view, "✅ Thêm hóa đơn chi tiết thành công!");
    
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "⚠️ Dữ liệu nhập không hợp lệ! Vui lòng nhập số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "❌ Lỗi khi thêm hóa đơn chi tiết: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public void suaHDChiTiet() {
        String idStr = view.getTxtID().getText();
        String maMon = view.getTxtMaMon().getText().trim();
        String maHD = view.getTxtMaHoaDonBanHang().getText().trim();
        String soBanStr = view.getTxtSoBan().getText().trim();
        String soLuongDatStr = view.getTxtSoLuongDat().getText().trim();
    
        if (idStr.isEmpty() || maMon.isEmpty() || maHD.isEmpty() || soBanStr.isEmpty() || soLuongDatStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
    
        try {
            int id = Integer.parseInt(idStr);
            int soBan = Integer.parseInt(soBanStr);
            int soLuongDat = Integer.parseInt(soLuongDatStr);
    
            // Kiểm tra mã món
            if (!ktBanHang.kiemTraMonTonTai(maMon)) {
                JOptionPane.showMessageDialog(view, "❌ Mã món không tồn tại trong thực đơn!");
                return;
            }
    
            // Kiểm tra số bàn
            if (!ktBanHang.kiemTraBanTonTai(soBanStr)) {
                JOptionPane.showMessageDialog(view, "❌ Số bàn không tồn tại trong danh sách bàn!");
                return;
            }
    
            // Kiểm tra mã hóa đơn
            if (!ktBanHang.kiemTraMaHoaDon(maHD)) {
                JOptionPane.showMessageDialog(view, "❌ Mã hóa đơn không tồn tại!");
                return;
            }
    
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setID(id);
            hdct.setMaMon(maMon);
            hdct.setMaHoaDonBanHang(maHD);
            hdct.setSoBan(soBan);
            hdct.setSoLuongDat(soLuongDat);
    
            model.suaHDChiTiet(hdct);
    
            // ✅ Cập nhật lại ID sau khi sửa
            capNhatLaiID();
    
            JOptionPane.showMessageDialog(view, "✅ Sửa hóa đơn chi tiết thành công!");
    
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "⚠️ Định dạng không hợp lệ! Vui lòng nhập số nguyên.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "❌ Lỗi khi sửa hóa đơn chi tiết: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void xoaHDChiTiet() {
        String idStr = view.getTxtID().getText();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập ID hóa đơn chi tiết cần xóa!");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            model.xoaHDChiTiet(id);
            capNhatLaiID(); // Gọi hàm cập nhật lại ID sau khi xóa
            JOptionPane.showMessageDialog(view, "Xóa hóa đơn chi tiết thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "ID phải là số nguyên hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa hóa đơn chi tiết: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public int demSoLuongHDChiTiet() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM hoadonbanhangchitiet";

        try (Connection conn = Connect.getConnect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void capNhatLaiID() {
        String sql1 = "SET @new_id = 0;";
        String sql2 = "UPDATE hoadonbanhangchitiet SET ID = (@new_id := @new_id + 1) ORDER BY ID;";
        String sql3 = "ALTER TABLE hoadonbanhangchitiet AUTO_INCREMENT = 1;";

        try (Connection conn = Connect.getConnect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
