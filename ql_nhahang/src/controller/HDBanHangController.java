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
            JOptionPane.showMessageDialog(view, "Vui lòng chọn hóa đơn cần xóa!");
            return;
        }

        try {
            // Kiểm tra xem hóa đơn có tồn tại không
            KTBanHang controller = new KTBanHang();
            if (!controller.kiemTraMaHoaDon(maHD)) {
                JOptionPane.showMessageDialog(view, "Hóa đơn không tồn tại!");
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


                    // Xóa các bản ghi liên quan trong hoadonbanhangchitiet
                    String sqlDeleteChiTiet = "DELETE FROM hoadonbanhangchitiet WHERE MaHoaDonBanHang = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sqlDeleteChiTiet)) {
                        ps.setString(1, maHD);
                        ps.executeUpdate();
                    }

                    // Xóa hóa đơn
                    model.xoaHoaDon(maHD);

                    // Cập nhật trạng thái bàn về "Trống" cho tất cả SoBan
                    if (!soBanList.isEmpty()) {
                        String sqlUpdate = "UPDATE ban SET TinhTrangBan = ? WHERE SoBan = ?";
                        try (PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
                            for (int soBan : soBanList) {
                                ps.setString(1, "Trống");
                                ps.setInt(2, soBan);
                                ps.addBatch();
                            }
                            ps.executeBatch();
                        }
                    }

                    conn.commit();
                    JOptionPane.showMessageDialog(view, "Xóa hóa đơn thành công!");
                } catch (SQLException e) {
                    conn.rollback();
                    JOptionPane.showMessageDialog(view, "Lỗi khi xóa hóa đơn: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                    conn.setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}