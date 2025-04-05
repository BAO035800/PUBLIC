package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Ban;
import model.BanDAO;
import model.Connect;
import view.banhangBan;

public class BanController {
    private banhangBan view;
    private BanDAO model;
    private KTBanHang ktBanHang; // Thêm kiểm tra bàn

    public BanController(banhangBan view, BanDAO model) {
        this.view = view;
        this.model = model;
        this.ktBanHang = new KTBanHang(); // Khởi tạo kiểm tra bàn
    }

    public void themBan() {
        String soBan = view.getTxtBan().getText();
        String tinhTrang = view.getCbTinhTrang().getSelectedItem().toString();
        String ghiChu = view.getTxtGhiChu().getText();
        
        if (soBan.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập số bàn!");
            return;
        }
        
        try {
            int soBanInt = Integer.parseInt(soBan);
            // Kiểm tra bàn đã tồn tại chưa
            if (ktBanHang.kiemTraBanTonTai(soBan)) {
                JOptionPane.showMessageDialog(view, "Bàn đã tồn tại!");
                return;
            }
            
            // Kiểm tra giá trị hợp lệ của `TinhTrangBan`
            if (!tinhTrang.equals("Trống") && !tinhTrang.equals("Đã đặt") && !tinhTrang.equals("Đang phục vụ")) {
                JOptionPane.showMessageDialog(view, "Tình trạng bàn không hợp lệ!");
                return;
            }

            Ban ban = new Ban();
            ban.setSoBan(soBanInt);
            ban.setTrangThaiBan(tinhTrang);
            ban.setGhiChu(ghiChu);
            model.themBan(ban);

            JOptionPane.showMessageDialog(view, "Thêm bàn thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Số bàn phải là số nguyên!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm bàn: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void suaBan() {
        String soBan = view.getTxtBan().getText();
        String tinhTrang = view.getCbTinhTrang().getSelectedItem().toString();
        String ghiChu = view.getTxtGhiChu().getText();

        if (soBan.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập số bàn!");
            return;
        }
        
        try {
            int soBanInt = Integer.parseInt(soBan);
            if (!ktBanHang.kiemTraBanTonTai(soBan)) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy bàn!");
                return;
            }

            Ban ban = new Ban(soBanInt, tinhTrang, ghiChu);
            model.suaBan(ban);
            JOptionPane.showMessageDialog(view, "Sửa bàn thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Số bàn phải là số nguyên!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi sửa bàn: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void xoaBan(int soBan) {
    if (!ktBanHang.kiemTraBanTonTai(String.valueOf(soBan))) {
        JOptionPane.showMessageDialog(view, "⚠️ Không tìm thấy bàn!");
        return;
    }

    // Kiểm tra và xóa các bản ghi trong hóa đơn chi tiết liên quan đến bàn này
    try (Connection conn = Connect.getConnect();
         PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM hoadonbanhangchitiet WHERE SoBan = ?");
         PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM hoadonbanhang WHERE SoBan = ?")) {

        // Xóa các chi tiết hóa đơn liên quan đến bàn này
        stmt1.setInt(1, soBan);
        int rowsAffected1 = stmt1.executeUpdate();
        
        // Xóa hóa đơn bán hàng liên quan đến bàn này
        stmt2.setInt(1, soBan);
        int rowsAffected2 = stmt2.executeUpdate();
        
        if (rowsAffected1 > 0 || rowsAffected2 > 0) {
            JOptionPane.showMessageDialog(view, "Dữ liệu liên quan đến bàn sẽ bị xóa khỏi hóa đơn!");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(view, "Lỗi khi xóa dữ liệu hóa đơn: " + e.getMessage());
        e.printStackTrace();
        return;
    }

    // Xóa bàn
    int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa bàn này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    try {
        model.xoaBan(soBan); // Xóa bàn từ database
        JOptionPane.showMessageDialog(view, "✅ Xóa bàn thành công!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(view, "Lỗi khi xóa bàn: " + e.getMessage());
        e.printStackTrace();
    }
}

    
}
