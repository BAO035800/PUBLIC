package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Ban;
import model.BanDAO;
import model.Connect;
import view.banhangBan;

public class BanController {
    private banhangBan view;
    private BanDAO model;
    private KTBanHang ktBanHang;

    public BanController(banhangBan view, BanDAO model) {
        this.view = view;
        this.model = model;
        this.ktBanHang = new KTBanHang();
    }

    public void themBan() {
        String soBan = view.getTxtBan().getText().trim();
        String tinhTrang = view.getCbTinhTrang().getSelectedItem().toString().trim();
        String ghiChu = view.getTxtGhiChu().getText().trim();

        if (soBan.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập số bàn!");
            return;
        }

        try {
            int soBanInt = Integer.parseInt(soBan);
            if (ktBanHang.kiemTraBanTonTai(soBan)) {
                JOptionPane.showMessageDialog(view, "Bàn đã tồn tại!");
                return;
            }

            // Kiểm tra trạng thái bàn hợp lệ
            if (!tinhTrang.equals("Trống") && !tinhTrang.equals("Đang phục vụ")) {
                JOptionPane.showMessageDialog(view, "Tình trạng bàn không hợp lệ!");
                return;
            }

            // Kiểm tra nếu bàn không có trong hóa đơn chi tiết, trạng thái phải là "Trống"
            if (!kiemTraSoBanTrongHoaDon(soBan) && !tinhTrang.equals("Trống")) {
                JOptionPane.showMessageDialog(view, "⚠️ Bàn này chưa được sử dụng trong hóa đơn, chỉ có thể đặt trạng thái 'Trống'!");
                return;
            }

            Ban ban = new Ban();
            ban.setSoBan(soBanInt);
            ban.setTrangThaiBan(tinhTrang);
            ban.setGhiChu(ghiChu);
            model.themBan(ban);

            JOptionPane.showMessageDialog(view, "✅ Thêm bàn thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Số bàn phải là số nguyên!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm bàn: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void suaBan() {
        String soBan = view.getTxtBan().getText().trim();
        String tinhTrang = view.getCbTinhTrang().getSelectedItem().toString().trim();
        String ghiChu = view.getTxtGhiChu().getText().trim();

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

            // Kiểm tra trạng thái bàn hợp lệ
            if (!tinhTrang.equals("Trống") && !tinhTrang.equals("Đang phục vụ")) {
                JOptionPane.showMessageDialog(view, "Tình trạng bàn không hợp lệ!");
                return;
            }

            // Kiểm tra nếu bàn không có trong hóa đơn chi tiết, trạng thái phải là "Trống"
            if (!kiemTraSoBanTrongHoaDon(soBan) && !tinhTrang.equals("Trống")) {
                JOptionPane.showMessageDialog(view, "⚠️ Bàn này chưa được sử dụng trong hóa đơn, chỉ có thể đặt trạng thái 'Trống'!");
                return;
            }

            // Nếu trạng thái là "Đang phục vụ", kiểm tra xem có hóa đơn "Chưa thanh toán" không
            if (tinhTrang.equals("Đang phục vụ")) {
                String sql = "SELECT COUNT(*) FROM hoadonbanhang h " +
                            "JOIN hoadonbanhangchitiet c ON h.MaHoaDonBanHang = c.MaHoaDonBanHang " +
                            "WHERE c.SoBan = ? AND h.TinhTrang = 'Chưa thanh toán'";
                try (Connection conn = Connect.getConnect();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, soBan);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next() && rs.getInt(1) == 0) {
                        JOptionPane.showMessageDialog(view, "⚠️ Bàn này không có hóa đơn 'Chưa thanh toán', không thể đặt trạng thái 'Đang phục vụ'!");
                        return;
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi kiểm tra hóa đơn: " + e.getMessage());
                    e.printStackTrace();
                    return;
                }
            }

            Ban ban = new Ban(soBanInt, tinhTrang, ghiChu);
            model.suaBan(ban);
            JOptionPane.showMessageDialog(view, "✅ Sửa bàn thành công!");
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

        // Kiểm tra bàn có trong hóa đơn chi tiết và có hóa đơn "Chưa thanh toán" không
        String sql = "SELECT COUNT(*) FROM hoadonbanhang h " +
                    "JOIN hoadonbanhangchitiet c ON h.MaHoaDonBanHang = c.MaHoaDonBanHang " +
                    "WHERE c.SoBan = ? AND h.TinhTrang = 'Chưa thanh toán'";
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, soBan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                int confirmUpdate = JOptionPane.showConfirmDialog(view,
                    "Bàn này đang có hóa đơn 'Chưa thanh toán'. Bạn có muốn chuyển bàn sang trạng thái 'Trống' không?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirmUpdate == JOptionPane.YES_OPTION) {
                    // Cập nhật trạng thái bàn thành 'Trống'
                    try (PreparedStatement updateStmt = conn.prepareStatement(
                            "UPDATE ban SET TinhTrangBan = 'Trống' WHERE SoBan = ?")) {
                        updateStmt.setInt(1, soBan);
                        updateStmt.executeUpdate();
                        JOptionPane.showMessageDialog(view, "✅ Đã chuyển bàn sang trạng thái 'Trống'!");
                    }
                }
                return; // Thoát nếu bàn có trong hóa đơn, không xóa
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi kiểm tra hóa đơn: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Nếu bàn không có trong hóa đơn chi tiết hoặc không có hóa đơn "Chưa thanh toán", cho phép xóa
        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa bàn này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            model.xoaBan(soBan);
            JOptionPane.showMessageDialog(view, "✅ Xóa bàn thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa bàn: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Hàm kiểm tra số bàn có tồn tại trong hóa đơn chi tiết hay không
    private boolean kiemTraSoBanTrongHoaDon(String soBan) {
        String sql = "SELECT COUNT(*) FROM hoadonbanhangchitiet WHERE SoBan = ?";
        try (Connection conn = Connect.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, soBan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "❌ Lỗi kiểm tra số bàn trong hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}