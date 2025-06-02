package controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Connect;
import model.Menu;
import model.MenuDAO;
import view.banhangMenu;
public class MenuController {
    private banhangMenu view;
    private MenuDAO model;
    private KTBanHang ktBanHang;
    public void setView(banhangMenu view) {
        this.view = view;
    }

    public void setModel(MenuDAO model) {
        this.model = model;
    }
    public MenuController(banhangMenu view,MenuDAO model){
        this.view = view;
        this.model = model;
        this.ktBanHang = new KTBanHang(); 
    }
    public void themMenu() {
        String maMon = view.getTxtMaMon().getText().trim();
        String tenMon = view.getTxtTenMon().getText().trim();
        String giaStr = view.getTxtGia().getText().trim();
        String tinhTrang = view.getCbTinhTrang().getSelectedItem().toString().trim();
        String soLuongStr = view.getTxtSoLuong().getText().trim();

        if (maMon.isEmpty() || tenMon.isEmpty() || giaStr.isEmpty() || tinhTrang.isEmpty() || soLuongStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            BigDecimal gia = new BigDecimal(giaStr);
            int soLuong = Integer.parseInt(soLuongStr);
            KTBanHang controller=new KTBanHang();
            if (controller.kiemTraMonTonTai(maMon)) {
                JOptionPane.showMessageDialog(view, "Món đã tồn tại trong hệ thống!");
                return;
            }

            Menu menu = new Menu();
            menu.setMaMon(maMon);
            menu.setTenMon(tenMon);
            menu.setGiaTien(gia);
            menu.setTinhTrangMon(tinhTrang);
            menu.setSoLuong(soLuong);

            model.themMenu(menu);
            JOptionPane.showMessageDialog(view, "Thêm món thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Giá và số lượng phải là số hợp lệ!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm món: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void suaMenu() {
        String maMon = view.getTxtMaMon().getText().trim();
        String tenMon = view.getTxtTenMon().getText().trim();
        String giaStr = view.getTxtGia().getText().trim();
        String tinhTrang = view.getCbTinhTrang().getSelectedItem().toString().trim();
        String soLuongStr = view.getTxtSoLuong().getText().trim();

        if (maMon.isEmpty() || tenMon.isEmpty() || giaStr.isEmpty() || tinhTrang.isEmpty() || soLuongStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            BigDecimal gia = new BigDecimal(giaStr);
            int soLuong = Integer.parseInt(soLuongStr);
            KTBanHang controller=new KTBanHang();
            if (!controller.kiemTraMonTonTai(maMon)) {
                JOptionPane.showMessageDialog(view, "Món không tồn tại!");
                return;
            }

            Menu menu = new Menu();
            menu.setMaMon(maMon);
            menu.setTenMon(tenMon);
            menu.setGiaTien(gia);
            menu.setTinhTrangMon(tinhTrang);
            menu.setSoLuong(soLuong);

            model.suaMenu(menu);
            JOptionPane.showMessageDialog(view, "Sửa món thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Giá và số lượng phải là số hợp lệ!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi sửa món: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void xoaMenu(String maMon) {
        // Kiểm tra xem món có tồn tại không
        if (!ktBanHang.kiemTraMonTonTai(maMon)) {
            JOptionPane.showMessageDialog(view, "⚠️ Món không tồn tại!");
            return;
        }
    
        // Kiểm tra và xóa các bản ghi trong hóa đơn chi tiết liên quan đến món này
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM hoadonbanhangchitiet WHERE MaMon = ?")) {
    
            // Xóa các chi tiết hóa đơn liên quan đến món này
            stmt1.setString(1, maMon);
            int rowsAffected1 = stmt1.executeUpdate();
            
            if (rowsAffected1 > 0) {
                JOptionPane.showMessageDialog(view, "Dữ liệu liên quan đến món sẽ bị xóa khỏi hóa đơn!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa dữ liệu hóa đơn: " + e.getMessage());
            e.printStackTrace();
            return;
        }
    
        // Xóa món từ database
        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa món này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
    
        try {
            model.xoaMenu(maMon); // Gọi phương thức model để xóa món từ database
            JOptionPane.showMessageDialog(view, "✅ Xóa món thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa món: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
