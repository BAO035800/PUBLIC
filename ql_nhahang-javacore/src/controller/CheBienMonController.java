package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.CheBienMon;
import model.CheBienMonDAO;
import model.Connect;
import view.bepCheBienMon;

public class CheBienMonController {
    private bepCheBienMon view;
    private CheBienMonDAO dao;

    public CheBienMonController(bepCheBienMon view, CheBienMonDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    public void themCheBienMon() {
        try {
            String maCheBien = view.getTxtMaCheBien().getText();
            String maMon = view.getTxtMaMon().getText();
            int soBan = Integer.parseInt(view.getTxtSoBan().getText());
            String maHoaDon = view.getTxtMaHoaDon().getText();
            String tinhTrang = (String) view.getCmbTinhTrang().getSelectedItem();

            if (maCheBien.isEmpty() || maMon.isEmpty() || maHoaDon.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin!");
                return;
            }

            CheBienMon cb = new CheBienMon();
            cb.setMaCheBien(maCheBien);
            cb.setMaMon(maMon);
            cb.setSoBan(soBan);
            cb.setMaHoaDonBanHang(maHoaDon);
            cb.setTinhTrang(tinhTrang);

            dao.themCheBienMon(cb);
            JOptionPane.showMessageDialog(view, "Thêm chế biến món thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Số bàn phải là số nguyên!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi thêm chế biến món: " + e.getMessage());
        }
    }

    public void suaCheBienMon() {
        try {
            CheBienMon cb = new CheBienMon();
            cb.setMaCheBien(view.getTxtMaCheBien().getText());
            cb.setMaMon(view.getTxtMaMon().getText());
            cb.setSoBan(Integer.parseInt(view.getTxtSoBan().getText()));
            cb.setMaHoaDonBanHang(view.getTxtMaHoaDon().getText());
            cb.setTinhTrang((String) view.getCmbTinhTrang().getSelectedItem());

            if (cb.getMaCheBien().isEmpty() || cb.getMaMon().isEmpty() || cb.getMaHoaDonBanHang().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin!");
                return;
            }

            dao.suaCheBienMon(cb);
            JOptionPane.showMessageDialog(view, "Cập nhật chế biến món thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Số bàn phải là số nguyên!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật chế biến món: " + e.getMessage());
        }
    }

    public void xoaCheBienMon(String maCheBien) {
        try {
            if (maCheBien.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Mã chế biến không được để trống!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(view, 
                "Bạn có chắc muốn xóa chế biến món với mã " + maCheBien + "?", 
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                dao.xoaCheBienMon(maCheBien);
                JOptionPane.showMessageDialog(view, "Xóa chế biến món thành công!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi xóa chế biến món: " + e.getMessage());
        }
    }

    public void refreshTable() {
        try {
            // Tạo danh sách để lưu dữ liệu từ bảng hoadonchitiet
            List<CheBienMon> hoaDonChiTietList = new ArrayList<>();
            
            // Kết nối cơ sở dữ liệu và lấy dữ liệu từ bảng hoadonchitiet với hóa đơn chưa thanh toán
            String sql = "SELECT c.MaHoaDonBanHang, c.MaMon, c.SoBan " +
                         "FROM hoadonbanhangchitiet c " +
                         "JOIN hoadonbanhang h ON c.MaHoaDonBanHang = h.MaHoaDonBanHang " +
                         "WHERE h.TrangThai = 'Chưa thanh toán'";
            try (Connection conn = Connect.getConnect();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CheBienMon cb = new CheBienMon();
                    cb.setMaHoaDonBanHang(rs.getString("MaHoaDonBanHang"));
                    cb.setMaMon(rs.getString("MaMon"));
                    cb.setSoBan(rs.getInt("SoBan"));
                    hoaDonChiTietList.add(cb);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view, "Lỗi khi lấy dữ liệu từ bảng hóa đơn chi tiết: " + e.getMessage());
                return;
            }

            // Kiểm tra nếu không có dữ liệu
            if (hoaDonChiTietList.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không có hóa đơn chưa thanh toán trong bảng hóa đơn chi tiết để làm mới!");
                return;
            }

            // Duyệt qua từng bản ghi từ hoadonchitiet và thêm vào chebienmonan
            for (CheBienMon hoadon : hoaDonChiTietList) {
                String maMon = hoadon.getMaMon();
                int soBan = hoadon.getSoBan();
                String maHoaDon = hoadon.getMaHoaDonBanHang();

                // Kiểm tra dữ liệu
                if (maMon == null || maHoaDon == null) {
                    JOptionPane.showMessageDialog(view, "Dữ liệu từ hóa đơn chi tiết không hợp lệ!");
                    continue;
                }

                // Tạo mã chế biến tự động (CB001, CB002, ...)
                String maCheBien = taoMaCheBienTuDong();

                // Tạo đối tượng CheBienMon với trạng thái "Đang làm"
                CheBienMon cb = new CheBienMon();
                cb.setMaCheBien(maCheBien);
                cb.setMaMon(maMon);
                cb.setSoBan(soBan);
                cb.setMaHoaDonBanHang(maHoaDon);
                cb.setTinhTrang("Đang làm");

                // Thêm vào cơ sở dữ liệu
                dao.themCheBienMon(cb);

                // Thông báo thành công
                JOptionPane.showMessageDialog(view, "Đã thêm món vào chế biến với mã " + maCheBien + "!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi làm mới bảng: " + e.getMessage());
        }
    }

    private String taoMaCheBienTuDong() {
        // Lấy danh sách các bản ghi hiện có để xác định mã tiếp theo
        List<CheBienMon> danhSach = dao.getDanhSachCheBien();
        int maxId = 0;

        // Tìm mã lớn nhất hiện có (dạng CBXXX)
        for (CheBienMon cb : danhSach) {
            String maCheBien = cb.getMaCheBien();
            if (maCheBien != null && maCheBien.startsWith("CB")) {
                try {
                    int id = Integer.parseInt(maCheBien.substring(2));
                    maxId = Math.max(maxId, id);
                } catch (NumberFormatException e) {
                    // Bỏ qua nếu không parse được
                }
            }
        }

        // Tăng mã lên 1 và định dạng lại (CB001, CB002, ...)
        maxId++;
        return String.format("CB%03d", maxId);
    }
}