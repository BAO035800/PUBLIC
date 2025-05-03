package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.HoaDonBanHang;
import model.HoaDonBanHangDAO;
import model.connectData;
import view.banhangHoaDonBanHang;

public class HDBanHangController {
    private banhangHoaDonBanHang view;
    private HoaDonBanHangDAO model;

    public HDBanHangController(banhangHoaDonBanHang view, HoaDonBanHangDAO model) {
        this.view = view;
        this.model = model;
    }

    public void themHDBanHang() {
        String maHD = view.getTxtMaHD().getText().trim();
        String trangThai = view.getCbTrangThai().getSelectedItem().toString().trim();

        if (maHD.isEmpty()) {
            JOptionPane.showMessageDialog(view, "⚠️ Vui lòng nhập mã hóa đơn!");
            return;
        }

        try {
            // Kiểm tra xem mã hóa đơn đã tồn tại chưa
            KTBanHang controller = new KTBanHang();
            if (controller.kiemTraMaHoaDon(maHD)) {
                JOptionPane.showMessageDialog(view, "❌ Mã hóa đơn đã tồn tại!");
                return;
            }

            // Thêm hóa đơn
            HoaDonBanHang hoaDon = new HoaDonBanHang();
            hoaDon.setMaHoaDonBanHang(maHD);
            hoaDon.setTinhTrang(trangThai);

            model.themHoaDon(hoaDon);

            JOptionPane.showMessageDialog(view, "✅ Thêm hóa đơn bán hàng thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void suaHDBanHang() {
        String maHD = view.getTxtMaHD().getText().trim();
        String trangThai = view.getCbTrangThai().getSelectedItem().toString().trim();

        if (maHD.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập mã hóa đơn!");
            return;
        }

        try {
            // Kiểm tra xem hóa đơn có tồn tại không
            KTBanHang controller = new KTBanHang();
            if (!controller.kiemTraMaHoaDon(maHD)) {
                JOptionPane.showMessageDialog(view, "❌ Mã hóa đơn không tồn tại!");
                return;
            }

            // Bắt đầu giao dịch
            try (Connection conn = connectData.connect()) {
                conn.setAutoCommit(false);
                try {
                    // Lấy tất cả SoBan từ hoadonbanhangchitiet
                    List<Integer> soBanList = new ArrayList<>();
                    String sqlSelect = "SELECT DISTINCT SoBan FROM hoadonbanhangchitiet WHERE MaHoaDonBanHang = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
                        ps.setString(1, maHD);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            soBanList.add(rs.getInt("SoBan"));
                        }
                    }

                    // Kiểm tra trạng thái "Chưa thanh toán" khi không có chi tiết hóa đơn
                    if (trangThai.equals("Chưa thanh toán") && soBanList.isEmpty()) {
                        JOptionPane.showMessageDialog(view, "⚠️ Hóa đơn chưa có chi tiết, không thể đặt trạng thái 'Chưa thanh toán'!");
                        conn.rollback();
                        return;
                    }

                    // Cập nhật hóa đơn
                    HoaDonBanHang hoaDon = new HoaDonBanHang();
                    hoaDon.setMaHoaDonBanHang(maHD);
                    hoaDon.setTinhTrang(trangThai);
                    model.suaHoaDon(hoaDon);

                    // Cập nhật trạng thái bàn cho tất cả SoBan
                    if (!soBanList.isEmpty()) {
                        String trangThaiBan;
                        if (trangThai.equals("Chưa thanh toán")) {
                            trangThaiBan = "Đang phục vụ";
                        } else {
                            // Bao gồm "Đã trả tiền" hoặc bất kỳ trạng thái nào khác
                            trangThaiBan = "Trống";
                        }
                        String sql = "UPDATE ban SET TinhTrangBan = ? WHERE SoBan = ?";
                        try (PreparedStatement ps = conn.prepareStatement(sql)) {
                            for (int soBan : soBanList) {
                                ps.setString(1, trangThaiBan);
                                ps.setInt(2, soBan);
                                ps.addBatch();
                            }
                            ps.executeBatch();
                        }
                    }

                    conn.commit();
                    JOptionPane.showMessageDialog(view, "✅ Cập nhật hóa đơn thành công!");
                } catch (SQLException e) {
                    conn.rollback();
                    JOptionPane.showMessageDialog(view, "❌ Lỗi khi cập nhật hóa đơn: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                    conn.setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "❌ Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void xoaHDBanHang(String maHD) {
        if (maHD == null || maHD.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn hóa đơn cần xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        int maxRetries = 3; // Số lần thử lại tối đa
        int attempt = 0;
        boolean success = false;
        String errorMessage = "";
    
        while (attempt < maxRetries && !success) {
            attempt++;
            try (Connection conn = connectData.connect()) {
                conn.setAutoCommit(false); // Bắt đầu giao dịch
                try {
                    // Kiểm tra xem hóa đơn có tồn tại không
                    KTBanHang controller = new KTBanHang();
                    if (!controller.kiemTraMaHoaDon(maHD)) {
                        JOptionPane.showMessageDialog(view, "Hóa đơn không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
    
                    // Lấy danh sách SoBan từ hoadonbanhangchitiet với khóa rõ ràng
                    List<Integer> soBanList = new ArrayList<>();
                    String sqlSelect = "SELECT DISTINCT SoBan FROM hoadonbanhangchitiet WHERE MaHoaDonBanHang = ? FOR UPDATE";
                    try (PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
                        ps.setString(1, maHD);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            soBanList.add(rs.getInt("SoBan"));
                        }
                    }
    
                    // Xóa các bản ghi trong chebienmonan
                    String sqlDeleteCheBien = "DELETE FROM chebienmonan WHERE MaHoaDonBanHang = ?";
                    int rowsAffectedCheBien = 0;
                    try (PreparedStatement ps = conn.prepareStatement(sqlDeleteCheBien)) {
                        ps.setString(1, maHD);
                        rowsAffectedCheBien = ps.executeUpdate();
                    }
    
                    // Xóa các bản ghi trong hoadonbanhangchitiet
                    String sqlDeleteChiTiet = "DELETE FROM hoadonbanhangchitiet WHERE MaHoaDonBanHang = ?";
                    int rowsAffectedChiTiet = 0;
                    try (PreparedStatement ps = conn.prepareStatement(sqlDeleteChiTiet)) {
                        ps.setString(1, maHD);
                        rowsAffectedChiTiet = ps.executeUpdate();
                    }
    
                    // Kiểm tra xem tất cả bản ghi chi tiết đã được xóa chưa
                    String sqlCheckChiTiet = "SELECT COUNT(*) FROM hoadonbanhangchitiet WHERE MaHoaDonBanHang = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sqlCheckChiTiet)) {
                        ps.setString(1, maHD);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next() && rs.getInt(1) > 0) {
                            throw new SQLException("Không thể xóa hết bản ghi từ hoadonbanhangchitiet. Còn lại " + rs.getInt(1) + " bản ghi.");
                        }
                    }
    
                    // Xóa hóa đơn từ hoadonbanhang
                    String sqlDeleteHoaDon = "DELETE FROM hoadonbanhang WHERE MaHoaDonBanHang = ?";
                    int rowsAffectedHoaDon = 0;
                    try (PreparedStatement ps = conn.prepareStatement(sqlDeleteHoaDon)) {
                        ps.setString(1, maHD);
                        rowsAffectedHoaDon = ps.executeUpdate();
                    }
    
                    // Kiểm tra xem hóa đơn đã được xóa chưa
                    if (rowsAffectedHoaDon == 0) {
                        throw new SQLException("Không thể xóa hóa đơn từ hoadonbanhang. Hóa đơn không tồn tại hoặc bị khóa.");
                    }
    
                    // Cập nhật trạng thái bàn về "Trống" chỉ khi cần thiết
                    if (!soBanList.isEmpty()) {
                        String sqlUpdate = "UPDATE ban SET TinhTrangBan = ? WHERE SoBan = ? AND TinhTrangBan != ?";
                        try (PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
                            for (int soBan : soBanList) {
                                ps.setString(1, "Trống");
                                ps.setInt(2, soBan);
                                ps.setString(3, "Trống"); // Chỉ cập nhật nếu trạng thái không phải "Trống"
                                ps.addBatch();
                            }
                            ps.executeBatch();
                        }
                    }
    
                    conn.commit(); // Hoàn tất giao dịch
                    success = true;
                    JOptionPane.showMessageDialog(view, "Xóa hóa đơn, chi tiết và chế biến món ăn thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    conn.rollback();
                    errorMessage = e.getMessage();
                    if (e.getMessage().contains("Lock wait timeout") && attempt < maxRetries) {
                        try {
                            Thread.sleep(1000 * attempt); // Đợi trước khi thử lại
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "Lỗi khi xóa hóa đơn: " + errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                        return;
                    }
                } finally {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                if (attempt == maxRetries) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi xóa hóa đơn sau " + maxRetries + " lần thử: " + errorMessage, 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Lỗi không xác định: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}