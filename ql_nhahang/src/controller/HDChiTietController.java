package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        this.menuModel = new MenuDAO();
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

            // Bắt đầu giao dịch
            try (Connection conn = Connect.getConnect()) {
                conn.setAutoCommit(false);
                try {
                    // Kiểm tra trạng thái hóa đơn
                    String sqlCheckHoaDon = "SELECT TrangThai FROM hoadonbanhang WHERE MaHoaDonBanHang = ?";
                    String trangThai = "";
                    try (PreparedStatement stmt = conn.prepareStatement(sqlCheckHoaDon)) {
                        stmt.setString(1, maHD);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            trangThai = rs.getString("TrangThai");
                        } else {
                            throw new SQLException("Hóa đơn không tồn tại!");
                        }
                    }

                    // Kiểm tra số lượng món còn lại
                    String sqlCheckMon = "SELECT SoLuong, TinhTrangMon FROM menu WHERE MaMon = ?";
                    int soLuongHienTai = 0;
                    String tinhTrangMon = "";
                    try (PreparedStatement stmt = conn.prepareStatement(sqlCheckMon)) {
                        stmt.setString(1, maMon);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            soLuongHienTai = rs.getInt("SoLuong");
                            tinhTrangMon = rs.getString("TinhTrangMon");
                        } else {
                            throw new SQLException("Món không tồn tại!");
                        }
                    }

                    if (tinhTrangMon.equals("Hết")) {
                        JOptionPane.showMessageDialog(view, "❌ Món " + maMon + " đã hết hàng!");
                        return;
                    }

                    if (soLuongHienTai < soLuongDat) {
                        JOptionPane.showMessageDialog(view, "❌ Số lượng đặt vượt quá số lượng hiện có (" + soLuongHienTai + ")!");
                        return;
                    }

                    // Thêm bản ghi vào hoadonbanhangchitiet
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setMaMon(maMon);
                    hdct.setMaHoaDonBanHang(maHD);
                    hdct.setSoBan(soBan);
                    hdct.setSoLuongDat(soLuongDat);

                    model.themHDChiTiet(hdct);

                    // Cập nhật số lượng món trong menu
                    String sqlUpdateMenu = "UPDATE menu SET SoLuong = SoLuong - ?, TinhTrangMon = IF(SoLuong - ? = 0, 'Hết', TinhTrangMon) WHERE MaMon = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sqlUpdateMenu)) {
                        stmt.setInt(1, soLuongDat);
                        stmt.setInt(2, soLuongDat);
                        stmt.setString(3, maMon);
                        stmt.executeUpdate();
                    }

                    // Cập nhật trạng thái bàn nếu hóa đơn là "Chưa thanh toán"
                    if (trangThai.equals("Chưa thanh toán")) {
                        String sqlUpdateBan = "UPDATE ban SET TinhTrangBan = 'Đang phục vụ' WHERE SoBan = ?";
                        try (PreparedStatement stmt = conn.prepareStatement(sqlUpdateBan)) {
                            stmt.setInt(1, soBan);
                            stmt.executeUpdate();
                        }
                    }

                    // Kiểm tra món có hết hàng không
                    if (soLuongHienTai - soLuongDat == 0) {
                        JOptionPane.showMessageDialog(view, "Món " + maMon + " đã hết hàng!");
                    }

                    conn.commit();
                    JOptionPane.showMessageDialog(view, "✅ Thêm hóa đơn chi tiết thành công!");
                } catch (SQLException e) {
                    conn.rollback();
                    JOptionPane.showMessageDialog(view, "❌ Lỗi khi thêm hóa đơn chi tiết: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } finally {
                    conn.setAutoCommit(true);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "⚠️ Dữ liệu nhập không hợp lệ! Vui lòng nhập số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "❌ Lỗi kết nối cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void suaHDChiTiet() {
        try {
            String maMon = view.getTxtMaMon().getText().trim();
            String maHD = view.getTxtMaHoaDonBanHang().getText().trim();
            String soBanStr = view.getTxtSoBan().getText().trim();
            String soLuongDatStr = view.getTxtSoLuongDat().getText().trim();

            if (maMon.isEmpty() || maHD.isEmpty() || soBanStr.isEmpty() || soLuongDatStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

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

            // Bắt đầu giao dịch
            try (Connection conn = Connect.getConnect()) {
                conn.setAutoCommit(false);
                try {
                    // Kiểm tra trạng thái hóa đơn
                    String sqlCheckHoaDon = "SELECT TinhTrang FROM hoadonbanhang WHERE MaHoaDonBanHang = ?";
                    String trangThai = "";
                    try (PreparedStatement stmt = conn.prepareStatement(sqlCheckHoaDon)) {
                        stmt.setString(1, maHD);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            trangThai = rs.getString("TinhTrang");
                        } else {
                            throw new SQLException("Hóa đơn không tồn tại!");
                        }
                    }

                    // Lấy số lượng đặt cũ
                    int soLuongDatCu = 0;
                    String sqlSelectOld = "SELECT SoLuongDat FROM hoadonbanhangchitiet WHERE MaMon = ? AND MaHoaDonBanHang = ? AND SoBan = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sqlSelectOld)) {
                        stmt.setString(1, maMon);
                        stmt.setString(2, maHD);
                        stmt.setInt(3, soBan);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            soLuongDatCu = rs.getInt("SoLuongDat");
                        } else {
                            throw new SQLException("Hóa đơn chi tiết không tồn tại!");
                        }
                    }

                    // Kiểm tra số lượng món còn lại
                    String sqlCheckMon = "SELECT SoLuong, TinhTrangMon FROM menu WHERE MaMon = ?";
                    int soLuongHienTai = 0;
                    String tinhTrangMon = "";
                    try (PreparedStatement stmt = conn.prepareStatement(sqlCheckMon)) {
                        stmt.setString(1, maMon);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            soLuongHienTai = rs.getInt("SoLuong");
                            tinhTrangMon = rs.getString("TinhTrangMon");
                        } else {
                            throw new SQLException("Món không tồn tại!");
                        }
                    }

                    if (tinhTrangMon.equals("Hết") && soLuongDat > soLuongDatCu) {
                        JOptionPane.showMessageDialog(view, "❌ Món " + maMon + " đã hết hàng!");
                        return;
                    }

                    // Tính số lượng cần điều chỉnh
                    int deltaSoLuong = soLuongDat - soLuongDatCu;
                    if (soLuongHienTai < deltaSoLuong) {
                        JOptionPane.showMessageDialog(view, "❌ Số lượng đặt vượt quá số lượng hiện có (" + soLuongHienTai + ")!");
                        return;
                    }

                    // Cập nhật hóa đơn chi tiết
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setMaMon(maMon);
                    hdct.setMaHoaDonBanHang(maHD);
                    hdct.setSoBan(soBan);
                    hdct.setSoLuongDat(soLuongDat);
                    model.suaHDChiTiet(hdct);

                    // Cập nhật số lượng món trong menu
                    String sqlUpdateMenu = "UPDATE menu SET SoLuong = SoLuong - ?, TinhTrangMon = IF(SoLuong - ? = 0, 'Hết', TinhTrangMon) WHERE MaMon = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sqlUpdateMenu)) {
                        stmt.setInt(1, deltaSoLuong);
                        stmt.setInt(2, deltaSoLuong);
                        stmt.setString(3, maMon);
                        stmt.executeUpdate();
                    }

                    // Cập nhật trạng thái bàn nếu hóa đơn là "Chưa thanh toán"
                    if (trangThai.equals("Chưa thanh toán")) {
                        String sqlUpdateBan = "UPDATE ban SET TinhTrangBan = 'Đang phục vụ' WHERE SoBan = ?";
                        try (PreparedStatement stmt = conn.prepareStatement(sqlUpdateBan)) {
                            stmt.setInt(1, soBan);
                            stmt.executeUpdate();
                        }
                    }

                    // Kiểm tra món có hết hàng không
                    if (soLuongHienTai - deltaSoLuong == 0) {
                        JOptionPane.showMessageDialog(view, "Món " + maMon + " đã hết hàng!");
                    }

                    conn.commit();
                    JOptionPane.showMessageDialog(view, "✅ Sửa hóa đơn chi tiết thành công!");
                } catch (SQLException e) {
                    conn.rollback();
                    JOptionPane.showMessageDialog(view, "❌ Lỗi khi sửa hóa đơn chi tiết: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } finally {
                    conn.setAutoCommit(true);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "⚠️ Dữ liệu nhập không hợp lệ! Vui lòng nhập số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "❌ Lỗi kết nối cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void xoaHDChiTiet() {
        try {
            String maMon = view.getTxtMaMon().getText().trim();
            String maHD = view.getTxtMaHoaDonBanHang().getText().trim();
            String soBanStr = view.getTxtSoBan().getText().trim();

            if (maMon.isEmpty() || maHD.isEmpty() || soBanStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int soBan;
            try {
                soBan = Integer.parseInt(soBanStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "⚠️ Số bàn không hợp lệ! Vui lòng nhập số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

            // Bắt đầu giao dịch
            try (Connection conn = Connect.getConnect()) {
                conn.setAutoCommit(false);
                try {
                    // Lấy số lượng món hiện tại
                    String sqlCheck = "SELECT SoLuongDat FROM hoadonbanhangchitiet WHERE MaMon = ? AND MaHoaDonBanHang = ? AND SoBan = ?";
                    int soLuongDat = 0;
                    try (PreparedStatement stmt = conn.prepareStatement(sqlCheck)) {
                        stmt.setString(1, maMon);
                        stmt.setString(2, maHD);
                        stmt.setInt(3, soBan);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            soLuongDat = rs.getInt("SoLuongDat");
                        } else {
                            throw new SQLException("Hóa đơn chi tiết không tồn tại!");
                        }
                    }

                    // Cập nhật số lượng món trong menu
                    String sqlUpdateMenu = "UPDATE menu SET SoLuong = SoLuong + ?, TinhTrangMon = 'Còn' WHERE MaMon = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sqlUpdateMenu)) {
                        stmt.setInt(1, soLuongDat);
                        stmt.setString(2, maMon);
                        stmt.executeUpdate();
                    }

                    // Xóa bản ghi trong hoadonbanhangchitiet
                    String sqlDelete = "DELETE FROM hoadonbanhangchitiet WHERE MaMon = ? AND MaHoaDonBanHang = ? AND SoBan = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {
                        stmt.setString(1, maMon);
                        stmt.setString(2, maHD);
                        stmt.setInt(3, soBan);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected == 0) {
                            throw new SQLException("Không tìm thấy bản ghi để xóa!");
                        }
                    }

                    // Kiểm tra xem còn hóa đơn "Chưa thanh toán" nào cho SoBan không
                    String sqlCheckBan = "SELECT COUNT(*) FROM hoadonbanhang h " +
                                        "JOIN hoadonbanhangchitiet c ON h.MaHoaDonBanHang = c.MaHoaDonBanHang " +
                                        "WHERE c.SoBan = ? AND h.TrangThai= 'Chưa thanh toán'";
                    try (PreparedStatement stmt = conn.prepareStatement(sqlCheckBan)) {
                        stmt.setInt(1, soBan);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next() && rs.getInt(1) == 0) {
                            // Nếu không còn hóa đơn "Chưa thanh toán", đặt TinhTrangBan thành "Trống"
                            String sqlUpdateBan = "UPDATE ban SET TinhTrangBan = 'Trống' WHERE SoBan = ?";
                            try (PreparedStatement updateStmt = conn.prepareStatement(sqlUpdateBan)) {
                                updateStmt.setInt(1, soBan);
                                updateStmt.executeUpdate();
                            }
                        }
                    }

                    conn.commit();
                    JOptionPane.showMessageDialog(view, "✅ Xóa hóa đơn chi tiết thành công!");
                } catch (SQLException e) {
                    conn.rollback();
                    JOptionPane.showMessageDialog(view, "❌ Lỗi khi xóa hóa đơn chi tiết: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } finally {
                    conn.setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "❌ Lỗi kết nối cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}